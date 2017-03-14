package Project;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServer {
	//keep list of all serverhandlers to broadcast messages to everyone
	ArrayList<ChatServerHandler> list = new ArrayList<ChatServerHandler>();	
	ArrayList<Thread> threads = new ArrayList<Thread>();
	int ctr=0;
	
	public ChatServer() throws IOException{
		int port =8090;
		
		
		ServerSocket ss = new ServerSocket(port);
		ss.setReuseAddress(true);
		try{
			while(true){
				Socket s = ss.accept();
				ChatServerHandler sh = new ChatServerHandler(s, this, ctr);
				list.add(sh);
				threads.add(new Thread(sh));
				threads.get(ctr).start();
				ctr++;
				
			}
		}
		finally{
			ss.close();
		}
		
	}

	public static void main(String[] args) throws IOException{
		new ChatServer();

	}
	
	public void broadcastMessage(String str){
		
		for(ChatServerHandler h : list){
			h.sendMessage(str);
		}
		
	}
	public boolean uniqueNameCheck(ChatServerHandler sh){
		for(ChatServerHandler h : list){
			if (sh.hNumber!=h.hNumber){
				if (h.getUsername().equals(sh.getUsername())){
					System.out.println("Not unique");
					sh.sendMessage("DENY\n");
					return false;
					
				}
			}
		}
		return true;
	}
	

}
