package controller;

import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.awt.Checkbox;
import java.sql.*;
import java.util.regex.Pattern;

public class NewUser {
	
	@FXML
	private TextField text_name;
	@FXML
	private PasswordField text_psw;
	@FXML
	private Button but_ok;
	@FXML
	private RadioButton but_male;
	@FXML
	private RadioButton but_fe;
	@FXML
	private ToggleGroup sex;
	@FXML
	private Label text_error;
	@FXML
	private Label lab_cong;
	
	private Stage stage;
	private  String usersex=null;
	private String error=null;
	
//	String regex1="^[0-9A-Za-z]{6,16}$";
//	String regex2="[0-9]+.*";
	
	public int finderror() {
		
		int flag=0;
		error=null;
		RadioButton selectedRadioButton = (RadioButton) sex.getSelectedToggle();
		usersex = selectedRadioButton.getText();
		if(MyChecker.check4empty(text_name.getText())) {
			error = "请输入用户名";
			System.out.println(text_name.getText());
			System.out.println(text_psw.getText());
			return 1;
		}
		else if(MyChecker.check4empty(text_psw.getText())) {
			error = "请输入密码";
			return 1;
		}
		else if(MyChecker.check4empty(usersex)) {
			error = "请选择您的性别";
			return 1;
		}
		else if(MyChecker.check4name(text_name.getText())) {
			error = "不合法的用户名！";
			return 1;
		}
		else if(!MyChecker.check4psw(text_psw.getText())) {
			error = "不合法的密码！";
			return 1;
		}
		return 0;
	}
	
	public void createNewUser() {
		
		text_error.setText("");
		if(finderror()==1) {
			text_error.setText(error);
			return;
		}
		DBInit.check4user();
		
		MyPutManager.buildUser(text_name.getText(), text_psw.getText(), usersex);
	    lab_cong.setText("恭喜您注册成功！");
		System.out.println("User insert successfully");
	    System.out.println(text_name.getText());
	}
	
	public void init(Main main,Stage stage) {
		this.stage=stage;
	}

}
