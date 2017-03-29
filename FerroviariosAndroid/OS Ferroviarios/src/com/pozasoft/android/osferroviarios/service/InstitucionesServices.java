package com.pozasoft.android.osferroviarios.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pozasoft.android.osferroviarios.core.AbstractService;
import com.pozasoft.android.osferroviarios.core.ServiceListener;
import com.pozasoft.android.osferroviarios.domain.Institucion;

public class InstitucionesServices extends AbstractService{
	
	private static final String ID_INSTITUCION 	 	  = "id_institucion";
	private static final String NOMBRE_INSTITUCION	  = "nombre_institucion";
	private static final String DOMICILIO_INSTITUCION = "domicilio_institucion";
	private static final String TELEFONO_INSTITUCION  = "telefono_institucion";
	private static final String NOMBRE_ESPECIALIDAD   = "nombre_especialidad";

	public void getInstituciones(final String zona,final String barrio, final String especialidad,final ServiceListener listener){
		execute(new Runnable() {			
			public void run() {
				try {
					complete(listener, doGetZonas(zona,barrio,especialidad));
				} catch (Exception e) {
					fail(listener, e);
				}				
			}
		});
	}

	protected PSResponse doGetZonas(String zona,String barrio, String especialidad) throws RuntimeException, ClientProtocolException, IOException, JSONException {		
		StringBuilder builder = new StringBuilder();
		ArrayList<Institucion> instituciones = new ArrayList<Institucion>();
	    HttpClient client = new DefaultHttpClient();
	    String urlString = "http://apka.hecsgroup.com/servicios/institucion.php?id_barrio="
	    		+ barrio +"&id_zona=" + zona + "&id_especialidad=" + especialidad;
		HttpGet httpGet = new HttpGet(urlString);

		HttpResponse response = client.execute(httpGet);
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode == 200) {
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	        
	        String s = builder.toString();
			JSONArray json = new JSONArray(s);
			for (int i = 0; i < json.length(); i++) {
				JSONObject jsonInstitucion = (JSONObject) json.get(i);
				Institucion institucion = new Institucion();
				institucion.setId(jsonInstitucion.getString(ID_INSTITUCION));
				institucion.setName(jsonInstitucion.getString(NOMBRE_INSTITUCION));
				institucion.setAddress(jsonInstitucion.getString(DOMICILIO_INSTITUCION));
				institucion.setEspecialidadName(jsonInstitucion.getString(NOMBRE_ESPECIALIDAD));
				institucion.setPhone(jsonInstitucion.getString(TELEFONO_INSTITUCION));		
				instituciones.add(institucion);
			}
	    }
 
	    
	    PSResponse psResponse = new PSResponse();
	    psResponse.id = "INSTITUCIONES";
	    psResponse.response = instituciones;
		return psResponse;
	}

}
