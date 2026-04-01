/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketWithThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author sakib
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 5100);
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        DataInputStream input = new DataInputStream(s.getInputStream());

        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println(input.readUTF());
            String toSend = scn.nextLine();
            output.writeUTF(toSend);

            if (toSend.equals("Exit")) {
                System.out.println("Close the conection" + s);
                s.close();
                break;
            }

            System.out.println(input.readUTF());

        }
    }

}
