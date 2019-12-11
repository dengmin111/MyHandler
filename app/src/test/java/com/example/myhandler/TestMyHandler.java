package com.example.myhandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMyHandler {
    public MyHandler myHandler;
    public String res = "";
    public MyHandler resTarget;
    @Before
    public void setUp(){
        myHandler = new MyHandler(new MyHandler.CallBack() {
            @Override
            public void handleMessage(MyMessage mmsg) {
                res = mmsg.getMessage();
                resTarget = mmsg.getTarget();
                myHandler.removeAllMessages();
            }
        });
    }

    @After
    public void tearDown(){
        if(myHandler != null){
            myHandler.removeAllMessages();
            myHandler = null;
        }
    }

    @Test
    public void test(){
        final String inputMessage = "from new thread";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                myHandler.sendMessage(MyMessage.getMyMessage().setMessage(inputMessage));
            }
        });
        thread.start();
        myHandler.getMyLooper().loop();
        Assert.assertEquals(inputMessage,res);
        Assert.assertNotNull(resTarget);
        Assert.assertEquals(myHandler,resTarget);
    }
}
