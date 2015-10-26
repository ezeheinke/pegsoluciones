package com.peg.android.pbj.ui.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peg.android.pbj.R;
import com.peg.android.pbj.domain.Comentario;
import com.peg.android.pbj.domain.Noticia;
import com.peg.android.pbj.domain.PBJApplication;
import com.peg.android.pbj.service.ComentariosService;
import com.peg.android.pbj.service.MenuExecutor;
import com.peg.android.pbj.service.ServiceError;
import com.peg.android.pbj.service.ServiceListener;

public class ComentariosActivity extends Activity implements ServiceListener {
	
	private static final int AMARILLO_COMENTARIOS = Color.rgb(255,255,255);
	private static final int AZUL_COMENTARIOS 	  = Color.rgb(229,229,229);
	Noticia n;
	ProgressDialog dialog;
	private MenuExecutor menuExecutor;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		
	    menuExecutor = new MenuExecutor(this);
		
		setContentView(R.layout.comentarios);	 
	    n = (Noticia) this.getIntent().getSerializableExtra("noticia");
	    ((TextView)findViewById(R.id.titulo_comentarios)).setText(n.getTitulo());
	    new ComentariosService().getComentarios(n.getId(), this);
	     
	    Button b = (Button)findViewById(R.id.comentar);
	    b.setOnClickListener(new OnClickListener() {
		
	    	@Override
	    	public void onClick(View v) {				
				Intent myIntent = new Intent(ComentariosActivity.this, ComentarActivity.class);		
				myIntent.putExtra("noticia", n);
				startActivity(myIntent);	
			}
		});
	     
	    dialog = new ProgressDialog(ComentariosActivity.this);
    	dialog.setTitle("Cargando");
    	dialog.setMessage("Espere por favor...");
    	dialog.setCancelable(false);  
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		 LinearLayout r = (LinearLayout) findViewById(R.id.linear_comentarios);
    	 r.addView(PBJApplication.banner,2);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		return menuExecutor.onClickMenuItem(item);
	}
	
	@Override
	public void finish() {
		super.finish();
		LinearLayout r = (LinearLayout) findViewById(R.id.linear_comentarios);
   	    r.removeView(PBJApplication.banner);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		LinearLayout r = (LinearLayout) findViewById(R.id.linear_comentarios);
   	    r.removeView(PBJApplication.banner);
	}
	
	


	@SuppressWarnings("unchecked")
	@Override
	public void onComplete(Object response) {
		LinearLayout lista = (LinearLayout) findViewById(R.id.lista_comentarios);
		List<Comentario> comentarios = (List<Comentario>) response;
		dialog.cancel();
		
		for(int i = 0 ; i < comentarios.size() ; i++){
			Comentario c = comentarios.get(i);
			LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	View v = (View) inflater.inflate(R.layout.comentario, null);
	    	
	    	int textColor;
	    	if(i % 2 == 0){
	    		v.setBackgroundColor(AZUL_COMENTARIOS);
	    		//textColor = AMARILLO_COMENTARIOS;
	    	}
	    	else{
	    		v.setBackgroundColor(AMARILLO_COMENTARIOS);
	    		//textColor =AZUL_COMENTARIOS;
	    	}
	    	
	    	TextView t;
	    	t = (TextView) v.findViewById(R.id.text_comentario);
	    	t.setText(c.getComentario());
	    	//t.setTextColor(textColor);
	    	
	    	t = (TextView) v.findViewById(R.id.autor_comentario);
	    	t.setText(c.getNombre());
	    	//t.setTextColor(textColor);
	    	
	    	t = (TextView) v.findViewById(R.id.fecha_comentario);
	    	t.setText(c.getFecha());
	    	//t.setTextColor(textColor);	    	
	    	
	    	lista.addView(v);
	    	
		}
		
		if(comentarios.size() == 0){
			TextView t = new TextView(this);
			t.setText(R.string.cero_comentarios);
			t.setPadding(10, 10, 10, 10);
			lista.addView(t);			
		}
	}

	@Override
	public void onError(ServiceError e) {
		dialog.cancel();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(ComentariosActivity.this);
		
		builder.setTitle("Error").setMessage("No se pudieron obtener los comentarios, intentelo mas tarde")			        	
    	.setCancelable(false);  
		
    	android.content.DialogInterface.OnClickListener listenerOnClick = new android.content.DialogInterface.OnClickListener() {				
			public void onClick(DialogInterface dialog, int which) {
				finish();					
			}
		};
		
		builder.setPositiveButton("OK",listenerOnClick );
    	builder.create().show();

	}	
}
