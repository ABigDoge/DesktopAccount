package controller;
	
import model.*;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		showMainWindow();
	}
	
	public void showMainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("/view/MainWindow.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
	//		AnchorPane root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			MyController controller = loader.getController();
			controller.setMain(this);
			controller.init(this, primaryStage);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setTitle("登录窗口");
			primaryStage.getIcons().add(new Image("/resources/images/login.png"));
		//	primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openNewWindowWrite(String message) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/WriteView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			WriteController controller = loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());

			Stage newStage = new Stage();
			controller.init(this, newStage);
			controller.setMessage(message);
			newStage.setScene(scene);
			newStage.setTitle("记录支出");
			newStage.getIcons().add(new Image("/resources/images/buildput.png"));
			
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void openNewWindowIn(String message) {
		
		try {

			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/WriteInView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			WriteInController controller = loader.getController();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());

			Stage newStage = new Stage();
			controller.init(this, newStage);
			controller.setMessage(message);
			newStage.setScene(scene);
			newStage.setTitle("记录收入");
			newStage.getIcons().add(new Image("/resources/images/buildput.png"));
		
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void openNewWindowRegister() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/NewUserView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			NewUser controller = loader.getController();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());

			Stage newStage = new Stage();
			controller.init(this, newStage);
			newStage.setScene(scene);
			newStage.setTitle("注册新用户");
			newStage.getIcons().add(new Image("/resources/images/newuser.png"));
			
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void openNewWindowHistory(String message) {
		
		try {
	
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/HistoryView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			HistoryController controller = loader.getController();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());

			Stage newStage = new Stage();
			controller.init(this, newStage);
			controller.setMessage(message);
			controller.showList();
	//		System.out.println(message);
			newStage.setScene(scene);
			newStage.setTitle("历史记录");
			newStage.getIcons().add(new Image("/resources/images/book.png"));
		
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void change2Homepage(String message) {

		try {
		
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("/view/HomepageView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			Homepage controller = loader.getController();
			controller.setMain(this);
			controller.setMessage(message);
			controller.showBarchartnGetTotal();
		
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("主页");
			primaryStage.getIcons().add(new Image("/resources/images/homepage.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}