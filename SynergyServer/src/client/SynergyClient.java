
package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 *
 * @author andrealottarini
 */
public class SynergyClient {

    private static final int port = 24800;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {

	if(args.length != 1 ) {
			System.out.println("Incorrect Number of parameters");
			System.exit(-1);
	}

        InetAddress localhost = java.net.InetAddress.getLocalHost();
        String local = localhost.getCanonicalHostName();
        System.out.println("This hostanme is: "+local);

        Socket s = new Socket();
        System.out.println("Socket creata");

        InetAddress server = InetAddress.getByName(args[0]);

        SocketAddress sockaddr = new InetSocketAddress(server, port);
        try {
            //timeout in millisecondi
            s.connect(sockaddr, 2000);

        } catch (IOException ex) {
            System.out.println("Server down: muoro");
            System.exit(-1);
        }
        System.out.println("Connesso");

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Unable to open OOstream");
            System.exit(1);
        }

        try {
            out.writeUTF(local);
        } catch (IOException ex) {
            System.out.println("Unable to write on the stream");
            System.exit(1);
        }

        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("Unable to close OOstream");
            System.exit(-1);
        }

        try {
            s.close();
        } catch (IOException ex) {
            System.out.println("Unable to close Socket");
            System.exit(-1);
        }
    }


}

