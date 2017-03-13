package Project;

import java.util.*;

public class Group {
	public List<Student> members = new ArrayList<Student>();
	public Group (Student one, Student two){
		members.add(one);
		members.add(two);
		
	}
	public Response getResponse(int stu, int pos){
		Student sel = members.get(stu);
		return sel.getResponse(pos);
	}
	public Student selStudent(int stu){
		return members.get(stu);
	}

}
