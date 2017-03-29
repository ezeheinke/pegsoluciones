package com.pozasoft.android.osferroviarios.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pozasoft.android.osferroviarios.R;
import com.pozasoft.android.osferroviarios.domain.Barrio;
import com.pozasoft.android.osferroviarios.domain.Constants;
import com.pozasoft.android.osferroviarios.domain.Especialidad;
import com.pozasoft.android.osferroviarios.domain.Zona;
import com.pozasoft.android.osferroviarios.service.BarrriosManager;
import com.pozasoft.android.osferroviarios.service.EspecialidadesManager;
import com.pozasoft.android.osferroviarios.service.ZonasManager;


public class BuscadorActivity extends Activity {
	
	private Spinner especialidadesSpinner;
	private Spinner zonasSpines;
	private Spinner barriosSpines;

	private Barrio 		 barrioSelected;
	private Zona   		 zonaSelected;
	private Especialidad especialidadSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		setContentView(R.layout.buscador);
		
		barriosSpines = (Spinner) findViewById(R.id.barrio);
		barriosSpines.setOnItemSelectedListener(new OnItemSelectedListener() {
		
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				barrioSelected = BarrriosManager.getBarrios(zonaSelected.getId())[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { 
				System.out.println("dassad");
			}

		});
		
		zonasSpines = (Spinner) findViewById(R.id.zona);
		zonasSpines.setAdapter(new ZonaAdapter(this, R.layout.spinner, ZonasManager.getZonas()));
		zonasSpines.setOnItemSelectedListener(new OnItemSelectedListener() {
		
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				zonaSelected = ZonasManager.getZonas()[position];
				barriosSpines.setAdapter(new BarrioAdapter(BuscadorActivity.this, R.layout.spinner, BarrriosManager.getBarrios(zonaSelected.getId())));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { 
				System.out.println("dassad");
			}

		});
		
		
		especialidadesSpinner = (Spinner) findViewById(R.id.especialidad);
		especialidadesSpinner.setAdapter(new EspecialidadAdapter(this, R.layout.spinner, EspecialidadesManager.getEspecialidades()));
		especialidadesSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				especialidadSelected = EspecialidadesManager.getEspecialidades()[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { 
				System.out.println("dassad");
			}

		});
		
	}
	
	@SuppressLint("ShowToast")
	public void onBuscar(View view) {
		Parche.zona = zonaSelected.getId();
		Parche.barrio = barrioSelected.getId();
		Parche.especialidad = especialidadSelected.getId();
		if(Parche.barrio.equals(Constants.ID_TODOS_BARRIOS) && Parche.especialidad.equals(Constants.ID_TODAS_LAS_ESPECIALIDADES)){
			Toast.makeText(this, "Tiene que elegir al menor un barrio o una especialidad", 4000).show();
		} else {
			Intent intent = new Intent(this,ResultadosActivity.class);
			startActivity(intent);
		}
	}
	
	public void onEmergencias(View v){
        final Dialog dialog = new Dialog(this);        
        dialog.setContentView(R.layout.telefonos_popup);
        dialog.setTitle("Emergencias y contacto");
        
        dialog.findViewById(R.id.llamarOSFE).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:08003333313"));
				startActivity(callIntent);
			}
		});
        
        dialog.findViewById(R.id.llamarEmergencias).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:08003336733"));
				startActivity(callIntent);
			}
		});
        
        dialog.show();       
	}	
	
	public class ZonaAdapter extends ArrayAdapter<Zona>{
	
		Zona[] objects;
		
		
		public ZonaAdapter(Context context, int resource, Zona[] objects) {
			super(context, resource, objects);
			this.objects = objects;
		}

	    @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

        	
	        LayoutInflater inflater=getLayoutInflater();
	        View row=inflater.inflate(R.layout.spinner_item, parent, false);
	        TextView label=(TextView)row.findViewById(R.id.name_item);
	        label.setText(objects[position].getName());
	
	        if(parent instanceof Spinner){
	        	label.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);	    
	        }
	        
	        return row;
        }
	       
	}
	
	public class EspecialidadAdapter extends ArrayAdapter<Especialidad>{
		
		Especialidad[] objects;

		public EspecialidadAdapter(Context context, int resource, Especialidad[] objects) {
			super(context, resource, objects);
			this.objects = objects;
		}

	    @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
        	
	        LayoutInflater inflater=getLayoutInflater();
	        View row=inflater.inflate(R.layout.spinner_item, parent, false);
	        TextView label=(TextView)row.findViewById(R.id.name_item);
	        label.setText(objects[position].getName());
	
	        if(parent instanceof Spinner){
	        	label.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);	    
	        }
	        
	        return row;
        }   
	}
	
	public class BarrioAdapter extends ArrayAdapter<Barrio>{
		
		Barrio[] objects;
		
		public BarrioAdapter(Context context, int resource, Barrio[] objects) {
			super(context,resource,objects);
			this.objects = objects;
		}		

	    @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

	        LayoutInflater inflater=getLayoutInflater();
	        View row=inflater.inflate(R.layout.spinner_item, parent, false);
	        TextView label=(TextView)row.findViewById(R.id.name_item);
	        label.setText(objects[position].getName());

	        if(parent instanceof Spinner){
	        	label.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);	    
	        }
	        
	        return row;
        }   
	}
}
