/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylittleconcert_clienttcp_alexis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vesprada
 */
public class MyLittleConcert_ClientTCP_Alexis {

    /**
     * @param args the command line arguments
     */
    private static final int PORT = 8888;
    private static final String MACHINE = "localhost";

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Socket clientSocket = new Socket();
            InetSocketAddress sockAddr = new InetSocketAddress(MACHINE, PORT);
            clientSocket.connect(sockAddr);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            String answer;
            System.out.print("WRITE THE CONCERT NAME: ");
            answer = sc.nextLine() + ",";
            System.out.print("WRITE YOUR NAME: ");
            answer = answer + sc.nextLine();
            System.out.print("CHOOSE TYPE Of TICKET:VIP OR NORMAL: ");
            String type = sc.nextLine().toLowerCase();
            while (!type.equals("vip") && !type.equals("normal")) {
                System.out.print("CHOOSE TYPE Of TICKET:VIP OR NORMAL: ");
                type = sc.nextLine().toLowerCase();
            }
            answer = answer + "," + type;
            bw.write(answer);
            bw.newLine();
            bw.flush();
            System.out.println("DATA SENT TO SERVER AND HERE IS YOUT TICKET. PLEASE GO TO THE BANCK TO PAY");
            System.out.println("*************************************************************************");
            System.out.println(br.readLine());
            System.out.println("*************************************************************************");
            br.close();
            bw.close();
            clientSocket.close();
            System.out.println("CLIENT FINISHED CONNECTION CLOSED");

        } catch (IOException ex) {
            Logger.getLogger(MyLittleConcert_ClientTCP_Alexis.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
