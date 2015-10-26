package com.peg.android.pbj.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.os.Message;

public abstract class AbstractService{

	private static final int N_THREAD = 10;
	private static ExecutorService executors;
	static private Handler messagHandler;
	
	static{
		executors = Executors.newFixedThreadPool(N_THREAD);		
		messagHandler  = new Handler() {
	    	public void handleMessage(Message message) {
			    super.handleMessage(message);
			    Object[] os = (Object[]) message.obj;
		    	ServiceListener listener = (ServiceListener) os[0];	    			    	
			    switch (message.what) {
				    case 0:{				    	
				    	listener.onComplete(os[1]);
				        break;
				    }
				    case 1:{
				    	ServiceError e = (ServiceError) os[1];
				    	listener.onError(e);
				        break;
				    }
			    }
		    }
	    };    
	}
	
	protected void execute(Runnable r){
		executors.execute(r);		
	}
	
	
	protected void complete(ServiceListener listener,Object o){	
		Message msg = Message.obtain();
        msg.what = 0;
        Object[] os = {listener,o}; 
        msg.obj = os;       
        messagHandler.sendMessage(msg);
	}
	
	protected void fail(ServiceListener listener,ServiceError serviceError){	
		Message msg = Message.obtain();
        msg.what = 1;
        Object[] os = {listener,serviceError}; 
        msg.obj = os;       
        messagHandler.sendMessage(msg);
	}

	
}
