import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* 
 * Main server class. This class includes main(), and is the class that listens
 * for incoming connections and starts ServerThreads to handle those connections
 *
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AtomicInteger numThreads = new AtomicInteger(0);
		// the list of threads is kept in a linked list
		ArrayList<Thread> list = new ArrayList<Thread>();
		
		try {
			// listen for incoming connections on port 15432
			ServerSocket socket = new ServerSocket(15432);
			System.out.println("Server listening on port 15432");

			// loop (forever) until program is stopped
			while(true) {
				// accept a new connection
				Socket client = socket.accept();
				// start a new ServerThread to handle the connection and send
				// output to the client
				Thread thrd = new Thread(new ServerThread(client));
				list.add(thrd);
				thrd.start();
				numThreads.incrementAndGet();
				System.out.println("Thread " + numThreads.get() + " started.");

			}
		}
		catch (IOException ioe){
			ioe.printStackTrace();
		}
		catch (Exception ioe){
			ioe.printStackTrace();
		}
	}
}

