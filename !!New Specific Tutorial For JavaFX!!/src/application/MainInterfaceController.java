package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * The main interface of the application
 * 
 * @author GOXR3PLUS
 *
 */
public class MainInterfaceController extends BorderPane {
	
	@FXML
	private Button start;
	
	@FXML
	private Button pause;
	
	@FXML
	private Button resume;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private TextArea infoArea;
	
	// -----------------------------------------
	
	private SpeechRecognizer speechRecognition = new SpeechRecognizer();
	
	/**
	 * Constructor
	 */
	public MainInterfaceController() {
		
		// FXMLLoader
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainInterfaceController.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
		} catch (IOException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, " FXML can't be loaded!", ex);
		}
		
	}
	
	/**
	 * Called as soon as .fxml is initialised
	 */
	@FXML
	private void initialize() {
		
		// start
		start.disableProperty().bind(speechRecognition.speechRecognizerThreadRunningProperty());
		start.setOnAction(a -> {
			statusLabel.setText("Status : [Running]");
			//infoArea.appendText("Starting Speech Recognizer\n");
			speechRecognition.startSpeechRecognition();
		});
		
		// stop
		pause.disableProperty().bind(speechRecognition.ignoreSpeechRecognitionResultsProperty().or(start.disabledProperty().not()));
		pause.setOnAction(a -> {
			statusLabel.setText("Status : [Paused]");
			//infoArea.appendText("Pausing Speech Recognizer\n");
			speechRecognition.ignoreSpeechRecognitionResults();
		});
		
		// restart
		resume.disableProperty().bind(speechRecognition.ignoreSpeechRecognitionResultsProperty().not());
		resume.setOnAction(a -> {
			statusLabel.setText("Status : [Running]");
			//infoArea.appendText("Resuming Speech Recognizer\n");
			speechRecognition.stopIgnoreSpeechRecognitionResults();
		});
		
		//Bind the SpeechRecognitionText to InfoArea
		infoArea.textProperty().bind(Bindings.createStringBinding(() -> infoArea.getText() + " \n " + speechRecognition.getSpeechRecognitionResultProperty().get(),
				speechRecognition.getSpeechRecognitionResultProperty()));
		
	}
}
