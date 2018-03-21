import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args) throws IOException {
      
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        while(true){
        	try{
	        	Socket conSocket = serverSocket.accept();
	        	conSocket.setSoTimeout(2000);
	        	BufferedReader inFromClient = new BufferedReader(new InputStreamReader(conSocket.getInputStream()));
	        	DataOutputStream outToClient = new DataOutputStream(conSocket.getOutputStream());
	   

	        	String s  = "HTTP/1.1 200 OK\r\n\r\n";
	        	StringBuilder sB = new StringBuilder();
	        	sB.append(s);
	        	System.out.println("pre loop");
		        while((s = inFromClient.readLine()) != null && s.length() !=0){
		        	System.out.println("loop");
		        	sB.append(s+"\r\n");
		        }
		        outToClient.writeBytes(sB.toString());
		        conSocket.close();
		        inFromClient.close();
		        outToClient.close();
	        	System.out.println("Closed");
			}
	    	
	    	catch(Exception e){
	   			e.fillInStackTrace();
	   			System.out.println("Exception");
	        }
        }
    }
}

