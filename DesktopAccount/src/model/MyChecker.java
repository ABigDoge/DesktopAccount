package model;

public class MyChecker {
	
	public static boolean check4empty(String str) {
		if(str.length()==0||str==null) {
			return true;
		}
		return false;
		
	}
	
	public static boolean check4name(String str) {
		String regex="[0-9]+.*";
		if(str.matches(regex)) {
			return true;
		}
		return false;
		
	}
	
	public static boolean check4num(String str) {
		String regex="-?[0-9]+(\\.[0-9]+)?";
		if(str.matches(regex)) {
			return true;
		}
		return false;
		
	}
	
	public static boolean check4psw(String str) {
		String regex="^[0-9A-Za-z]{6,16}$";
		if(str.matches(regex)) {
			return true;
		}
		return false;
		
	}

}
