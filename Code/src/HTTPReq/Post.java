/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HTTPReq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Sohan Millat
 */
public class Post {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("User-Agent", "Chrome");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        String jsonInputString = "{"
                + "\"title\":\"Basic Application Development\","
                + "\"body\":\"Try to hack chrome using java\","
                + "\"userId\":1"
                + "}";

        // Write data
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int response = con.getResponseCode();
        System.out.println("Response code is: " + response);

        if (response == 201) {
            BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String store = "";
            StringBuffer str = new StringBuffer();

            while ((store = read.readLine()) != null) {
                str.append(store);
            }

            System.out.println("Post Response: " + str.toString());
        }
    }
}
