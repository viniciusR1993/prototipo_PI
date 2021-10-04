package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {	
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/View.fxml"));
			Parent parent = loader.load();
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("SGR - Sistema de Gestão de Recebiveis");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Usado para pega o Scene principal
		public static Scene getMainScene() {
			return mainScene;
		}

		public static void main(String[] args) {
			launch(args);
		}
}
