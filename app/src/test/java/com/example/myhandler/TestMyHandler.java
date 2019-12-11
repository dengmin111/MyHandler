package com.example.myhandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMyHandler {
    public MyHandler myHandler;
    public String res;
    public MyHandler resTarget;
    public static volatile int i = 0;

    @Before
    public void setUp() {
        myHandler = new MyHandler(new MyHandler.CallBack() {
            @Override
            public void handleMessage(MyMessage mmsg) {
                res = mmsg.getMessage();
                resTarget = mmsg.getTarget();
                System.out.println(Thread.currentThread().getName());
                myHandler.removeAllMessages();
            }
        });
    }

    @After
    public void tearDown() {
        if (myHandler != null) {
            myHandler.removeAllMessages();
            myHandler = null;
        }
    }

    @Test
    public void test_InterThreadCommunication() {
        final String inputMessage = "from new thread";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                myHandler.sendMessage(MyMessage.getMyMessage().setMessage(inputMessage));
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();
        myHandler.getMyLooper().loop();
        Assert.assertEquals(inputMessage, res);
        Assert.assertNotNull(resTarget);
        Assert.assertEquals(myHandler, resTarget);
    }

    @Test
    public void test_MessageQueueIsOnlyOne() {
        MyHandler testHandler = new MyHandler(null);
        Assert.assertEquals(myHandler.getMyLooper().getMyMessageQueue(), testHandler.getMyLooper().getMyMessageQueue());
    }
}
