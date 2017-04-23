package application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	MainInterfaceController mainInterface = new MainInterfaceController();

	/**
	 * The main window of the application
	 */
	public static Stage window;

	@Override
	public void start(Stage stage) {
		try {

			// Scene
			Scene scene = new Scene(mainInterface);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Primary Stage
			window = stage;
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/unmute.png")));
			stage.setScene(scene);
			stage.setOnCloseRequest(close->System.exit(0));
			stage.show();
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, " Error loading the Main class", ex);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
