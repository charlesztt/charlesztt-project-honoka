package com.prinzkarl.honoka.listener;

import com.prinzkarl.honoka.mail.SendMail;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by charlesztt on 2/28/16.
 */
public class MailListener implements Runnable {
    public void run()
    {
        try {
            listenToMail();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void listenToMail() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(5233);
        while (true)
        {
            Socket socket = serverSocket.accept();
            InputStream inputFromClient = socket.getInputStream();
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputFromClient, writer, StandardCharsets.UTF_8);
            String theString = writer.toString();
            String mailTitle = theString.split("[|]+")[0];
            String mailContent = theString.split("[|]+")[1];
            String mailTarget = theString.split("[|]+")[2];
            SendMail mailTask = new SendMail(mailTitle, mailContent, mailTarget);
            Thread mailThread = new Thread(mailTask);
            mailThread.start();
            mailThread.join();
            socket.close();
        }
    }

    public MailListener()
    {
    }
}
