package com.peg.android.pbj.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peg.android.pbj.R;
import com.peg.android.pbj.domain.Noticia;
import com.peg.android.pbj.domain.PBJApplication;
import com.peg.android.pbj.domain.Strings;
import com.peg.android.pbj.service.ImageDownloader;
import com.peg.android.pbj.service.ImageListener;
import com.peg.android.pbj.service.MenuExecutor;
import com.peg.android.pbj.service.NoticiasService;
import com.peg.android.pbj.service.ServiceError;
import com.peg.android.pbj.service.ServiceListener;

public class DetalleActivity extends Activity implements ImageListener, ServiceListener{

	Noticia n;
	private Bitmap bitmap;
	private MenuExecutor menuExecutor;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		
		menuExecutor = new MenuExecutor(this);
	    setContentView(R.layout.detalle);	 
	    n = (Noticia) this.getIntent().getSerializableExtra("noticia");
	     
    	TextView t = (TextView) findViewById(R.id.titulo_detalle);
    	t.setText(n.getTitulo());
    	 
    	t = (TextView) findViewById(R.id.resumen_detalle);
    	t.setText(n.getResumen());
    	 
    	ImageDownloader.getInstance().download(this, n.getImagen());
    	 
    	configurarCompartir();
 
    	new NoticiasService().getDetalle(this, Integer.valueOf(n.getId()));	     	    	 	    	 	     
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		 LinearLayout r = (LinearLayout) findViewById(R.id.detalle);
    	 r.addView(PBJApplication.banner,2);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LinearLayout r = (LinearLayout) findViewById(R.id.detalle);
   	    r.removeView(PBJApplication.banner);
	}
	
	@Override
	public void finish() {
		super.finish();
		LinearLayout r = (LinearLayout) findViewById(R.id.detalle);
   	    r.removeView(PBJApplication.banner);
	};

	void configurarCompartir() {
		
		ImageView compartir, comentarios;
		
		compartir = (ImageView) findViewById(R.id.compartir_facebook);
		compartir.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String shareBody = n.getUrlNoticia() + "\n" + n.getTitulo() + "\n" + n.getNoticia();
			    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			        sharingIntent.setType("text/plain");
			        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"PBJ Android - " +  n.getTitulo());
			        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
			        startActivity(Intent.createChooser(sharingIntent,"Planeta Boca Juniors - Android"));
			}
		});
		
		comentarios = (ImageView) findViewById(R.id.ir_comentarios);
		comentarios.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(DetalleActivity.this, ComentariosActivity.class);		
				myIntent.putExtra("noticia", n);
				DetalleActivity.this.startActivity(myIntent);	
			}
		});		
	}

	public void imageDownloaded(Bitmap b, String url) {
		ImageView imagen = (ImageView) findViewById(R.id.imagen_detalle);
		imagen.setImageBitmap(b);
		this.bitmap = b;
	}

	public void onComplete(Object response) {
		 TextView t = (TextView) findViewById(R.id.noticia_detalle);
		 t.setText((String) response);
		 n.setNoticia((String) response);
	}

	public void onError(ServiceError e) {
		TextView t = (TextView) findViewById(R.id.noticia_detalle);
		t.setText(Strings.ERROR_DETALLE);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		return menuExecutor.onClickMenuItem(item);
	}	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(bitmap != null){
			bitmap.recycle();
			bitmap = null;			
		}
	}
}
