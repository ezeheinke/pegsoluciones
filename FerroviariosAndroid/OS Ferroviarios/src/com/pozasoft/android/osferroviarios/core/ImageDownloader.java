package com.pozasoft.android.osferroviarios.core;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ImageDownloader extends AbstractService {
	
	private static final int N_THREAD = 2;
	private static final ExecutorService executors;
	private static final Handler handler;
	
	static{
		executors = Executors.newFixedThreadPool(N_THREAD);
		handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				ImageListener listener = (ImageListener)(((Object[])message.obj)[0]);
				Bitmap bitmap = (Bitmap)(((Object[])message.obj)[1]);
				String url = (String)(((Object[])message.obj)[2]);
				listener.imageDownloaded(bitmap, url);
			}
		};
	}
	
	private static ImageDownloader instance;
	
	public static ImageDownloader getInstance(){
		if(instance == null)
			instance = new ImageDownloader();
		return instance;
	}
	
	private ImageDownloader() {		
	
	}
	
	public void download(final ImageListener listener, final String url){			
			executors.execute(new Runnable() {
				public void run() {
					Message message = new Message();
			    	Object[] objs = {listener,download(url),url};
			    	System.out.println(url);
			    	message.obj = objs;				    	 
			    	handler.sendMessage(message);					
				}
			});
		}
	
	
	public Bitmap download(String url){
		 InputStream openStream = null;				     
	     try {
	    	 URL connection = new URL(url);
	    	 Log.i("Image download" ,"Downloading: " + url);
	    	 openStream = connection.openStream();	    		    	
	    	 return BitmapFactory.decodeStream(openStream);	    	 
	     } catch(Exception e){
	    	 return null;
	     } finally {
	    	 try {
	    		 if(openStream != null)	
	    			 openStream.close();
			} catch (Exception e) {
				return null;
			}
	     }
		
	}


}
