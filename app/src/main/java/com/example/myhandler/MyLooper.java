package com.example.myhandler;

public class MyLooper {
    private static MyMessageQueue myMessageQueue = new MyMessageQueue();
    private static ThreadLocal<MyLooper> threadLocal = new ThreadLocal<>();

    private MyLooper(){
    }

    public void loop(){
        MyMessage myMessage = null;
        while (true){
            myMessage = myMessageQueue.getMessage();
            if(myMessage == null){
                return;
            }
            myMessage.getTarget().dispatchMessage(myMessage);
            MyMessage.recycle(myMessage);
        }
    }

    public static MyLooper getMyLooper(){
        if(threadLocal.get() == null){
            threadLocal.set(new MyLooper());
        }
        return threadLocal.get();
    }

    public MyMessageQueue getMyMessageQueue(){
        return myMessageQueue;
    }
}
