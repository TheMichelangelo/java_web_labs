package Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connection extends Thread{

    private final Socket clientSocket;
    private final Server server;
    private String login = null;
    private OutputStream outputStream;

    public Connection(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException, InterruptedException {
        InputStream inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( (line = reader.readLine()) != null)
        {
            String[] tokens = line.split(" ");
            if (tokens != null && tokens.length > 0)
            {
                String cmd = tokens[0];
                if ("logoff".equals(cmd) || "quit".equalsIgnoreCase(cmd)) {
                    handleLogoff();
                    break;
                } else if ("login".equalsIgnoreCase(cmd)) {
                    handleLogin(outputStream, tokens);
                } else if("msg".equals(cmd)) {
                    handleMessage(tokens);
                }else {
                    String msg = "unknown " + cmd + "\n";
                    outputStream.write(msg.getBytes());
                }
            }
        }

        clientSocket.close();
    }

    private void handleMessage(String[] tokens) throws IOException {
        if(tokens.length<2)
            return;
        String message = login + ": ";
        for(int i=1;i<tokens.length;i++)
            message += tokens[i];
        if(!message.endsWith("\n"))
            message += "\n";
        List<Connection> workerList = server.getWorkerList();
        for(Connection worker : workerList) {
            if (!login.equals(worker.getLogin())) {
                worker.send(message);
            }
        }
    }

    private void handleLogoff() throws IOException {
        List<Connection> workerList = server.getWorkerList();
        String onlineMsg = login+ " left chat\n";
        for(Connection worker : workerList) {
            if (!login.equals(worker.getLogin())) {
                worker.send(onlineMsg);
            }
        }
        clientSocket.close();
    }

    public String getLogin() {
        return login;
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 2) {
            login = tokens[1];
            String msg = "login successfuly\n";
            outputStream.write(msg.getBytes());

            List<Connection> workerList = server.getWorkerList();

            String onlineMsg =login + " join chat"+ "\n";
            for(Connection worker : workerList) {
                if (!login.equals(worker.getLogin())) {
                    worker.send(onlineMsg);
                }
            }
        }
        else
        {
            String msg = "login error";
            outputStream.write(msg.getBytes());
        }
    }

    private void send(String msg) throws IOException {
        if (login != null) {
            outputStream.write(msg.getBytes());
        }
    }
}
