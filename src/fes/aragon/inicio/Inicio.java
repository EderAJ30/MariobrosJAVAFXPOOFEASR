package fes.aragon.inicio;
	
import fes.aragon.controlador.SampleController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Inicio extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Stage grapherStage = new Stage();
			FXMLLoader root = new FXMLLoader(getClass().getResource("/fes/aragon/fxml/Sample.fxml"));
			
			//Pane root = (Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/Sample.fxml"));
			Scene scene = new Scene(root.load());
			scene.getStylesheets().add(getClass().getResource("/fes/aragon/css/application.css").toExternalForm());
			SampleController controlador=root.getController();
			controlador.setEscena(scene);
			controlador.iniciar();
			
			primaryStage.setScene(scene);
			controlador.eventosVentana();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
