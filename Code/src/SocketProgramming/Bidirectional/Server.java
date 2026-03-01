/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketProgramming.Bidirectional;

/**
 *
 * @author Sohan Millat
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println("Waiting for the clients...");

        Socket socket = serverSocket.accept();
        System.out.println("Client request is accepted");

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        String message = "";
        String reply = "";

        while (true) {
            message = input.readUTF();
            System.out.println("Client says : " + message);

            if (message.equals("bye")) {
                break;
            }

            reply = sc.nextLine();
            output.writeUTF(reply);
        }

        socket.close();
        serverSocket.close();
    }
}