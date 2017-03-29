package com.pozasoft.android.osferroviarios.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pozasoft.android.osferroviarios.R;
import com.pozasoft.android.osferroviarios.activity.Parche;
import com.pozasoft.android.osferroviarios.domain.Institucion;

public class DetalleAdapter extends ArrayAdapter<Institucion>{
	
	
	private Activity activity;
	private List<Institucion> items;
	private int row;
	private Institucion institucion;

	public DetalleAdapter(Activity act, int row, Institucion institucion) {
		super(act,row,new Institucion[5]);
		this.activity = act;
		this.row = row;
		this.institucion = institucion;

	}

	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		
		
		if(position == 0){
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.detalle_top_info, null);
	
				holder = new ViewHolder();
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			
			holder.tvname     = (TextView) view.findViewById(R.id.nombre);
			holder.tvPhoneNo  = (TextView) view.findViewById(R.id.especialidad);
			
			holder.tvname.setText(Parche.institucion.getName());
			
			holder.tvPhoneNo.setText(Parche.institucion.getEspecialidadName());
		}else{		
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(row, null);
	
				holder = new ViewHolder();
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
	
			holder.tvname     = (TextView) view.findViewById(R.id.tvname);
			holder.tvPhoneNo  = (TextView) view.findViewById(R.id.tvphone);
			holder.iconRow    = (ImageView) view.findViewById(R.id.iconRow);
	
			switch (position) {		
					
				case 1:
					holder.tvname.setText("DIRECCION");
					holder.tvPhoneNo.setText(institucion.getAddress());
					holder.iconRow.setImageResource(R.drawable.map_icon);
					break;
					
				case 2:
					holder.tvname.setText("TELEFONO");
					holder.tvPhoneNo.setText(institucion.getPhone());
					holder.iconRow.setImageResource(R.drawable.phone_icon);
					break;	
					
				case 3:
					holder.tvname.setText("HORARIOS");
					holder.tvPhoneNo.setText("A completar.");
					break;				
		
				case 4:
					holder.tvname.setText("OBSERVACIONES");
					holder.tvPhoneNo.setText("A completar.");
					break;	
					
				default:
					break;
			}
		
		}
		return view;
	}
	
	public class ViewHolder {
		public TextView tvname, tvPhoneNo;
		public ImageView iconRow;
	}
}
