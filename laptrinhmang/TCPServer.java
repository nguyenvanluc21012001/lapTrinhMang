/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmang;

/**
 *
 * @author h
 */
import java.io.*;
import java.net.*;

public class TCPServer {

   public static void main(String argv[]) throws Exception {
      if (argv.length != 2) {
         System.out.println("Usage: java TCPServer <port number> <greeting file> <output file>");
         System.exit(1);
      }
      int port = Integer.parseInt(argv[0]);
      String greetingFile = argv[1];
      String outputFile = argv[2];
      
      String greeting = readFromFile(greetingFile);

      ServerSocket welcomeSocket = new ServerSocket(port);

      while (true) {
         Socket connectionSocket = welcomeSocket.accept();
         System.out.println("Client connected: " + connectionSocket.getInetAddress().getHostName());
         
         PrintWriter out = new PrintWriter(connectionSocket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

         out.println(greeting);
         
         String inputLine;
         BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
         while ((inputLine = in.readLine()) != null) {
            writer.write(inputLine);
            writer.newLine();
         }
         writer.close();
         in.close();
         out.close();
         connectionSocket.close();
      }
   }
   
   private static String readFromFile(String filename) throws IOException {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      StringBuilder stringBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
         stringBuilder.append(line);
         stringBuilder.append(System.lineSeparator());
      }
      reader.close();
      return stringBuilder.toString();
   }
}

