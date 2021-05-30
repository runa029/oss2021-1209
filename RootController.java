package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class RootController implements Initializable{
	@FXML private Button btnHelp;
	@FXML private Button btnShare;
	@FXML private Button btnExit;
	@FXML private Button btnOption;
	@FXML private ListView<String> folderList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	folderList.setItems(FXCollections.observableArrayList(
			//���� ����� �������� �̸��� ���⼭ �߰��� �� ������?
			));

		
	btnHelp.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			handleBtnHelpAction(event);
		} 
	   });
	
	 btnShare.setOnAction(event->handleBtnShareAction(event));
	 btnExit.setOnAction(event->{
		try {
			handleBtnExitAction(event);
		} catch (Exception e) {
			
		}
	});
	 btnOption.setOnAction(event->{
		try {
			handleBtnOptionAction(event);
		} catch (Exception e) {
			
		}
	});
	}
	
	private Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void handleBtnHelpAction(ActionEvent e) {
		//���� �˾� ����
	}
	
	public void handleBtnShareAction(ActionEvent e) {
		//���� �˾� ����
	}
	
	public void handleBtnExitAction(ActionEvent e) throws Exception {
		//���α׷� ���� �˾� ����
		Stage dialogExit = new Stage(StageStyle.UTILITY);
		dialogExit.initModality(Modality.WINDOW_MODAL);
		dialogExit.initOwner(primaryStage);
		dialogExit.setTitle("Ȯ��");
		
		Parent parentExit = FXMLLoader.load(getClass().getResource("Exit_dialog.fxml"));
		Button btnYes = (Button) parentExit.lookup("#btnYes");
		Button btnNo = (Button) parentExit.lookup("#btnNo");
		btnYes.setOnAction(event->Platform.exit());
		btnNo.setOnAction(event->dialogExit.close());
		
		Scene scene = new Scene(parentExit);
		dialogExit.setScene(scene);
		dialogExit.setResizable(false);
		dialogExit.show();
	}
	
	public void handleBtnOptionAction(ActionEvent e) throws Exception {
		//�޴� ���� ���
		Stage dialogOption = new Stage(StageStyle.UTILITY);
		dialogOption.initModality(Modality.WINDOW_MODAL);
		dialogOption.initOwner(primaryStage);
		dialogOption.setTitle("�ɼ�");
		
		Parent parentOption = FXMLLoader.load(getClass().getResource("Option_dialog.fxml"));
		
		Scene scene = new Scene(parentOption);
		dialogOption.setScene(scene);
		dialogOption.setResizable(false);
		dialogOption.show();
	}
	

	
}
