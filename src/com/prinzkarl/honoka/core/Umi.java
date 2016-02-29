package com.prinzkarl.honoka.core;


import com.prinzkarl.honoka.listener.*;

/**
 * Thread class handling ports
 *
 * Created by charlesztt on 2/28/16.
 */
public class Umi implements Runnable {
    public void run(){
        listening();
    }

    private void listening(){
        //Listener for mail modules
        MailListener mailListener = new MailListener();
        Thread mailListenerThread = new Thread(mailListener);
        mailListenerThread.start();
    }

    public Umi(){
    }
}
