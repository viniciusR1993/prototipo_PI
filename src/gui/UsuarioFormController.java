package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import db.DbException;
import entities.Cargo;
import entities.Usuario;
import gui.Util.Constraints;
import gui.listeners.DataChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.service.TipoCadastroService;
import model.service.UsuarioService;

public class UsuarioFormController implements Initializable {

	private UsuarioService service;
	private ObservableList<Cargo> obsList;
	private TipoCadastroService tipoCadastroService;

	private Usuario entity;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private TextField txtName;
	@FXML
	private Label lbErrorName;
	@FXML
	private Label lbErrorId;
	@FXML
	private Label lbErrorTipoCadastro;
	@FXML
	private Label lbErrorSenha;
	@FXML
	private ComboBox<Cargo> comboTipoCadastro;
	
	public void setUsuario(Usuario entity) {
		this.entity = entity;
	}

	public void setServices(UsuarioService service, TipoCadastroService tipoCadastroService) {
		this.service = service;
		this.tipoCadastroService = tipoCadastroService;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Erro saving object", null, e.getMessage(), AlertType.ERROR);
		}/* catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}*/

	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	private Usuario getFormData() {
		
		Usuario obj = new Usuario();
		
		//ValidationException exception = new ValidationException("Validation error");

		obj.setId(Utils.tryParseToInt(txtId.getText()));

		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			System.out.println("Nome não encontrado");
			//exception.addError("name", "Field can't be empty");
		}
		obj.setName(txtName.getText());

		if (comboTipoCadastro.getValue() == null || comboTipoCadastro.getValue().getTipo().trim().equals("")) {
			System.out.println("Cadastro não encontrado");
			//exception.addError("Tipo Cadastro", "Field can't be empty");
		}
		
		obj.setTipoCadastro(comboTipoCadastro.getValue().getTipo());
		
		obj.setPassword(txtSenha.getText());
		return obj;
	}

	@FXML
	public void onBtCancelActino(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId); // Define que somente inteiros serão aceito no txtId
		Constraints.setTextFieldMaxLength(txtName, 70); // define tamanho maximo de 70 caracteres para txtName
		
		initializeComboBoxTipoCadastro();
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		//comboTipoCadastro.setValue(new TipoCadastro(entity.getTipoCadastro()));
		txtSenha.setText(entity.getPassword());
		Locale.setDefault(Locale.US);
	}
	
	public void loadAssociateObjects() {
		if (tipoCadastroService == null) {
			throw new IllegalStateException("DepartmentService was null");
		}
		List<Cargo> list = tipoCadastroService.findAll();
		obsList = FXCollections.observableArrayList(list);
		comboTipoCadastro.setItems(obsList);
	}

	/*private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		lbErrorName.setText(fields.contains("name") ? errors.get("name") : "");
		lbErrorId.setText(fields.contains("id") ? errors.get("id") : "");
		lbErrorTipoCadastro.setText(fields.contains("tipoCadastro")? errors.get("tipoCadastro") : "");
	}*/
	
	private void initializeComboBoxTipoCadastro() {
		Callback<ListView<Cargo>, ListCell<Cargo>> factory = lv -> new ListCell<Cargo>() {
			@Override
			protected void updateItem(Cargo item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getTipo());
			}
		};
		comboTipoCadastro.setCellFactory(factory);
		comboTipoCadastro.setButtonCell(factory.call(null));		
	}
}
