package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import db.DbException;
import entities.Banco;
import gui.Util.Constraints;
import gui.listeners.DataChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.service.BancoService;

public class BancoFormController implements Initializable {

	private BancoService service;
	//private ObservableList<Banco> obsList;
	//private TipoCadastroService tipoCadastroService;

	private Banco entity;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtName;
	@FXML
	private Label lbErrorName;
	@FXML
	private Label lbErrorId;
	@FXML
	private Label lbErrorCodigo;
		
	public void setBanco(Banco entity) {
		this.entity = entity;
	}

	public void setServices(BancoService service) {
		this.service = service;
		//this.tipoCadastroService = tipoCadastroService;
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
	
	private Banco getFormData() {
		
		Banco obj = new Banco();
		
		//ValidationException exception = new ValidationException("Validation error");

		obj.setId_banco(Utils.tryParseToInt(txtId.getText()));
		obj.setCodigo(Utils.tryParseToInt(txtCodigo.getText()));

		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			System.out.println("Nome não encontrado");
			//exception.addError("name", "Field can't be empty");
		}
		obj.setNome(txtName.getText());

		/*if (comboTipoCadastro.getValue() == null || comboTipoCadastro.getValue().getTipo().trim().equals("")) {
			System.out.println("Cadastro não encontrado");
			//exception.addError("Tipo Cadastro", "Field can't be empty");
		}*/
		
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
		Constraints.setTextFieldInteger(txtCodigo); // Define que somente inteiros serão aceito no txtId
		Constraints.setTextFieldMaxLength(txtName, 70); // define tamanho maximo de 70 caracteres para txtName
		
		//initializeComboBoxTipoCadastro();
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("entity was null");
		}
		txtId.setText(String.valueOf(entity.getId_banco()));
		txtName.setText(entity.getNome());
		txtCodigo.setText(String.valueOf(entity.getCodigo()));
		//comboTipoCadastro.setValue(new TipoCadastro(entity.getTipoCadastro()));
		//txtSenha.setText(entity.getPassword());
		Locale.setDefault(Locale.US);
	}
	
	/*public void loadAssociateObjects() {
		if (tipoCadastroService == null) {
			throw new IllegalStateException("DepartmentService was null");
		}
		List<Cargo> list = tipoCadastroService.findAll();
		obsList = FXCollections.observableArrayList(list);
		comboTipoCadastro.setItems(obsList);
	}

	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		lbErrorName.setText(fields.contains("name") ? errors.get("name") : "");
		lbErrorId.setText(fields.contains("id") ? errors.get("id") : "");
		lbErrorTipoCadastro.setText(fields.contains("tipoCadastro")? errors.get("tipoCadastro") : "");
	}*/
	
	/*private void initializeComboBoxTipoCadastro() {
		Callback<ListView<Cargo>, ListCell<Cargo>> factory = lv -> new ListCell<Cargo>() {
			@Override
			protected void updateItem(Cargo item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getTipo());
			}
		};
		comboTipoCadastro.setCellFactory(factory);
		comboTipoCadastro.setButtonCell(factory.call(null));		
	}*/
}
