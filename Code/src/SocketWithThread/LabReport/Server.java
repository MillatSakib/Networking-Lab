/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketWithThread.LabReport;

import java.io.*;
import java.net.*;
import java.net.ServerSocket;

/**
 *
 * @author sakib
 */
public class Server {

    private static int clientCount = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);

        System.out.println("Waiting for Clients.");

        while (true) {
            clientCount++;
            Socket s = ss.accept();
            System.out.println("A new client is connected" + s);
            System.out.println("Total Client number " + clientCount);
            if (clientCount > 5) {
                System.out.println("Client Connection Rejected");
                Thread cancleClient = new ClientRejecter(s);
                cancleClient.start();
                continue;
            }

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Assigning a new thread for the client.");

            Thread t = new ClientHandler(s, dis, dos);
            t.start();
        }
    }
}

class ClientRejecter extends Thread {

    final Socket soc;

    ClientRejecter(Socket s) {
        this.soc = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("Run Block executed");
            this.soc.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}





class ClientHandler extends Thread {

    final Socket soc;
    final DataInputStream input;
    final DataOutputStream output;

    ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.soc = s;
        this.input = dis;
        this.output = dos;
    }

    @Override
    public void run() {

        String receive;
        String toReturn;
        while (true) {
            try {
                output.writeUTF("Enter a String: ");
                receive = input.readUTF();

                if (receive.equals("Exit")) {

                    System.out.println("The Client wants to " + this.soc + "Exit");

                    this.soc.close();
                    break;
                }
                toReturn = String.valueOf(receive);
                String toUpperCase = toReturn.toUpperCase();
                output.writeUTF(toUpperCase);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}
