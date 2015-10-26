package com.peg.android.pbj.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peg.android.pbj.R;
import com.peg.android.pbj.domain.Noticia;
import com.peg.android.pbj.domain.PBJApplication;
import com.peg.android.pbj.service.ComentariosService;
import com.peg.android.pbj.service.MenuExecutor;
import com.peg.android.pbj.service.ServiceError;
import com.peg.android.pbj.service.ServiceListener;

public class ComentarActivity extends Activity {
	
	ProgressDialog dialog;
	Noticia n;
	AlertDialog.Builder builder;
	private MenuExecutor menuExecutor;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		menuExecutor = new MenuExecutor(this);
	    setContentView(R.layout.comentar);	 	     
	    n = (Noticia) this.getIntent().getSerializableExtra("noticia");
	     
	     
	    TextView titulo = (TextView) findViewById(R.id.titulo_form_comentar);
	    titulo.setText(n.getTitulo());
	     
	     
	    Button b = (Button) findViewById(R.id.boton_form_enviar);
	    b.setOnClickListener(new OnClickListener() {
			
	    	@Override
			public void onClick(View v) {
				
				dialog = new ProgressDialog(ComentarActivity.this);
		    	dialog.setTitle("Cargando");
		    	dialog.setMessage("Espere por favor...");
		    	dialog.setCancelable(false);  
				dialog.show();
		    	
				builder = new AlertDialog.Builder(ComentarActivity.this);
				builder.setCancelable(false).setNegativeButton("OK", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
				
				TextView nombre = (TextView) findViewById(R.id.autor_form_comentar);
				TextView mail = (TextView) findViewById(R.id.mail_form_comentar);
				TextView comentario = (TextView) findViewById(R.id.text_form_comentar);
				
				new ComentariosService().comentar(new ServiceListener() {
					
					@Override
					public void onError(ServiceError e) {
						dialog.cancel();
						AlertDialog.Builder builder = new AlertDialog.Builder(ComentarActivity.this);
			    		
			    		builder.setTitle("Error").setMessage("No se pudo enviar su comentario intentelo mas tarde")			        	
			        	.setCancelable(false);  
			    		
			        	android.content.DialogInterface.OnClickListener listenerOnClick = new android.content.DialogInterface.OnClickListener() {				
							public void onClick(DialogInterface dialog, int which) {
								finish();					
							}
						};
						
						builder.setPositiveButton("OK",listenerOnClick );
			        	builder.create().show();
					}
					
					@Override
					public void onComplete(Object response) {
						dialog.cancel();		
						builder.setTitle("Exito");
						builder.setMessage("Tu comentario fue enviado correctamente y queda pendiente de aprobación");
						AlertDialog alert = builder.create();
						alert.show();
					}
				},n.getId(),comentario.getText().toString() + ". Enviado desde Planeta Boca Android",
				mail.getText().toString(),nombre.getText().toString(),"android");
			}
		});
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		if(PBJApplication.banner.getParent() == null){
			LinearLayout r = (LinearLayout) findViewById(R.id.linear_comentar);
    	 	r.addView(PBJApplication.banner,2);
		}
	}
	
	@Override
	public void finish() {
		super.finish();
		LinearLayout r = (LinearLayout) findViewById(R.id.linear_comentar);
   	    r.removeView(PBJApplication.banner);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		return menuExecutor.onClickMenuItem(item);
	}	
}
