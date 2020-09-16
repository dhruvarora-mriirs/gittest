package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import javax.json.Json;
import javax.json.JsonObject;

public class ClientThread extends Thread {
private BufferedReader reader;
private Client client;

public ClientThread(Socket socket, Client client)throws IOException{
	this.reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.client=client;
    
}
public void run() {
	while(true) {
		JsonObject jsonObject= Json.createReader(reader).readObject();
		if(jsonObject.containsKey("p")) {
			client.setOtherPartyName(jsonObject.getString("name"));
			EllipticCurve ec= new EllipticCurve(
					new BigInteger(jsonObject.getString("a")), 
					new BigInteger(jsonObject.getString("b")), 
							new BigInteger(jsonObject.getString("p")), 
									new BigInteger(jsonObject.getString("n")), 
											new BigInteger(jsonObject.getString("h")));
			
			client.setOtherPartyEllipticCurve(ec);
			client.setOtherPartyGeneratorPoint(new Point(new BigInteger(jsonObject.getString("GeneratorX")),new BigInteger(jsonObject.getString("GeneratorY"))));

		client.setOtherPartyPublicKeyPoint(new Point(new BigInteger(jsonObject.getString("PublicKeyX")),new BigInteger(jsonObject.getString("PublicKeyY"))));

		System.out.println(client.getOtherPartyName()+"s domain param "+ ec);
		System.out.println("generator point A "+ client.getOtherPartyGeneratorPoint());
		System.out.println(client.getOtherPartyName()+"s public key B ="+ client.getOtherPartyPublicKeyPoint());
		if(client.getPrompt()!=null)System.out.println(client.getPrompt());
		}else if(jsonObject.containsKey("x")) {
			try {
				System.out.println("recived signed message ==>"+jsonObject.toString());
				String xHashStringTokens=obtainMessageHash(jsonObject).trim();
				BigInteger xHashTokens= new BigInteger(xHashStringTokens);
				recieveMessage(xHashTokens, jsonObject);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
private String obtainMessageHash(JsonObject jsonObject) throws NoSuchAlgorithmException{
	String xHash =null;
	if(!Client.ASCII_FLAG) {
		xHash=Client.hashMessage(jsonObject.getString("x"), "SHA-256");
		System.out.println(client.getName()+" h(x):"+xHash);
	}
	else {
		xHash= jsonObject.getString("x");
		System.out.println(client.getName()+" h(x):"+xHash);
	}
	return xHash;
}
private void recieveMessage(BigInteger hashTokens, JsonObject jsonObject) throws NoSuchAlgorithmException{
	boolean flag=true;
	BigInteger r =new BigInteger(jsonObject.getString("r").trim());
	BigInteger s =new BigInteger(jsonObject.getString("s").trim());
	Point pointP=null;
	BigInteger w=null,u1=null,u2=null;
	try {
		w=s.modInverse(client.getOtherPartyEllipticCurve().getN());
		u1=w.multiply(hashTokens).mod(client.getOtherPartyEllipticCurve().getN());
		u2=w.multiply(r).mod(client.getOtherPartyEllipticCurve().getN());
		pointP= client.getOtherPartyEllipticCurve().add(client.getOtherPartyEllipticCurve().fastMultiply(u1, client.getOtherPartyGeneratorPoint()),
				client.getOtherPartyEllipticCurve().fastMultiply(u2, client.getOtherPartyPublicKeyPoint()));
	
	}catch (Exception e) {
		// TODO: handle exception
		flag=false;
	}
	if(flag) {
		displayCalculations(w, u1, u2);
		System.out.println(client.getName()+"calculate P=u1*A+u2*B="+pointP+"& v=Px="+pointP.getX());
		validateMessage(pointP.getX(), r, jsonObject.getString("x"));
	}else
	{System.out.println(client.getName()+"message was not signed with "+client.getOtherPartyName()+"private key");
		
	}
	
}
private void displayCalculations(BigInteger w, BigInteger u1,BigInteger u2) {
	System.out.println(client.getName()+"calculate w<congryent> s^(-1)mod n="+w);
	String Sharh="SHA(X)";
	if(Client.ASCII_FLAG)Sharh="h(x)";
	System.out.println(client.getName()+"calculate u1 congruent w"+Sharh+"mod n="+u1);
	System.out.println(client.getName()+"calculate u2 congruent w"+Sharh+"mod n="+u2);

}
private void validateMessage(BigInteger v , BigInteger r , String string)
{
	boolean flag=true;
	if(!v.equals(r)) {
		System.out.println(client.getName()+"message not validated "+client.getOtherPartyName()+"private key");
		flag=false;
		
	}
	if(flag) {
		System.out.println(client.getName()+"validate  message was valid "+client.getOtherPartyName()+"private key");
		System.out.println(client.getOtherPartyName()+" "+string);
	}
	
	}

}
