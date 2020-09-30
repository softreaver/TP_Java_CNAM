package fr.cnam.tpSockets;

import java.net.*;
import java.io.*;

public class ClientUDP {

  public static void main(String[] args) {

    String hostname;
    int port ;

    if (args.length < 2) { System.out.println("Usage : UDPClient IP_server Port_server [port_client]");System.exit(1);}
    hostname = args[0];
    port = Integer.parseInt(args[1]);
    try {
      InetAddress server = InetAddress.getByName(hostname);
      BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket theSocket = new DatagramSocket();
      while (true) {
        String theLine = userInput.readLine();
        if (theLine.equals(".")) break;
        byte[] data = (theLine + "\n").getBytes();
        DatagramPacket theOutput = new DatagramPacket(data, data.length, server, port);
        theSocket.send(theOutput);
      }  // end while
    }  // end try
    catch (UnknownHostException uhex) {
      System.err.println(uhex);
    }  
    catch (SocketException sex) {
      System.err.println(sex);
    }
    catch (IOException ioex) {
      System.err.println(ioex);
    }

  }  // end main

}
