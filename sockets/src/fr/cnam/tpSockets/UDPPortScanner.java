package fr.cnam.tpSockets;

import java.net.*;

public class UDPPortScanner {

 public static void main(String[] args) {
   
   for (int port = 1024; port <= 65535; port++) {
     try {
       // tentative de mise en ecoute d'un socket UDP sur le port
      DatagramSocket server = new DatagramSocket(port);
       server.close();
     }
     catch (SocketException ex) {
       //Echec : un serveur UDP existe déjà sur ce port
       System.out.println("Serveur UDP déjà en écoute sur le port " + port + ".");
     }
   }
 }
}