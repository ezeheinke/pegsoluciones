/**
 * 
 */
package com.pegsoluciones.blackberry.common.web.url;

import java.io.InputStream;

import org.json.me.JSONObject;

import com.pegsoluciones.blackberry.common.util.StringUtils;
import com.pegsoluciones.blackberry.common.web.provider.ContentType;
import com.pegsoluciones.blackberry.common.web.provider.DefaultWebserviceProvider;

/**
 * This class is the Google implementation to the URL Shotener service.<br>
 * Please visit {@link http://goo.gl/}
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 * @see URLShortener
 * 
 */
public final class GoogleURLShortener implements URLShortener {

	private static final String GOOGLE_URL = "http://goo.gl/api/shorten";


	public String shorten(String longUrl) {

		try {
			
			DefaultWebserviceProvider provider = new DefaultWebserviceProvider();

			StringBuffer param = new StringBuffer();
			param.append("url=");
			param.append(longUrl);
			
			InputStream stream = provider.loadResource(
					GOOGLE_URL, 
					param.toString(),
					null,
					new ContentType("application/x-www-form-urlencoded;charset=UTF-8"),
					0);
			
			JSONObject jsonObject = new JSONObject(StringUtils.getStringFromStream(stream, "UTF-8"));
			return jsonObject.getString("short_url");
		}

		catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
