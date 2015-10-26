package com.peg.android.pbj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;

import com.peg.android.pbj.domain.Banner;
import com.peg.android.pbj.domain.Strings;
import com.peg.android.pbj.ui.activity.DetalleFromUrlActivity;
import com.peg.android.pbj.ui.activity.TabbedListListActivity;

public class PBJActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		
        
        if( savedInstanceState!= null){ // get saved state
            try {
                name= savedInstanceState.getString(Strings.NOMBRE_NOTICIA);
                Log.d("pablo","RestoredState!");
            }
            catch(Exception e){
                Log.d("pablo","FailedToRestore",e);
            }
        }
        
        
        setContentView(R.layout.main);     
                      
        Banner.res = getResources();

        new Thread() {
            public void run() {            	
            	try {
					Thread.sleep(1500);
					Message msg = Message.obtain();
		            msg.what = 1;
		            messagHandler.sendMessage(msg);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}	           
            }

        }.start();
    
        
   }   
    
	private Handler messagHandler = new Handler() {
		public void handleMessage(Message message) {
		     try{
	        	ConnectivityManager cm =
		                (ConnectivityManager)PBJActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
	        	 NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
	        	 boolean isConnected = activeNetwork != null && (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI ||
			        		activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) ;
			    	
			    	if(!isConnected){
			    		AlertDialog.Builder builder = new AlertDialog.Builder(PBJActivity.this);
			    		
			    		builder.setTitle("Error").setMessage("No se dectecto conexion a internet, la aplicacion se cerrara")			        	
			        	.setCancelable(false);  
			    		
			        	OnClickListener listenerOnClick = new OnClickListener() {				
							public void onClick(DialogInterface dialog, int which) {
								PBJActivity.this.finish();					
							}
						};
						builder.setPositiveButton("OK",listenerOnClick );
			        	builder.create().show();
			    	}else{		    	
			    		startMyActivity();
			    	}
	        }catch (Exception e) {
				System.out.println(e);
				Log.i("$$$$$$$$", e.toString());
			}

	    }
	};
	private String name;    

	public void startMyActivity() {		
		Intent myIntent = new Intent(this, TabbedListListActivity.class);		
		PBJActivity.this.startActivity(myIntent);				
		goToDetailIfitStartedFromURL();		
		this.finish();
	}

	private void goToDetailIfitStartedFromURL() {       		
    	Intent intent = getIntent();
    	Uri data = intent.getData();
        if(data != null){        	
        	String url = data.toString();
        	int i = url.indexOf("/",8);
        	if(i > 0){
        		String name = url.substring(i + 1,url.length() - 1);        	
        		intent = new Intent(this, DetalleFromUrlActivity.class);
        		intent.putExtra(Strings.NOMBRE_NOTICIA, name);
        		intent.putExtra(Strings.URL_NOTICIA, url);
        		startActivity(intent);
        	}
        }
	}   

	 protected void onSaveInstanceState(Bundle outState){
	        name= "";
	        outState.putString(Strings.NOMBRE_NOTICIA, name);
	        super.onSaveInstanceState(outState); // save view state
	 }
}