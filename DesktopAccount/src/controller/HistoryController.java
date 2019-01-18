package controller;

import model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.*;

public class HistoryController implements Initializable{
	
	    @FXML 
	    private TableView<MyPut> tview;
	    @FXML
	    private TableColumn<MyPut, String> col_a;
	    @FXML
	    private TableColumn<MyPut, String> col_d;
	    @FXML
	    private TableColumn<MyPut, String> col_t;
	    @FXML
	    private TableColumn<MyPut, String> col_s;
	  
	    
	    private Stage stage;
	
	    String username;
	    double money=0;
	    
	    public ObservableList<MyPut> list = FXCollections.observableArrayList();
	    
	    public void init(Main main,Stage stage) {
			this.stage=stage;
			
		}
	    
	    public void setMessage(String message) {
			
			username = message;
			
		}
	    
	    public void getMyPut(int flag) {
	    	
	    	Connection connection = null;
			try {
			
				connection = DriverManager.getConnection("jdbc:sqlite:myaccount.db");
			
				Statement statement = connection.createStatement();
			//	System.out.println(username);
				System.out.println("open successfully");
				
				String sql = "select * from '" + username +"';";
				ResultSet rs = statement.executeQuery(sql);
				if(rs==null) {
					System.out.println("itsnull");
				}
				while (rs.next()) {
					money = rs.getDouble("AMOUNT");
					if( flag==1 && money<0 ) {
						continue;
					}
					else if( money<0 ) {
						money = -money;
					}
					else if( flag==0 && rs.getDouble("AMOUNT")>0 ) {
						continue;
					}
					MyPut user = new MyPut(rs.getString("DETAIL"),
							Double.toString(money),
							rs.getString("date"), rs.getString("SORT"));
					list.add(user);
				//	System.out.println(rs.getString("SORT"));
				}
				System.out.println("find account successfully");
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}

		}
	    
	    public void showList(){
	          
	    //    MyPut user = new MyPut("11", "22");
	     //   System.out.println(user.getTime());
	    //     user.setAmount("11");
	      //   user.setDetail("123");
	         
	    	list.clear();
	        col_a.setCellValueFactory(new PropertyValueFactory("amount"));//ӳ��          
	        col_d.setCellValueFactory(new PropertyValueFactory("detail")); 
	        col_t.setCellValueFactory(new PropertyValueFactory("time"));
	        col_s.setCellValueFactory(new PropertyValueFactory("sort"));
	        
	        getMyPut(1);
	     
	         tview.setItems(list); 
	       }
	    
	    public void showlistIn() {
	    	
	    	list.clear();
	    	col_a.setCellValueFactory(new PropertyValueFactory("amount"));//ӳ��          
	        col_d.setCellValueFactory(new PropertyValueFactory("detail")); 
	        col_t.setCellValueFactory(new PropertyValueFactory("time"));
	        col_s.setCellValueFactory(new PropertyValueFactory("sort"));
	        
	        getMyPut(0);
	        tview.setItems(list); 
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
}
