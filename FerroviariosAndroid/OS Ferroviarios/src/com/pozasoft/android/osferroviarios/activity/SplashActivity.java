package com.pozasoft.android.osferroviarios.activity;

import java.util.ArrayList;
import java.util.Hashtable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.pozasoft.android.osferroviarios.R;
import com.pozasoft.android.osferroviarios.core.ServiceError;
import com.pozasoft.android.osferroviarios.core.ServiceListener;
import com.pozasoft.android.osferroviarios.domain.Barrio;
import com.pozasoft.android.osferroviarios.domain.Especialidad;
import com.pozasoft.android.osferroviarios.domain.Zona;
import com.pozasoft.android.osferroviarios.service.BarriosServices;
import com.pozasoft.android.osferroviarios.service.BarrriosManager;
import com.pozasoft.android.osferroviarios.service.EspecialidadesManager;
import com.pozasoft.android.osferroviarios.service.EspecialidadesServices;
import com.pozasoft.android.osferroviarios.service.PSResponse;
import com.pozasoft.android.osferroviarios.service.ZonasManager;
import com.pozasoft.android.osferroviarios.service.ZonasServices;

public class SplashActivity extends Activity implements ServiceListener {
	
	int TOTAL_SERVICES 	 = 3;
	int finishedServices = 0;
	boolean noError = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.activity_main);
		
		new ZonasServices().getZonas(this);
		new BarriosServices().getBarriosXZonas(this);
		new EspecialidadesServices().getEspecialidades(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public synchronized void onComplete(Object response) {
		finishedServices++;
		
		PSResponse psResponse = (PSResponse) response;
		
		if(psResponse.id.equals("ZONAS")){
			ZonasManager.setZonas((ArrayList<Zona>) psResponse.response);
		}
		
		if(psResponse.id.equals("BARRIOS")){
			BarrriosManager.setBarriosXZona((Hashtable<String, Barrio[]>)psResponse.response);
		}
		
		if(psResponse.id.equals("ESPECIALIDADES")){
			EspecialidadesManager.setEspecialidades((Especialidad[]) psResponse.response);
		}
		
		if(finishedServices == TOTAL_SERVICES && noError){
			startActivity(new Intent(this, BuscadorActivity.class));
			finish();
		}
	}

	@Override
	public void onError(ServiceError e) {
		noError = false;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Error")
				.setMessage("Ha ocurrido un error de comunicación. Comprube su conexión y vuelva a intentarlo.")
				.setNeutralButton("OK",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				})
				.setCancelable(true);  
		
		builder.create().show();
	}
}
