package Chat;

import java.util.*;

public class Student {
	private String fname;
	private String lname;
	private int score;
	public List<Response> Responses= new ArrayList<Response>();
	public Student(String fn, String ln, int sco){
		fname=fn;
		lname=ln;
		score=sco;
	}
	public String getFirstName(){
		return fname;
	}
	public String getLastName(){
		return lname;
	}
	public int getScore(){
		return score;
	}
}
