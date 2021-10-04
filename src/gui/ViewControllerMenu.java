package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.criaStage;

public class ViewControllerMenu {
	
	@FXML
	private Button btUsuario;
	@FXML
	private Button btClientes;
	@FXML
	private Button btBancos;
	@FXML
	private Button btIniciar;
	@FXML
	private Button btSair;
			
	@FXML
	private void onBtUsuario(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewUsuario.fxml", "Usuario");			
	}
	
	@FXML
	private void onBtCliente(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewClientes.fxml", "Clientes");		
	}
	
	@FXML
	private void onBtBancos(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewBanco.fxml", "Banco");			
	}
	
	@FXML
	private void onBtIniciar(ActionEvent event) {
		Utils.currentStage(event).close();	
		new criaStage("/gui/ViewPaginaInicial.fxml", "Pagina Inicial");		
	}
	
	@FXML
	private void onBtSair(ActionEvent event) {
		Utils.currentStage(event).close();	
	}
	
	

}
