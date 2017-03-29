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
import com.pozasoft.android.osferroviarios.domain.Especialidad;
import com.pozasoft.android.osferroviarios.domain.Zona;

public class EspecialidadesServices extends AbstractService{
	
	private static final String ID_ESPECIALIDAD = "id_especialidad";
	private static final String NOMBRE_ESPECIALIDAD = "nombre_especialidad";

	public void getEspecialidades(final ServiceListener listener){
		execute(new Runnable() {			
			public void run() {
				try {
					complete(listener, doGetZonas());
				} catch (Exception e) {
					fail(listener, e);
				}				
			}
		});
	}

	protected PSResponse doGetZonas()  throws RuntimeException, ClientProtocolException, IOException, JSONException {		
		StringBuilder builder = new StringBuilder();
		Especialidad[] especialidades = null;
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet("http://apka.hecsgroup.com/servicios/especialidad.php");
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
			especialidades = new Especialidad[json.length() + 1];
			Especialidad todas = new Especialidad();
			todas.setId("0");
			todas.setName("Todas las prestaciones");
			especialidades[0] = todas;
			for (int i = 0; i < json.length() ; i++) {
				JSONObject jsonZona = (JSONObject) json.get(i);
				Especialidad especialidad = new Especialidad();
				especialidad.setId(jsonZona.getString(ID_ESPECIALIDAD));
				especialidad.setName(jsonZona.getString(NOMBRE_ESPECIALIDAD));
				especialidades[i + 1] = especialidad;
			}

        }

	    PSResponse psResponse = new PSResponse();
	    psResponse.id = "ESPECIALIDADES";
	    psResponse.response = especialidades;
		return psResponse;
	}

}
