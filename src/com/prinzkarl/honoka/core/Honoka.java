package com.prinzkarl.honoka.core;

import com.prinzkarl.honoka.mail.SendMail;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by charlesztt on 2/28/16.
 */
public class Honoka {
    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        Umi umi = new Umi();
        Thread umiThread = new Thread(umi);
        umiThread.start();

        while(true) {
            System.out.print(">> ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if (str.equals("exit")){
                System.out.println("Exiting the system...");
                System.exit(0);
            }
            if (str.indexOf("send://")!=-1)
            {
                String mailCommand = str.substring("send://".length());
                String mailTitle = mailCommand.split("[|]+")[0];
                String mailContent = mailCommand.split("[|]+")[1];
                String mailTarget = mailCommand.split("[|]+")[2];
                SendMail mailTask = new SendMail(mailTitle, mailContent, mailTarget);
                Thread mailThread = new Thread(mailTask);
                mailThread.start();
                mailThread.join();
            }
        }
    }
}
