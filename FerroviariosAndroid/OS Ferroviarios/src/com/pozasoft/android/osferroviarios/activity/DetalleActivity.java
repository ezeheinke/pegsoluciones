package com.pozasoft.android.osferroviarios.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.pozasoft.android.osferroviarios.R;
import com.pozasoft.android.osferroviarios.adapter.DetalleAdapter;

public class DetalleActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		setContentView(R.layout.detalle);
		
		ListView listView = (ListView) findViewById(R.id.detail_list);
		listView.setOnItemClickListener(this);
		DetalleAdapter objAdapter = new DetalleAdapter(this, R.layout.institucion_row,Parche.institucion);
		listView.setAdapter(objAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		if(position == 1 ){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com?q=" + Parche.institucion.getAddress()));
			startActivity(browserIntent);
		} else if(position == 2 ){
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:" + Parche.institucion.getPhone()));
			startActivity(callIntent);
		}
		
	}


	
}
