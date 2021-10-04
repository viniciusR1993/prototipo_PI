package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.criaStage;

public class ViewControllerConsulta {
	
	@FXML
	private Button btRelatorioTitulos;
	@FXML
	private Button btArquivos;
	@FXML
	private Button btVoltar;
			
	@FXML
	private void onBtRelatorioTitulos(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewRelatoriosTitulos.fxml", "RELATÓRIOS DE TITULOS");		
	}
	
	@FXML
	private void onBtArquivos(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewArquivos.fxml", "ARQUIVOS");	
	}
	
	@FXML
	private void onBtVoltar(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewPaginaInicial.fxml", "Pagina Inicial");	
	}
	
	

}
