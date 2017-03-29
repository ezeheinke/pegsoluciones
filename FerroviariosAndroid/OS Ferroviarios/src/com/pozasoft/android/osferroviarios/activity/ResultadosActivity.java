package com.pozasoft.android.osferroviarios.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.pozasoft.android.osferroviarios.R;
import com.pozasoft.android.osferroviarios.adapter.InstitucionAdapter;
import com.pozasoft.android.osferroviarios.core.ServiceError;
import com.pozasoft.android.osferroviarios.core.ServiceListener;
import com.pozasoft.android.osferroviarios.domain.Institucion;
import com.pozasoft.android.osferroviarios.service.InstitucionesServices;
import com.pozasoft.android.osferroviarios.service.PSResponse;

public class ResultadosActivity extends Activity implements OnItemClickListener, ServiceListener {
	
	ProgressDialog dialog;
	private ListView listView;
	private List<Institucion> list = new ArrayList<Institucion>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		setContentView(R.layout.instituciones);		
		
		listView = (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);
	
		new InstitucionesServices().getInstituciones(Parche.zona,Parche.barrio,Parche.especialidad,this);

    	dialog = new ProgressDialog(this);
    	dialog.setTitle("Buscando Centros de Atención");
    	dialog.setMessage("Espere por favor...");
    	dialog.setCancelable(false);
		dialog.show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Parche.institucion = list.get(position);		
		startActivity(new Intent(this,DetalleActivity.class));
	}

	@Override
	public void onComplete(Object response) {

		PSResponse psResponse = (PSResponse) response;
		
		list = (List<Institucion>) psResponse.response;
		InstitucionAdapter objAdapter = new InstitucionAdapter(
				ResultadosActivity.this, R.layout.institucion_row, list);
		listView.setAdapter(objAdapter);
		dialog.cancel();
		
		if(list.size() == 0) {
			Toast makeText = Toast.makeText(this, "No se encontraron resultados.", 2000);
			makeText.show();
		}
	}

	@Override
	public void onError(ServiceError e) {
		dialog.cancel();
		Toast makeText = Toast.makeText(this, "Ocurrió un problema, compruebe su conexion a internet y vuelva a intentarlo.", 2000);
		makeText.show();
	}

	public void onVolver(View v){
		finish();		
	}
	
}
