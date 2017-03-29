package com.pozasoft.android.osferroviarios.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

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
import com.pozasoft.android.osferroviarios.domain.Barrio;

public class BarriosServices extends AbstractService{
	
	private static final String ID_ZONA 	  = "id_zona";
	private static final String BARRIOS 	  = "barrios";
	private static final String ID_BARRIO     = "id_barrio";
	private static final String NOMBRE_BARRIO = "nombre_barrio";

	public void getBarriosXZonas(final ServiceListener listener){
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

	protected PSResponse doGetZonas() throws RuntimeException, ClientProtocolException, IOException, JSONException {
		Hashtable<String, Barrio[]> barriosXzonas = new Hashtable<String, Barrio[]>();
		StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet("http://apka.hecsgroup.com/servicios/barrio.php");
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
				JSONObject jsonBarrios = (JSONObject) json.get(i);
				String idZona = jsonBarrios.getString(ID_ZONA);
				JSONArray jsonBarriosArray =jsonBarrios.getJSONArray(BARRIOS);
				Barrio[] barrios = new Barrio[jsonBarriosArray.length() + 1];
				Barrio barrio = new Barrio();
				barrio.setId("0");
				barrio.setName("Todas las localidades");
				barrios[0] = barrio;
				for (int j = 0; j < jsonBarriosArray.length() ; j++) {
					JSONObject jsonBarrio = (JSONObject) jsonBarriosArray.get(j );
					Barrio b  = new Barrio();
					b.setId(jsonBarrio.getString(ID_BARRIO));
					b.setName(jsonBarrio.getString(NOMBRE_BARRIO));
					barrios[j + 1] = b;
				}
				barriosXzonas.put(idZona,barrios);
			}
	    }

		PSResponse psResponse = new PSResponse();	
		psResponse.id = "BARRIOS";
		psResponse.response = barriosXzonas;
		return psResponse;
	}

}
