package com.pozasoft.android.montoya;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import android.widget.Toast;

public class TriviaActivity extends Activity {
	
	static List<Question> QUESTIONS;
	int correctas = 0;
	Question lastQuestion;
	
	static {
		InputStream is;
		try {
			is = MyApplication.getAppContext().getAssets().open("trivia.json");
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
				q.setCorrectAnswer(j.getString("answer"));
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
	List<Question> randomQuestions;

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
        setContentView(R.layout.activity_trivia);
        
        currentQuestion = -1;
        pregunta = (TextView) findViewById(R.id.pregunta);
        Log.i("",QUESTIONS.toString());
        radioGroup = (RadioGroup) findViewById(R.id.ragiogroup);
        opciones = new ArrayList<RadioButton>();
        for(int i = 1; i < 4 ; i++){
        	opciones.add((RadioButton) radioGroup.findViewWithTag("" + i));
        }
        
        Random r = new Random();
        randomQuestions = new ArrayList<Question>();
        List<Question> qs = new ArrayList<Question>(QUESTIONS);
        while(randomQuestions.size() < 3){
        	Question q = qs.get(r.nextInt(qs.size() - 1));
        	if(!randomQuestions.contains(q))
        		randomQuestions.add(q);
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
		int id = radioGroup.getCheckedRadioButtonId();
		if(id != -1 || currentQuestion == -1 ){
			currentQuestion++;
			if(currentQuestion > 0){
				String s;
				if(lastQuestion.getCorrectAnswer().equals(
						"" + (Integer.parseInt((String)findViewById(id).getTag()) - 1))){
					s = "Respuesta correcta. ";
					correctas++;
				}else{
					s = "La respuesta correcta era \"" +
						lastQuestion.getCorrectAnswerText() +
						"\". ";
				}
				
				Toast.makeText(this, s + "Llevas " + correctas +
						(correctas != 1 ? " preguntas correctas.":" pregunta correcta."), 
						3000).show();
			}
			if(currentQuestion < randomQuestions.size()){
				Question q = randomQuestions.get(currentQuestion);
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
				lastQuestion = q;
			} else {
				startActivity(new Intent(this,GraciasActivity.class));
			}
		}
	}
	
	public void showDialog(View v){}
}
