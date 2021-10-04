package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.criaStage;

public class ViewControllerRelatoriosTitulos {
	
	@FXML
	private Button btTitulosAberto;
	@FXML
	private Button btTitulosLiquidados;
	@FXML
	private Button btTitulosVencidos;
	@FXML
	private Button btVoltar;
			
	@FXML
	private void onBtTitulosAbertos(ActionEvent event) {
		System.out.println("Titulso Abertos");		
	}
	
	@FXML
	private void onBtTitulosLiquidados(ActionEvent event) {
		System.out.println("Titulos Liquidados");		
	}
	
	@FXML
	private void onBtTitulosVencidos(ActionEvent event) {
		System.out.println("Titulos Vencidos");		
	}
	
	@FXML
	private void onBtVoltar(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewConsulta.fxml", "CONSULTA");
	}
	
	

}
