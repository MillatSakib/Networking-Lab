/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SocketWithThread.CLP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author sakib
 */
public class Client {

    public static void main(String[] args) throws IOException {
        try{Socket s = new Socket("localhost", 5000);
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
            String havetoSend = scn.nextLine();
            output.writeUTF(havetoSend);
            if (havetoSend.equals("Exit")) {
                System.out.println("Close the conection" + s);
                s.close();
                break;
            }

            System.out.println(input.readUTF());

        }
     }catch(EOFException e){
            System.out.println("Server are out of resource.");
     }
        }
}
