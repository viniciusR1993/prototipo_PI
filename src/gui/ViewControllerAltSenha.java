package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import entities.Usuario;
import gui.listeners.DataChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.UsuarioDao;

public class ViewControllerAltSenha implements Initializable{
	
	@FXML
	private Button btAlterar;
	@FXML
	private Button btSair;
	@FXML
	private TextField txUsuario;
	@FXML
	private PasswordField psSenha;
	
	private Usuario entity;
	//private UsuarioService service;
	//private TipoCadastroService tipoCadastroService;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void setUsuario(Usuario entity) {
		this.entity = entity;
	}
	/*public void setServices(UsuarioService service, TipoCadastroService tipoCadastroService) {
		this.service = service;
		this.tipoCadastroService = tipoCadastroService;
	}*/
	private Usuario getFormData() {
	
		Usuario obj = new Usuario();
		
		//ValidationException exception = new ValidationException("Validation error");

		/*obj.setId(Utils.tryParseToInt(txUsuario.getText()));

		if (txUsuario.getText() == null || txUsuario.getText().trim().equals("")) {
			System.out.println("Nome não encontrado");
			//exception.addError("name", "Field can't be empty");
		}*/
		obj.setName(txUsuario.getText());

		/*if (comboTipoCadastro.getValue() == null || comboTipoCadastro.getValue().getTipo().trim().equals("")) {
			System.out.println("Cadastro não encontrado");
			//exception.addError("Tipo Cadastro", "Field can't be empty");
		}*/
		
		//obj.setTipoCadastro(comboTipoCadastro.getValue().getTipo());
		
		obj.setPassword(psSenha.getText());
		return obj;
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		 // Define que somente inteiros serão aceito no txtId
		//Constraints.setTextFieldMaxLength(txUsuario, 70); // define tamanho maximo de 70 caracteres para txtName
	}
		
	@FXML
	private void onBtAlterar(ActionEvent event) {
		/*if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}*/
		try {
			UsuarioDao user = DaoFactory.createUserDao();
			List<Usuario> listUser = user.findAll();
			entity = getFormData();
			for(Usuario u: listUser) {
				if(u.getName().equals(entity.getName())) {
					entity.setId(u.getId());
					entity.setTipoCadastro(u.getTipoCadastro());
				}
			}
			
			user.update(entity);
			notifyDataChangeListeners();
			//Utils.currentStage(event).close();
			System.out.println("Senha alterada");
		} catch (DbException e) {
			Alerts.showAlert("Erro saving object", null, e.getMessage(), AlertType.ERROR);
		}/* catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}*/
	}
	
	@FXML
	private void onBtSair(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	

}
