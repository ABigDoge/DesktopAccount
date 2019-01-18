package controller;

import model.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.util.Date;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.lang.management.ThreadMXBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar; 

public class MyController {
	@FXML
	private Button but_1;
	@FXML
	TextField text_1;
	@FXML
	PasswordField text_2;
	@FXML
	Label tip_name;
	@FXML
	Label tip_psw;
	
	private Main main;
	private Stage stage;

	public void setMain(Main main) {
		this.main=main;
	}
	
	public void init(Main main,Stage stage) {
		this.stage=stage;
	}
	
	public int check() {
		if(MyChecker.check4empty(text_1.getText())) {
			tip_name.setText("请输入用户名");
			return 1;
		}
		else if(MyChecker.check4empty(text_2.getText())) {
			tip_psw.setText("请输入密码");
			return 1;
		}
		return 0;
		
	}

//	MyOutput output = new MyOutput(mytext2, mytext1);
	
	public int checkPsw() {
		
		if(check()==1) {
			return 0;
		}
		DBInit.check4user();
		int rs = MyPutManager.findUser(text_1.getText(), text_2.getText());
		if(rs==2) {
			tip_name.setText("该用户不存在！");
		}
		else if(rs==0) {
			tip_psw.setText("密码错误！");
		}
		return rs;
	}
	
	public void main2Write() {
		
		String message = text_1.getText();
		main.openNewWindowWrite(message);
		
	}
	
	public void main2Homepage() {
		
		String message = text_1.getText();
		tip_name.setText("");
		tip_psw.setText("");
		if(checkPsw()==1) {
			main.change2Homepage(message);
		}
		else {
			System.out.println("error");
		}
	//	stage.hide();
		
	}
	
	public void createUser() {
		
		main.openNewWindowRegister();
		
	}
}
