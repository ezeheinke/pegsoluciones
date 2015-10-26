package com.peg.blackberry.planetaboca.util;

import com.blackberry.facebook.ApplicationSettings;
import com.blackberry.facebook.Facebook;
import com.blackberry.facebook.inf.User;

public class PBJFacebook {

	private final static String REST_URL = "http://api.facebook.com/restserver.php"; // As per Facebook.
	private final static String GRAPH_URL = "https://graph.facebook.com"; // As per Facebook.
	private final static String NEXT_URL = "http://www.facebook.com/connect/login_success.html"; // Your successful URL.
	private final static String APPLICATION_KEY = "f79f0ba4a7e0d80fcfb6693fabbad066";//"f21032d377681e02051e639830b4b678"; // Your Facebook Application Key. 
	private final static String APPLICATION_SECRET = "601d6447a20970c2dfa4c3dd20852675";//"590906fcfea8e348589cf43f06192c2e"; // Your Facebook Application Secret.
	private final static String APPLICATION_ID = "307035705986426";//"317175255300"; // Your Facebook Application ID.
	private final static String[] PERMISSION = Facebook.Permissions.ALL_PERMISSIONS;
	
	public static ApplicationSettings as = new ApplicationSettings(NEXT_URL, APPLICATION_ID, APPLICATION_SECRET,
			PERMISSION);
	
	public static Facebook fb = Facebook.getInstance(as);
	public static User currentUser;
	
}
