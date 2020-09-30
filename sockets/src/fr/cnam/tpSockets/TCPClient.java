package fr.cnam.tpSockets;

import java.net.*;
import java.io.*;

public class TCPClient {

 public static void main(String[] args) {

   if (args.length != 3) {
       System.out.println("Usage : TCPClient IP_server port_server message");
   }
   else {
       String hostname = args[0];
       int port = Integer.parseInt(args[1]);
   
       PrintWriter out = null;
       BufferedReader networkIn = null;
       try {
         Socket theSocket = new Socket(hostname, port);
         out = new PrintWriter(theSocket.getOutputStream());
         System.out.println("Connected to echo server to port "+port);
         out.println(args[2]);
         out.flush();
       }  
         catch (IOException ex) {
         System.err.println(ex);
       }
       finally {
          if (out != null) out.close();
       }
   }
 }  // end main

}  // end EchoClient
