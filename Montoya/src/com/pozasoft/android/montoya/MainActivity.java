package com.pozasoft.android.montoya;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.KITKAT) @SuppressLint({ "ClickableViewAccessibility", "InlinedApi", "ShowToast", "InflateParams" }) public class MainActivity extends Activity{

	private final static String OK = "Gracias por registrarte en NetBox en breve le enviaremos a su mail los datos de su nueva cuenta.";
	private final static String DATOS_INCOMPLETOS = "Tiene que completar todos los datos.";
	
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
        setContentView(R.layout.activity_main);
        
    	findViewById(R.id.activityRoot).requestFocus();
    	hideSoftKeyboard();
    	

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
    
    Handler mTimerHandler = new Handler();

    public void onSiguiente(View view){
    	
    	String message = OK;
    	
    	String  nombre      = ((TextView)findViewById(R.id.nombre)).getText().toString();
    	String  apellido    = ((TextView)findViewById(R.id.apellido)).getText().toString();
    	String  mail        = ((TextView)findViewById(R.id.mail)).getText().toString();
    	String  localidad   = ((TextView)findViewById(R.id.localidad)).getText().toString();
    	String  telefono    = ((TextView)findViewById(R.id.telefono)).getText().toString();

    	
    	if(nombre.length() == 0 || apellido.length() == 0 ||  localidad.length() == 0
    		&& mail.length() == 0 ){	
    		message = DATOS_INCOMPLETOS;    	
    	   	AlertDialog alert = new AlertDialog.Builder(this)
            .setTitle("SANTIAGO MONTOYA")
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int which) { 
                   
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .create();
        	    	    	
        	alert.show();
        	
        	alert.getWindow().getDecorView().setSystemUiVisibility(
        			this.getWindow().getDecorView().getSystemUiVisibility());
        	
        	alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);  

    	} else {
			SQLiteManager.getInstance(this).addAlta(nombre, apellido, mail, localidad, telefono);
			startActivity(new Intent(this, EncuestaActivity.class));
		}
    }


    public void showDialog(View v){

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.searchprompt, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.user_input);


        // set dialog message
        alertDialogBuilder
            .setCancelable(false)
            .setNegativeButton("Ingresar",
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    /** DO THE METHOD HERE WHEN PROCEED IS CLICKED*/
                    String user_text = (userInput.getText()).toString();

                    /** CHECK FOR USER'S INPUT **/
                    if (user_text.equals("fruta"))
                    {
                    	loginOk();
                    }
                    else{
                        String message = "Contraseña incorrecta";
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage(message);
                        builder.setPositiveButton("Cancelar", null);
                        builder.setNegativeButton("Reintentar", new DialogInterface.OnClickListener() {
                            @Override
                           public void onClick(DialogInterface dialog, int id) {
                                showDialog(null);
                           }
                        });
                        AlertDialog alert = builder.create();
                        
                        alert.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    	
                    	alert.show();
                    	
                    	alert.getWindow().getDecorView().setSystemUiVisibility(
                    			getWindow().getDecorView().getSystemUiVisibility());
                    	alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

                    }
                    }
              })
            .setPositiveButton("Cancelar",
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                	dialog.dismiss();
                }

              }

            );

        AlertDialog alertDialog = alertDialogBuilder.create();
        
        alertDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        		WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

		alertDialog.show();
		
		alertDialog.getWindow().getDecorView().setSystemUiVisibility(
				this.getWindow().getDecorView().getSystemUiVisibility());

		alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    protected void loginOk() {
		
    	View view = this.getLayoutInflater().inflate(R.layout.acciones, null);
        
        
    	final AlertDialog alert = new AlertDialog.Builder(this)
        .setCancelable(false)
        .setTitle("¿Que desea hacer?")      
        .setItems(new CharSequence[]{"Guardar","Cerrar"}, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
				SQLiteManager.getInstance(MainActivity.this).getAllAltas(MainActivity.this);
        		showToast("Se guardo en la carpeta de descargas al ultimas altas");
					break;				
				case 2:
					dialog.dismiss();
					break;
				}
				
			}
		})

        .setView(view)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .create();

    	
    	alert.show();
    	
    	alert.getWindow().getDecorView().setSystemUiVisibility(
    			this.getWindow().getDecorView().getSystemUiVisibility());

	}

	protected void showToast(String text) {
		Toast makeText = Toast.makeText(this, text, 5);
		makeText.show();
	    View decorView = getWindow().getDecorView();
		int uiOptions =   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  
			            | View.SYSTEM_UI_FLAG_FULLSCREEN 
			            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
		decorView.setSystemUiVisibility(uiOptions);
	}
	
	public final static boolean isValidEmail(CharSequence target) {
	    if (target == null) {
	        return false;
	    } else {
	        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	    }
	}
}
