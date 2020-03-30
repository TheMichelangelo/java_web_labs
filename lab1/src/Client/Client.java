package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private InputStream serverIn;
    private OutputStream serverOut;
    private BufferedReader bufferedIn;

    public Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Client client = new Client("localhost", 1001);

        if (!client.connect()) {
            System.err.println("Connect failed.");
        } else {
            System.out.println("Connect successful");
            System.out.println("Enter your name");

            String login = in.nextLine();
            if (client.login(login)) {
                System.out.println("Login successful");
            } else {
                System.err.println("Login failed");
            }
        }
        String message = in.nextLine();
        while (!message.equals("exit")) {
            client.send_message(message);
            message = in.nextLine();
        }
        client.logoff();
    }

    private void send_message(String msgBody) throws IOException {
        String cmd = "msg " + msgBody + "\n";
        serverOut.write(cmd.getBytes());
    }

    private boolean login(String login) throws IOException {
        String cmd = "login " + login + "\n";
        serverOut.write(cmd.getBytes());

        String response = bufferedIn.readLine();
        if ("login successfuly".equalsIgnoreCase(response)) {
            startMessageReader();
            return true;
        } else {
            return false;
        }
    }

    private void logoff() throws IOException {
        String cmd = "logoff\n";
        serverOut.write(cmd.getBytes());
    }

    private void startMessageReader() {
        Thread t = new Thread() {
            @Override
            public void run() {
                readMessageLoop();
            }
        };
        t.start();
    }

    private void readMessageLoop() {
        try {
            String line;
            while ((line = bufferedIn.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
