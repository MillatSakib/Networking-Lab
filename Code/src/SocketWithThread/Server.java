/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketWithThread;

import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sakib
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5100);

        System.out.println("Waiting for Clients.");

        while (true) {
            Socket s = ss.accept();
            System.out.println("A new client is connected" + s);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Assigning a new thread for the client.");

            Thread t = new ClientHandler(s, dis, dos);
            t.start();
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

    public void run() {
        DateFormat forDate = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat forTime = new SimpleDateFormat("hh/mm/ss");

        String receive;
        String toReturn;
        while (true) {
            try {
                output.writeUTF("What do you want? [Date/Time]");
                receive = input.readUTF();

                if (receive.equals("Exit")) {

                    System.out.println("The Client wants to " + this.soc + "Exit");

                    this.soc.close();
                    break;
                }

                Date d = new Date();

                switch (receive) {
                    case "Date":
                        toReturn = forDate.format(d);
                        output.writeUTF(toReturn);
                        break;
                    case "Time":
                        toReturn = forTime.format(d);
                        output.writeUTF(toReturn);
                        break;
                    default:
                        output.writeUTF("Invalid input");
                        break;
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}
