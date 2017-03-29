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
import com.pozasoft.android.osferroviarios.domain.Zona;

public class ZonasServices extends AbstractService{
	
	private static final String ID_ZONA = "id_zona";
	private static final String NOMBRE_ZONA = "nombre_zona";

	public void getZonas(final ServiceListener listener){
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
		ArrayList<Zona> zonas = new ArrayList<Zona>();
		StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet("http://apka.hecsgroup.com/servicios/zona.php");

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
				JSONObject jsonZona = (JSONObject) json.get(i);
				Zona zona = new Zona();
				zona.setId(jsonZona.getString(ID_ZONA));
				zona.setName(jsonZona.getString(NOMBRE_ZONA));
				zonas.add(zona);
			}
	
  	   }
		
	    PSResponse psResponse = new PSResponse();
	    psResponse.id = "ZONAS";
	    psResponse.response = zonas;
		return psResponse;
	}

}
