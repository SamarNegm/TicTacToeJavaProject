/*
    Server class is the main class that handles the server's logic
 */

package server;

import Handlers.ActiveUsersHandler;
import Handlers.AuthenHandler;
import java.net.ServerSocket;
import java.net.Socket;
    
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 *
 * @author Hossam Khalil
 */

public class Server {
    
    private ServerSocket serverSocket;
    private boolean isServerUp = false;

    private ClientAcceptListener clientAcceptListener;
 
    //Client accept listener inner class
    private class ClientAcceptListener extends Thread {

        private Socket clientSocket;
        private volatile boolean isStayAlive = true;
        
        //constructor 
        ClientAcceptListener()
        {
            this.start();
        }
        
        //Listen to connection request
        @Override
        public void run() {
            while (isStayAlive){
                try {
                    clientSocket = serverSocket.accept();
                     System.out.println("  ....  New client tryig to connect.." +clientSocket.getLocalPort());
                    //New client is accepted
               //     ServerUtils.appendLog("[ClientAcceptListener class]: Client has been accepted. ");
                    
                    //init thread to receive the client
                    new AuthenHandler(clientSocket);
                
                    
                } catch (IOException ex) {
                 //   ServerUtils.appendLog("[ClientAcceptListener class]: Connection dropped (client not accepted). ");
                }
            }
        }
        
        public void close()
        {
            isStayAlive = false;
            this.stop();
        }
    }

    //Start the server
    public void start()
    {
        System.out.println("server stared");
        try {
            //Create the server socket
            
            serverSocket = new ServerSocket(5055);
            clientAcceptListener = new ClientAcceptListener();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        isServerUp = true;
       
    }
    
    //Stop the server
    public void stop()
    {
        //check if the server has been up 
        if (isServerUp)
        {
            try {
                //close the server port
                serverSocket.close();

            } catch (IOException ex) {
             //   ServerUtils.appendLog("[Error]: Server failed to close the port on closing."); 
            }

            //close the listener thread
            clientAcceptListener.close();

 
            isServerUp = false;
        }

    }
}