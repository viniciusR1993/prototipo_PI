package tool;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class criaStage {
	public criaStage(String caminhoView, String titulo) {
		
		try {
			Stage stage = new Stage();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoView));
	        Parent parent = loader.load();
	        
	        Scene scene = new Scene(parent);
	        stage.setScene(scene);
			stage.setTitle(titulo);
			stage.show();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
