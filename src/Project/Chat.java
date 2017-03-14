package Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.*;


public class Chat extends JFrame{
	
	private static final long serialVersionUID = -5394960899104203569L;
	public static List<Group> groups = new ArrayList<Group>();
	public static List<Student> students = new ArrayList<Student>();
	private JFrame frame = new JFrame("Chat");
	private JTextArea textArea=new JTextArea(5,30);
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JButton send =new JButton("Send");
	private JTextArea reply = new JTextArea(5,30);
	private static InetAddress ipAddress;
	private static String ip; 
	private Socket s;
	private static String username;
	private PrintWriter out;
	
	public Chat(){
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void initialize() throws IOException, UnknownHostException, ConnectException{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		textArea.setText(Conv());
		frame.add(scrollPane, BorderLayout.NORTH);
		frame.add(reply, BorderLayout.CENTER);
		int port = 8090;
		ipAddress = InetAddress.getByName(ip);
		//supposed to create server if unavailable: occasionally works if changes are saved
		try{
			s = new Socket(ipAddress, port);
		}catch (ConnectException e){
			e.printStackTrace();
			new ChatServer();
			s= new Socket(ipAddress, port);
			
			
			
		}
		
		new Thread(new Reader(s.getInputStream())).start();
		out = new PrintWriter(s.getOutputStream(), true);
		out.println(username);
		out.flush();
		
		reply.addKeyListener(new KeyListener(){
			@Override
            public void keyTyped(KeyEvent e) {
            }
			 @Override
	            public void keyPressed(KeyEvent e) {
	                if ((e.getKeyCode() == KeyEvent.VK_ENTER) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
	                	addReply();
	                }
	            }
			 @Override
	            public void keyReleased(KeyEvent e) {
	            }
		});
		
		frame.add(send, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		
		
		
		send.addActionListener((e) -> {
			addReply();
		});
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
	}
	
	public static void main(String[] args){
		
		ip  = JOptionPane.showInputDialog("Enter the IP Address");
		
		username= JOptionPane.showInputDialog("Enter your name");
		
		students.add(new Student("Brieanna", "Miller", 1) );
		students.add(new Student("Nathan", "Borup", 1));
		students.add(new Student("Ethan", "Brown", 1) );
		students.add(new Student("Tyler", "Toponce", 1) );
		students.add(new Student("Kendra", "Koester", 1) );
		students.add(new Student("Cody", "May", 9999) );
		students.add(new Student("Michael", "Cullimore", 1) );
		students.add(new Student("Rizwan", "Mohammad", 1) );
		students.add(new Student("Lauren", "Ribiero", 1) );
		
		students.add(new Student("Trevor", "Marsh", 1) );
		
		Collections.sort(students, new NameComp());
		
		
		groups.add(new Group (students.get(0), students.get(1)));
		groups.add(new Group (students.get(2), students.get(3)));
		groups.add(new Group (students.get(4), students.get(5)));
		groups.add(new Group (students.get(6), students.get(7)));
		groups.add(new Group (students.get(8), students.get(9)));
		int counter=0;
		for(Student student : students){
			if(counter==0){
				counter++;
				student.responses.add(new Response("Hello"));
				student.responses.add(new Response("What's up?"));
				student.responses.add(new Response("Everything is awful"));
				student.responses.add(new Response("I agree."));
				student.responses.add(new Response("I am a dragon, grrr"));
				}
			else{
				counter=0;
				student.responses.add(new Response("Hey"));
				student.responses.add(new Response("Trying to live my life"));
				student.responses.add(new Response("What?"));
				student.responses.add(new Response("I'm not following..."));
				student.responses.add(new Response("Please leave"));
			}
		}
		new Chat();
		
	}
	
		private String Conv(){
			String conv="";
			for(Group group : groups){
				Student one= group.selStudent(0);
				Student two= group.selStudent(1);
				conv=(conv +"Conversation between " + one.getFirstName()+ " " +one.getLastName()+ " and " + two.getFirstName()+ " " +two.getLastName()+ ":\n");
				for(int i=0; i<5; i++){
					conv=(conv +one.getFirstName()+ " " +one.getLastName()+ "\n");
					conv=(conv +one.printResponse(i)+"\n");
					conv=(conv +two.getFirstName()+ " " +two.getLastName()+ " says:\n");
					conv=(conv +two.printResponse(i)+"\n");
				}
			}
			return conv;
		}
		
		private void addReply(){
			out.println(reply.getText());
			out.flush();
			reply.setText("");
		}
		
		
		private class Reader implements Runnable {
			private InputStream input;
			private BufferedReader br;
			
			public Reader(InputStream is) {
				input = is;
				br = new BufferedReader(new InputStreamReader(input));
			}
			
			
			public void run() {
				String str = "f";
				
				try {
					while ((str = br.readLine()) != null) {
						//System.out.print(character);
						
						textArea.append(str+"\n");
					
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

class NameComp implements Comparator<Student>{
	@Override
	public int compare(Student s1, Student s2) {
		String name1=s1.getLastName();
		String name2=s2.getLastName();
		return name1.compareTo(name2);
        
    }

	
}
