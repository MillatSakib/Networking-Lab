/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HTTPReq;

/**
 *
 * @author Sohan Millat
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Sohan Millat
 */
public class Get {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Chrome");
        int response = con.getResponseCode();
        System.out.println("Response code is: ");
        
        if(response==200){
            BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String store="";
            StringBuffer str = new StringBuffer();
            
            while((store=read.readLine())!=null){
            str.append(store);
            }
            System.out.println("Get Response: "+str.toString());
        }
        
                
    }
    
}
