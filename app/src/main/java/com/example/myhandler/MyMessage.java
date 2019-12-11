package com.example.myhandler;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MyMessage {
    private static final int MAX_SIZE = 10;
    private static ConcurrentLinkedQueue<MyMessage> messagePool = new ConcurrentLinkedQueue<>();
    private static volatile int size = 0;


    private String message;
    private MyHandler target;

    private MyMessage() {
    }

    public synchronized static MyMessage getMyMessage() {
        if (!messagePool.isEmpty()) {
            size--;
            return messagePool.poll();
        }
        return new MyMessage();
    }

    public synchronized static void recycle(MyMessage myMessage){
        if(size <=MAX_SIZE){
            myMessage.target = null;
            myMessage.message = null;
            messagePool.add(myMessage);
            size++;
        }
        myMessage = null;
    }


    public String getMessage() {
        return message;
    }

    public MyMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public MyHandler getTarget() {
        return target;
    }

    public void setTarget(MyHandler target) {
        this.target = target;
    }
}
