package com.pozasoft.android.montoya;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;

public class SeleccionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    View decorView = getWindow().getDecorView();
		int uiOptions =   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  
			            | View.SYSTEM_UI_FLAG_FULLSCREEN 
			            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
		decorView.setSystemUiVisibility(uiOptions);		
		setContentView(R.layout.activity_seleccion);
    	final View activityRootView = findViewById(R.id.activityRoot);
    	activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
    	    @Override
    	    public void onGlobalLayout() {
	    	    View decorView = getWindow().getDecorView();
	    		int uiOptions =   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	    			            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	    			            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	    			            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  
	    			            | View.SYSTEM_UI_FLAG_FULLSCREEN 
	    			            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
	    		decorView.setSystemUiVisibility(uiOptions);
    	     }
    	});
    }
    
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
	
	public void goTrivia(View v){
		startActivity(new Intent(this, TriviaActivity.class));
	}
	
	public void goEncuesta(View v){
		startActivity(new Intent(this, EncuestaActivity.class));
	}	
	public void showDialog(View v){}
}
