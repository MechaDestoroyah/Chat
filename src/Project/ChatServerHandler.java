package Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatServerHandler implements Runnable{

	private BufferedReader br;
	private PrintWriter pw;
	private Socket s;
	public int hNumber;
	private ChatServer cs;
	private String username;
	//added chatserver to constructor for access to all other handlers

	public ChatServerHandler(Socket s, ChatServer cs, int hNumber) throws IOException {
		this.s = s;
		this.cs = cs;
		this.hNumber= hNumber;
		this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.pw = new PrintWriter(s.getOutputStream());
	}

	@Override
	public void run() {
		String str = "f";
		boolean init= true;
		try {
			while ((str= br.readLine()) != null) {
				if(init==false){
				System.out.print(str);
				cs.broadcastMessage(str);
				}else{
					//only ends the thread, probably should terminate chat client too
					username=str;
					if(cs.uniqueNameCheck(this)==false){
						s.close();
						return;
					}else{
					cs.broadcastMessage(username + " has joined");
					init=false;
					}
				}
				
				
		}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getUsername(){
		return username;
	}
	//will receive character back from broadcast in chat server
	public void sendMessage(String str){
		pw.println(str);
		pw.flush();
	}
}