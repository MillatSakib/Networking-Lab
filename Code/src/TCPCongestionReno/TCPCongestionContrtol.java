/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPCongestionReno;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author sakib
 */
public class TCPCongestionContrtol {
    private int cwnd;
    private int ssthresh;
    private int rtt;
    private boolean congestion;
   
   
    public TCPCongestionContrtol(int s){
        cwnd = 1;
        ssthresh=s;
        rtt = 0;
        congestion = false;
    }
   
    public void run(){
        System.out.println("Connected to the server.. ...");
        System.out.println("Enter the length of your data: ");
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();
        int SeqNo=0;
        System.out.println("Started...");
       
        while(SeqNo < len){
            this.rtt++;
            System.out.println("RTT Value: "+this.rtt);
            System.out.println("------------------------");
           
            System.out.println("Previous congestion window, (cwnd)"+cwnd);
            System.out.println("Updated ssthresh: "+ssthresh);
            if(!congestion){
                if(cwnd < ssthresh){
                cwnd = cwnd*2;
                    System.out.println("---- Slow Start Phase ----");
                }
                else if(cwnd >= ssthresh){
                    cwnd= cwnd+1;
                    System.out.println("---- Congestion Avoidance ----");
                   
                   
                }
            }
           
            System.out.println("New window size: "+cwnd);
            sendPacket(SeqNo);
            SeqNo = SeqNo +cwnd;
           
        }
       
        System.out.println("()Data is completed "+this.rtt);
       
       
       
    }
   
   
    public void sendPacket(int SeqNo){
        System.out.println("Data send from "+(SeqNo+1)+"to"+(SeqNo+cwnd));
        if(!ACK()){
            congestion=true;
            if(timeout()){
                System.out.println("Loss detected by timeout.");
                ssthresh = cwnd/2;
                if(ssthresh == 0)ssthresh=1;
                cwnd = 1;
                retransmit();
            }else{
                System.out.println("Loss detecte by 3dup ack");
               
                ssthresh = cwnd/2;
                if(ssthresh == 0){
                ssthresh = 1;
                }
               
                cwnd = ssthresh +3;
               
                retransmit();
            }
        }else{
        congestion=false;
        }
       
    }
   
    public void retransmit(){
    congestion = false;
        System.out.println("Retransmit the lost packet");
    }
   
    public boolean ACK(){
    Random ack = new Random();
    return  ack.nextBoolean();
   
    }
    public boolean timeout(){
    Random ack = new Random();
    return  ack.nextBoolean();
   
    }
   
    public static void main(String[] Args){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please input the initial ssthresh value: ");
        int ssthres = scn.nextInt();
       
        TCPCongestionContrtol reno = new TCPCongestionContrtol(ssthres);
        reno.run();
    }
}
