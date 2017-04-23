package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import model.SpeechCalculator;

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
	private Button stop;

	@FXML
	private Button restart;

	@FXML
	private Label statusLabel;

	@FXML
	private TextArea infoArea;

	// -----------------------------------------

	private SpeechCalculator speechCalculator = new SpeechCalculator();

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
	 * As soon as fxml has been loaded then this method will be called
	 * 1)-constructor,2)-FXMLLOADER,3)-initialize();
	 */
	@FXML
	private void initialize() {

		// start
		start.setOnAction(a -> {
			statusLabel.setText("Status : [Running]");
			infoArea.appendText("Starting Speech Recognizer\n");
			speechCalculator.startSpeechThread();
		});

		// stop
		stop.setOnAction(a -> {
			statusLabel.setText("Status : [Stopped]");
			infoArea.appendText("Stopping Speech Recognizer\n");
			speechCalculator.stopSpeechThread();
		});

		// restart
		restart.setDisable(true);

	

	}
}
