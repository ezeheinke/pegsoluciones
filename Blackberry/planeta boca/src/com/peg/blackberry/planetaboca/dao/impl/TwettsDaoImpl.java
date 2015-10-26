package com.peg.blackberry.planetaboca.dao.impl;

import java.io.InputStream;
import java.util.Vector;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import org.json.me.JSONTokener;

import com.peg.blackberry.planetaboca.dao.TwettsDao;
import com.peg.blackberry.planetaboca.domain.Twett;
import com.peg.blackberry.planetaboca.domain.TwitterUser;
import com.pegsoluciones.blackberry.common.dao.AbstractDao;


public class TwettsDaoImpl extends AbstractDao implements TwettsDao {		
	
	private static final String RETWEETS = "retweet_count";
	private static final String USER_NAME = "screen_name";
	private static final String AVATAR_USER = "profile_image_url";
	private static final String USER = "user";
	private static final String TEXT = "text";
	private static final String URL_TWITTER = "http://api.twitter.com/1/statuses/" +
			"user_timeline.json?count=10&screen_name=";
	private static final String URL_USER = "https://twitter.com/users/show/";
	private static final String CREATE_AT = "created_at";
	private static final String ID = "id";
	
	
	public Vector getTwetts(String usuario) {

		try {
			Vector twetts = new Vector(); 
			String url = URL_TWITTER+ usuario;
			JSONArray jsonArray;
			InputStream in = getServiceProvider().loadResource(url);
			StringBuffer stringBuffer = new StringBuffer();
			int c;
			while((c = in.read()) != -1)stringBuffer.append((char)c);
			JSONTokener jsonTokener = new JSONTokener(stringBuffer.toString());
			jsonArray= new JSONArray( jsonTokener);
			
			for(int i = 0 ; i < jsonArray.length() ; i++){
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String text = jsonObject.getString(TEXT);				
				JSONObject userJSONObject = (JSONObject)jsonObject.get(USER);
				String urlAvatar = userJSONObject.getString(AVATAR_USER);
				String user = userJSONObject.getString(USER_NAME);
				String retwetts = jsonObject.getString(RETWEETS);
				String creado = jsonObject.getString(CREATE_AT);
				String id = jsonObject.getString(ID);
				twetts.addElement(new Twett(user, urlAvatar, text,retwetts,creado,id));				
			}	
			return twetts;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}							
		
	}

	public TwitterUser getUser(String user) {
		try {
			TwitterUser tU = new TwitterUser();
			JSONObject jsonObject;
			InputStream in = getServiceProvider().loadResource(URL_USER + user + ".json");
			StringBuffer stringBuffer = new StringBuffer();
			int c;
			while((c = in.read()) != -1)stringBuffer.append((char)c);
			JSONTokener jsonTokener = new JSONTokener(stringBuffer.toString());
			jsonObject = new JSONObject( jsonTokener);
			tU.setDescripcion(jsonObject.getString("description"));
			tU.setNombre("@" + jsonObject.getString("screen_name"));
			tU.setUrlAvatar(jsonObject.getString("profile_image_url"));
			return tU;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}	
	}



}
