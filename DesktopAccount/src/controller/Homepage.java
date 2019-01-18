package controller;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Homepage {
	
	private Main main;
	
	String username;
	private Stage stage;
	double money=0;
	
	@FXML
	private Label infoLabel;
	@FXML
    private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Label text_out;

    @FXML
    private Label text_in;

    @FXML
    private Label text_most;
    
    String[] sorts = {"饮食","服饰","日用品","交通","租金","其他"};
	Map<String, Integer> mapsort=new HashMap<>();

    private ObservableList<String> outsort = FXCollections.observableArrayList();
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	public void goBack() {
		main.showMainWindow();
	}
	
	public void initialize() {
	
		for (int i = 0; i < 6; i++) {
			mapsort.put(sorts[i], i);
			
		}
		outsort.addAll(Arrays.asList(sorts));
		xAxis.setCategories(outsort);
		
		
	}
	
	public void showBarchartnGetTotal() {
		double[] sortcounter = new double[6];
		double out = 0, in = 0, most = 0;
		String mostsort="饮食";
		
		Connection connection = null;
		try {
		
			connection = DriverManager.getConnection("jdbc:sqlite:myaccount.db");
		
			Statement statement = connection.createStatement();
		//	System.out.println(username);
			System.out.println("open successfully");
			
			String sql = "select * from " + username +";";
			ResultSet rs = statement.executeQuery(sql);
			if(rs==null) {
				System.out.println("itsnull");
			}
			while (rs.next()) {
				money = rs.getDouble("AMOUNT");
				
				if( rs.getDouble("AMOUNT")<0 ) {
					in-=money;
					continue;
				}
				out+=money;
				sortcounter[mapsort.get(rs.getString("SORT"))]+=money;
				
			//	System.out.println(rs.getString("SORT"));
			}
			System.out.println("set data successfully");
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
		
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		for (int i = 0; i < sortcounter.length; i++) {
            series.getData().add(new XYChart.Data<>(outsort.get(i), sortcounter[i]));
        }
        
        barChart.getData().add(series);
        
        text_in.setText(Double.toString(in));
        text_out.setText(Double.toString(out));
        for (int i = 0; i < sortcounter.length; i++) {
        	
        	if( sortcounter[i]>most ) {
        		mostsort = sorts[i];
        		most = sortcounter[i];
        	}
 
		}
        text_most.setText(mostsort);
		
	}

	public void setMessage(String message) {
		
		username = message;
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY); 
		if(hour<12) {
			message += ",上午好";
		}
		else if(hour<18) {
			message += ",下午好";
		}
		else {
			message += ",晚上好";
		}
		infoLabel.setText(message);
	}
	
	public void home2Write() {
		
		main.openNewWindowWrite(username);
		
	}
	
	public void home2InWrite() {
		
		main.openNewWindowIn(username);
		
	}
	
	public void home2History2() {
		
		main.openNewWindowHistory(username);
		
	}
	
	public void refresh() {
	
		main.change2Homepage(username);
	}
}
