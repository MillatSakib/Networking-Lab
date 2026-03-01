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
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2000);
        System.out.println("Connected");

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        Scanner sc = new Scanner(System.in);

        String message = "";
        String reply = "";

        while (true) {
            message = sc.nextLine();
            output.writeUTF(message);

            if (message.equals("bye")) {
                break;
            }

            reply = input.readUTF();
            System.out.println("Server says : " + reply);
        }

        socket.close();
    }
}


