package Chat;

import java.util.*;
//import java.util.List;

public class Chat {
	public static List<Group> Groups= new ArrayList<Group>();
	public static List<Student> Students = new ArrayList<Student>();
	public static void main(String[] args){
		
		Students.add(new Student("Nathan", "Borup", 1));
		Students.add(new Student("Ethan", "Brown", 1) );
		Students.add(new Student("Michael", "Cullimore", 1) );
		Students.add(new Student("Kendra", "Koester", 1) );
		Students.add(new Student("Trevor", "Marsh", 1) );
		Students.add(new Student("Cody", "May", 9999) );
		Students.add(new Student("Brieanna", "Miller", 1) );
		Students.add(new Student("Rizwan", "Mohammad", 1) );
		Students.add(new Student("Lauren", "Ribiero", 1) );
		Students.add(new Student("Tyler", "Toponce", 1) );
		for(Student student : Students){
			System.out.println(student.getFirstName());
		}
		
		Groups.add(new Group (Students.get(0), Students.get(1)));
		Groups.add(new Group (Students.get(2), Students.get(3)));
		Groups.add(new Group (Students.get(4), Students.get(5)));
		Groups.add(new Group (Students.get(6), Students.get(7)));
		Groups.add(new Group (Students.get(8), Students.get(9)));
		int counter=0;
		for(Student student : Students){
			if(counter==0){
				counter++;
				student.Responses.add(new Response("Hello"));
				student.Responses.add(new Response("What's up?"));
				student.Responses.add(new Response("Everything is awful"));
				student.Responses.add(new Response("I agree."));
				student.Responses.add(new Response("I am a dragon, grrr"));
				}
			else{
				counter=0;
				student.Responses.add(new Response("Hey"));
				student.Responses.add(new Response("Trying to live my life"));
				student.Responses.add(new Response("What?"));
				student.Responses.add(new Response("I'm not following..."));
				student.Responses.add(new Response("Please leave"));
			}
			
		}
		for(Group group : Groups){
			Student one= group.Members.get(0);
			Student two= group.Members.get(1);
			System.out.println("Conversation between " + one.getFirstName()+ " " +one.getLastName()+ " and " + two.getFirstName()+ " " +two.getLastName()+ ":");
			for(int i=0; i<5; i++){
				System.out.println(one.getFirstName()+ " " +one.getLastName()+ " says:");
				System.out.println(one.Responses.get(i).getResponse());
				System.out.println(two.getFirstName()+ " " +two.getLastName()+ " says:");
				System.out.println(two.Responses.get(i).getResponse());
				
			}
			
		}
		
		
		
		
	}
}
