package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.criaStage;

public class ViewControllerArquivos {
	
	@FXML
	private Button btGerado;
	@FXML
	private Button btExportado;
	@FXML
	private Button btImportado;
	@FXML
	private Button btVoltar;
			
	@FXML
	private void onBtGerado(ActionEvent event) {
		System.out.println("Gerado");		
	}
	
	@FXML
	private void onBtExportado(ActionEvent event) {
		System.out.println("Exportado");		
	}
	
	@FXML
	private void onBtImportado(ActionEvent event) {
		System.out.println("Importado");	
	}
	
	@FXML
	private void onBtVoltar(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewConsulta.fxml", "CONSULTA");
	}
	
	

}
