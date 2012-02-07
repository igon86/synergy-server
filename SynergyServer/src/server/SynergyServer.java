

package server;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author andrealottarini
 */
public class SynergyServer {

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
            System.out.println("Unable to read from Stream");
        }

        System.out.println(ridden);

        try {
            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Unable to close socket or stream");
        }

        // scrivo l'hostname del tipo su file
        try {
            // Create file
            FileWriter fstream = new FileWriter("client.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(ridden);

            String local = java.net.InetAddress.getLocalHost().getCanonicalHostName();
            FileWriter fstream2 = new FileWriter("server.txt");
            BufferedWriter out2 = new BufferedWriter(fstream2);
            out2.write(local);
            
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}

