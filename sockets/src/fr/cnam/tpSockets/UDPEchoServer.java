package fr.cnam.tpSockets;

import java.net.*;
import java.io.*;

public class UDPEchoServer {

  public static void main(String[] args) {

     byte[] buffer = new byte[512];
   int port = Integer.parseInt(args[0]);
   try {
       DatagramSocket theSocket = new DatagramSocket(port);
       while (true) {
           DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
           try {
                 theSocket.receive(incoming);
               String s = new String(incoming.getData(), 0, incoming.getLength());
                 System.out.println(incoming.getAddress() + " at port " + incoming.getPort() + " says " + s);
               DatagramPacket outgoing = new DatagramPacket(incoming.getData(), incoming.getLength(), incoming.getAddress(), incoming.getPort());
                 theSocket.send(outgoing);
           }
           catch (IOException e) {
               System.err.println(e);
           }
       } // end while
   }
   catch (SocketException sex) {
       System.err.println(sex);
   }
 }
}