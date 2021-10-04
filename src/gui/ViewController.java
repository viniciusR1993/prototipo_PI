package gui;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tool.bd;
import tool.criaStage;

public class ViewController {
	
	@FXML
	private Button btIniciar;
	@FXML
	private Button btSair;
	@FXML
	private TextField txMatricula;
	@FXML
	private PasswordField psSenha;
	@FXML
	private Label labelErroUsuario;
	@FXML
	private Label labelErroSenha;
		
	@FXML
	private void onBtIniciar(ActionEvent event) {
		labelErroUsuario.setText("");
		labelErroSenha.setText("");
		if(txMatricula.getText().trim().equals("")) {
			labelErroUsuario.setText("Informe o usuario para logar!");
		}
		if(psSenha.getText().trim().equals("")) {
			labelErroSenha.setText("Informe a senha para logar!");
		}
		
		Map<String,Integer> mapResult = bd.validaUsuario(txMatricula.getText(), psSenha.getText());
		System.out.println(mapResult);
		if(mapResult.get("chave") == 0 && !txMatricula.getText().trim().equals("")) {
			labelErroUsuario.setText("Usuário incorreto");
		}
		if(mapResult.get("chave") == 1 && mapResult.get("senha") == 0 && !psSenha.getText().trim().equals("")) {
			labelErroSenha.setText("Senha incorreta");
		}
		if(mapResult.get("chave") == 1 && mapResult.get("senha") == 1) {
			System.out.println("Entrar na aplicação");
			Utils.currentStage(event).close();
			if(bd.consultaTipoUsuario(txMatricula.getText()).equals("Administrador")) {
				new criaStage("/gui/ViewMenu.fxml", "Cadastro");
			}else {
				new criaStage("/gui/ViewPaginaInicial.fxml", "Pagina Inicial");
			}			
		}
	}
	
	@FXML
	private void onBtSair(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	

}
