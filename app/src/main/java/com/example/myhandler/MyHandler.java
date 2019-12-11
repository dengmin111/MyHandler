package com.example.myhandler;

public class MyHandler {
    private MyLooper myLooper;
    private MyMessageQueue myMessageQueue;
    private CallBack mCallBack;
    public MyHandler(){
        myLooper = MyLooper.getMyLooper();
        myMessageQueue = myLooper.getMyMessageQueue();
    }
    public MyHandler (CallBack mCallBack){
        this();
        this.mCallBack = mCallBack;
    }
    public MyHandler(MyLooper looper, CallBack mCallBack){
        this.mCallBack = mCallBack;
        myLooper = looper;
        myMessageQueue = looper.getMyMessageQueue();
    }

    public void sendMessage(MyMessage message){
        message.setTarget(this);
        myMessageQueue.putMessage(message);
    }

    public void dispatchMessage(MyMessage mmsg){
        if(mCallBack != null){
            mCallBack.handleMessage(mmsg);
        }
        handleMessage(mmsg);
    }

    public void removeAllMessages(){
        myMessageQueue.removeAllMessages();
    }

    public MyLooper getMyLooper(){
        return myLooper;
    }
    public void handleMessage(MyMessage mmsg){ }

    interface CallBack{
        void handleMessage(MyMessage mmsg);
    }
}
