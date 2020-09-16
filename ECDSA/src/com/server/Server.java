package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class Server {
	static final int PORT= 4444;
	private ServerSocket serverSocket;
	private Set<ServerThread> serverThreads = new HashSet<ServerThread>();
	public static void main(String[] args) throws IOException {
		Server server =new Server();
		server.serverSocket= new ServerSocket(PORT);
		System.out.println("Eavesdropping on all Commuication");
		while(true) {
			ServerThread serverThread = new ServerThread(server.serverSocket.accept(), server);
			server.serverThreads.add(serverThread);
			serverThread.start();
		}
		
	
	}
	public Set<ServerThread> getServerThreads() {
		return serverThreads;
	}
	void forwardMessage(String message, ServerThread originatingSeverThread) {
		serverThreads.forEach(serverThread -> {
			if(serverThread != originatingSeverThread)serverThread.forwardMessage(message);
		});
	}
	

}
