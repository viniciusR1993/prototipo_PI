package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.criaStage;

public class ViewControllerExecutarArquivos {
	
	@FXML
	private Button btGerarArquivo;
	@FXML
	private Button btExportarArquivo;
	@FXML
	private Button btImportarArquivo;
	@FXML
	private Button btVoltar;
			
	@FXML
	private void onBtGerarArquivo(ActionEvent event) {
		System.out.println("Clicou em GerarArquivo");		
	}
	
	@FXML
	private void onBtExportarArquivo(ActionEvent event) {
		System.out.println("Clicou em Exportar Arquivo");		
	}
	
	@FXML
	private void onBtImportarArquivo(ActionEvent event) {
		System.out.println("Clicou em Importar Arquivo");		
	}
	
	@FXML
	private void onBtVoltar(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewPaginaInicial.fxml", "Pagina Inicial");	
	}
	
	

}
