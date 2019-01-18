package model;

import controller.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyPut {
	
	private String detail;
	private  String amount;
	private String nowtime;
	private String sort;
	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
	
	public MyPut(String mydetail, String myamount, String time, String s) {
		detail = mydetail;
		amount = myamount;
	//	nowtime = dateFormat.format(date);
		nowtime = time;
		sort = s;
	}
	

	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
		
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
		
	}
	
	public String getTime() {
		return nowtime;
	}
	
	public String getSort() {
		return this.sort;
	}
}
