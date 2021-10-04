package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.criaStage;

public class ViewControllerPaginaInicial {
	
	@FXML
	private Button btExecutar;
	@FXML
	private Button btConsultar;
	@FXML
	private Button btConferenciaTitulos;
	@FXML
	private Button btLogout;
	@FXML
	private Button btAltSenha;
			
	@FXML
	private void onBtExecutar(ActionEvent event) {
		new criaStage("/gui/ViewExecutaArquivos.fxml", "EXECUTAR ARQUIVOS");
		Utils.currentStage(event).close();
	}
	
	@FXML
	private void onBtAltSenha(ActionEvent event) {
		new criaStage("/gui/ViewAltSenha.fxml", "Alterar Senha");
	}
	
	@FXML
	private void onBtConsultar(ActionEvent event) {
		new criaStage("/gui/ViewConsulta.fxml", "CONSULTA");
		Utils.currentStage(event).close();
	}
	
	@FXML
	private void onBtConferenciaTitulos(ActionEvent event) {
		new criaStage("/gui/ViewConferenciaTitulos.fxml", "CONFERÊNCIA DE TITULOS");
		Utils.currentStage(event).close();
	}
	
	@FXML
	private void onBtLogout(ActionEvent event) {
		Utils.currentStage(event).close();	
	}
	
	

}
