package fr.cnam.tpSockets;

import java.net.*;
import java.io.*;

public class TCPEchoClient {

 public static void main(String[] args) {

   String hostname = "localhost";
   int port = 1234;

   if (args.length != 2) {
       System.out.println("Usage : EchoClient IP_server port_server");
   }
   else {
       hostname = args[0];
       port = Integer.parseInt(args[1]);
   
       PrintWriter out = null;
       BufferedReader networkIn = null;
       try {
         Socket theSocket = new Socket(hostname, port);
         networkIn = new BufferedReader(
          new InputStreamReader(theSocket.getInputStream()));
         BufferedReader userIn = new BufferedReader(
          new InputStreamReader(System.in));
         out = new PrintWriter(theSocket.getOutputStream());
         System.out.println("Connected to echo server to port "+port);

         while (true) {
           String theLine = userIn.readLine();
           if (theLine.equals(".")) break;
           out.println(theLine);
           out.flush();
//Remarque : un message du client doit être suivi d'un message du serveur, et réciproquement
           System.out.println(networkIn.readLine());
         }
         
       }  // end try
       catch (IOException ex) {
         System.err.println(ex);
       }
       finally {
         try {
           if (networkIn != null) networkIn.close();
           if (out != null) out.close();
         }
         catch (IOException ex) {}
       }
   }
 }  // end main

}  // end EchoClient
