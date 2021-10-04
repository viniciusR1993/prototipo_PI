package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DbException;
import entities.Cliente;
import gui.listeners.DataChangeListener;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.service.ClienteService;
import tool.criaStage;

public class ViewControllerClientes implements Initializable, DataChangeListener{

	private ClienteService service  = new ClienteService();
	
	@FXML
	private TableView<Cliente> tableViewCliente;
	@FXML
	private TableColumn<Cliente, Integer> tableColumnId;
	@FXML
	private TableColumn<Cliente, String> tableColumnName;
	@FXML
	private TableColumn<Cliente, String> tableColumnDocumento;
	@FXML
	private TableColumn<Cliente, Cliente> tableColumnEDIT;
	@FXML
	private TableColumn<Cliente, Cliente> tableColumnREMOVE;
	@FXML
	private Button btNew;
	@FXML
	private Button btVoltar;
	
	private ObservableList<Cliente> obsCliente;
	
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Cliente obj = new Cliente();
		createDialogForm(obj, "/gui/ClientesForm.fxml", parentStage);
	}
	
	@FXML
	public void onBtVoltar(ActionEvent event) {
		Utils.currentStage(event).close();
		new criaStage("/gui/ViewMenu.fxml", "Cadastro");
	}

	// Em vez de dar o new SellerService na criação da variavel fazemos esse
	// metodo par anão fazer um forte acoplamento
	public void setClienteService(ClienteService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		updateTabelView();
		//Stage stage = (Stage) Main.getMainScene().getWindow(); // pega a referencia para janela no Scene e faz o casting
		//tableViewUsuario.prefHeightProperty().bind(stage.heightProperty()); // Isso faz com que a tableView acompanhe a altura da janela
	}
	
	// Ele vai acessar o serviço carregar os departamento, inserir na ObservableList
	// e carregar na tabela de Departmet
	public void updateTabelView() {
		ClienteDao user = DaoFactory.createClienteDao();
		List<Cliente> list = user.findAll();
		obsCliente = FXCollections.observableArrayList(list);
		tableViewCliente.setItems(obsCliente);
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
		initEditButtons(); // Esse acrescenta um botão Edit em cada lista da tabela
		initRemoveButtons();
	}
	
	public void createDialogForm(Cliente obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			ClientesFormController controller = loader.getController();
			controller.setCliente(obj);
			controller.setServices(new ClienteService());
			//controller.loadAssociateObjects();	//Carrega os departamentos do BD
			controller.subscribeDataChangeListener(this); // Se escreve para escrever o evento
			controller.updateFormData(); // Carrega os dados do objeto no formulario

			Stage dialogStage = new Stage(); // Novo palco para ter uma janela na frente da outra
			dialogStage.setTitle("Enter Cliente data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false); // Nesse caso informamos que a janela não pode ser redimensionada
			dialogStage.initOwner(parentStage); // Informa quem é o pai dessa stage
			dialogStage.initModality(Modality.WINDOW_MODAL); // Nesse caso informamos que ela é modal. Enquanto não
																// fechar não segue com a sessao anterior
			dialogStage.showAndWait(); // Executa a scena
		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTabelView();
	}

	// Esse codigo foi retirado na apostila da Udemy, serve para inserir o botão de edição nas colunas
	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Cliente, Cliente>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Cliente obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/ClientesForm.fxml", Utils.currentStage(event)));
			}
		});
	}
	// Esse codigo foi retirado na apostila da Udemy, serve para inserir o botão de edição nas colunas
	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Cliente, Cliente>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Cliente obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}
	
	private void removeEntity(Cliente obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Area you shure to delete?");
		if(result.get() == ButtonType.OK) {
			if(service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				service.remove(obj);
				updateTabelView();
			}catch(DbException e) {
				Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
			}			
		}
	}

}
