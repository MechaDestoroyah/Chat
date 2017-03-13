package Project;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServer {
	//keep list of all serverhandlers to broadcast messages to everyone
	ArrayList<ChatServerHandler> list = new ArrayList<ChatServerHandler>();	
	ArrayList<String> users = new ArrayList<String>();
	
	
	public ChatServer() throws IOException{
		int port =8090;
		
		
		ServerSocket ss = new ServerSocket(port);
		ss.setReuseAddress(true);
		try{
			while(true){
				Socket s = ss.accept();
				
				ChatServerHandler sh = new ChatServerHandler(s, this);
				
				new Thread(sh).start();
				list.add(sh);
			}
		}
		finally{
			ss.close();
		}
		
	}

	public static void main(String[] args) throws IOException{
		new ChatServer();

	}
	
	public void broadcastMessage(char character){
		
		for(ChatServerHandler h : list){
			h.sendMessage(character);
		}
		
	}
	

}
