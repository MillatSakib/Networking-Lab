/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketProgramming.Onedirectional;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Sohan Millat
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(2300);

        System.out.println("Waiting for the client...");

        Socket s = ss.accept();

        System.out.println("Client Reqeust is accepted");
        DataInputStream input = new DataInputStream(s.getInputStream());
        String str = "";
        
        while(!str.equals("Bye")){
        
        str=input.readUTF();
        
            System.out.println("Client Says: "+str);
        }
        s.close();
    }
}
