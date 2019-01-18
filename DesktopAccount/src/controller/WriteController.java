package controller;

import model.*;
import java.sql.*;

import java.util.Calendar;

import javax.print.attribute.standard.RequestingUserName;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class WriteController {

	@FXML
	private Button but_s;
	@FXML
	private Button but_c;
	@FXML
	private TextField text_a;
	
	@FXML
	private TextField text_d;
	@FXML
	private Label infoLabel;
	@FXML
    private RadioButton rid_food;

    @FXML
    private ToggleGroup out_sort;

    @FXML
    private RadioButton rid_cloth;

    @FXML
    private RadioButton rid_daily;

    @FXML
    private RadioButton rid_trans;

    @FXML
    private RadioButton rid_rent;

    @FXML
    private RadioButton rid_others;
    
    @FXML
    private Label lab_error;
    
    @FXML
	private Label lab_cong;

	
	private Stage stage;
	
	String username;
	String sort;
//	String regex="-?[0-9]+(\\.[0-9]+)?";
	String error=null;
	
	public int checkerror() {
		if(MyChecker.check4empty(text_a.getText())) {
			error="请填写钱数！";
			return 1;
		}
		else if(!MyChecker.check4num(text_a.getText())) {
			error="请填写有效的数字！";
			return 1;
		}
		else if(MyChecker.check4empty(text_d.getText())) {
			error="请填写具体说明！";
			return 1;
		}
		return 0;
	}
	
	public void store() {
		
		if(checkerror()==1) {
			lab_error.setText(error);
			return;
		}
		RadioButton selectedRadioButton = (RadioButton) out_sort.getSelectedToggle();
	    sort = selectedRadioButton.getText();
		
	    MyPutManager.store2sql(username, text_a.getText(),text_d.getText(), sort, 1);
	    lab_cong.setText("记录成功！");
	    
	}
	
	public void init(Main main,Stage stage) {
		this.stage=stage;
	}
	
	public void setMessage(String message) {
		
		username = message;
		
	}
}
