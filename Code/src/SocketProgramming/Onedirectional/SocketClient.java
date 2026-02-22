/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketProgramming.Onedirectional;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Sohan Millat
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {

        Socket s = new Socket("localhost", 2300);
        System.out.println("Connected");

        DataOutputStream output = new DataOutputStream(s.getOutputStream());

        Scanner scn = new Scanner(System.in);

        String str = "";

        while (!str.equals("Bye")) {
            str = scn.nextLine();
            output.writeUTF(str);

        }

        s.close();

    }

}
