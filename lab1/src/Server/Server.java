package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

    ServerSocket serverSocket;
    private ArrayList<Connection> workerList = new ArrayList<>();

    public Server() {
        try {
            serverSocket = new ServerSocket(1001);
            System.out.println(serverSocket.toString());
        } catch (IOException e) {
            fail(e, "Can't start server");
        }
        System.out.println("Server.Server is up");
        this.start();
    }

    public static void main(String args[]) {
        new Server();
    }

    public List<Connection> getWorkerList() {
        return workerList;
    }

    private void fail(IOException e, String message) {
        System.out.println(message + ". " + e.getMessage());
    }

    public void run() {
        try {
            while (true) {
                System.out.println("About to accept client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                Connection worker = new Connection(this, clientSocket);
                workerList.add(worker);
                worker.start();
            }
        } catch (IOException e) {
            fail(e, "No listening");
        }
    }
}
