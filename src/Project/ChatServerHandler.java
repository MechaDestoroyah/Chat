package Project;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;

public class ChatServerHandler implements Runnable{

	private Reader r;
	private PrintWriter w;
	@SuppressWarnings("unused")
	private Socket s;
	private ChatServer cs;
	//added chatserver to constructor for access to all other handlers

	public ChatServerHandler(Socket s, ChatServer cs) throws IOException {
		this.s = s;
		this.cs = cs;
		System.out.print("ACK\n");
		this.r = new InputStreamReader(s.getInputStream());
		this.w = new PrintWriter(s.getOutputStream());
	}

	@Override
	public void run() {
		char character = 0;
		try {
			while ((character = (char) r.read()) != -1) {
				System.out.print(character);
				cs.broadcastMessage(character);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//will receive character back from broadcast in chat server
	public void sendMessage(char character){
		w.write(character);
		w.flush();
	}
}