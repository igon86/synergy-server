/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lottarin
 */
public class Main {

    private static final int port = 24800;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ObjectInputStream in = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
            System.exit(-1);
        }


        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: " + port);
            System.exit(-1);
        }

        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
        }

        String ridden = "";
        try {
            ridden = in.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(ridden);

        try {
            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // scrivo l'hostname del tipo su file
        try {
            // Create file
            FileWriter fstream = new FileWriter("hostname.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(ridden);
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
