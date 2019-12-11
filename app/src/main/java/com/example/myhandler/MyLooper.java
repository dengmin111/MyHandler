package com.example.myhandler;

public class MyLooper {
    private MyMessageQueue myMessageQueue;
    private static ThreadLocal<MyLooper> threadLocal = new ThreadLocal<>();

    public MyLooper(MyMessageQueue myMessageQueue){
        this.myMessageQueue = myMessageQueue;
        threadLocal.set(this);
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
            return new MyLooper(new MyMessageQueue());
        }
        return threadLocal.get();
    }

    public MyMessageQueue getMyMessageQueue(){
        return myMessageQueue;
    }
}
