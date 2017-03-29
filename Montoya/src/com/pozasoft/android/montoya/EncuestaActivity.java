package com.pozasoft.android.montoya;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EncuestaActivity extends Activity {
	
	private static final String RadioGroup = null;
	static List<Question> QUESTIONS;
	
	static {
		InputStream is;
		try {
			is = MyApplication.getAppContext().getAssets().open("encuesta.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			
			JSONArray questions= new JSONArray(new String(buffer, "UTF-8"));
			
			QUESTIONS = new ArrayList<Question>();
			
			for(int i = 0 ; i < questions.length();i++){
				JSONObject j = (JSONObject) questions.get(i);
				Question q = new Question();
				q.setQuestion(j.getString("question"));
				q.setChoices(new ArrayList<String>());
				JSONArray a = j.getJSONArray("choices");
				for(int k = 0; k < a.length() ; k++){
					q.getChoices().add(a.getString(k));
				}
				QUESTIONS.add(q);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	TextView pregunta;
	List<RadioButton> opciones;
	int currentQuestion;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_encuesta);
        
        currentQuestion = -1;
        pregunta = (TextView) findViewById(R.id.pregunta);
        Log.i("",QUESTIONS.toString());
        radioGroup = (RadioGroup) findViewById(R.id.ragiogroup);
        opciones = new ArrayList<RadioButton>();
        for(int i = 1; i < 10 ; i++){
        	opciones.add((RadioButton) radioGroup.findViewWithTag("" + i));
        }
        onSiguiente(null);
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
	
	public void onSiguiente(View v){		
		if(radioGroup.getCheckedRadioButtonId() != -1 || currentQuestion == -1 ){
			
			if(currentQuestion > -1){
				RadioGroup radioGroup = (android.widget.RadioGroup) findViewById(R.id.ragiogroup);
				SQLiteManager.getInstance(this).addRespuesta("" + (currentQuestion + 1),
						findViewById(radioGroup.getCheckedRadioButtonId()).getTag().toString());
			}
				
			
			currentQuestion++;
			if(currentQuestion < QUESTIONS.size()){
				Question q = QUESTIONS.get(currentQuestion);
				pregunta.setText(q.getQuestion());
				int i = 0;
				for(; i < q.getChoices().size(); i++){
					opciones.get(i).setText(q.getChoices().get(i));
					opciones.get(i).setVisibility(View.VISIBLE);
				}
				
				for(; i < opciones.size(); i++){
					opciones.get(i).setVisibility(View.GONE);
				}
				
				radioGroup.clearCheck();
			} else {
				startActivity(new Intent(this,SeleccionActivity.class));
			}
		}
	}
	
	public void showDialog(View v){}
}
