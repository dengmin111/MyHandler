package com.example.myhandler;

import java.util.concurrent.LinkedBlockingQueue;

public class MyMessageQueue {
    private LinkedBlockingQueue<MyMessage> myMessageQueue = new LinkedBlockingQueue<>();
    private boolean isQuit = false;

    public MyMessage getMessage(){
        if(isQuit){
            return null;
        }
        MyMessage myMessage = null;
        try {
           myMessage = myMessageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myMessage;
    }

    public void putMessage(MyMessage myMessage){
        try {
            isQuit = true;
            myMessageQueue.put(myMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void removeAllMessages(){
        myMessageQueue.clear();
        isQuit = true;
    }
}
