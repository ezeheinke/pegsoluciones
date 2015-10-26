package com.peg.android.pbj.service;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Patterns;

import com.peg.android.pbj.domain.Mensaje;

public class MensajesService extends AbstractService {


    private static final String MENSAJE = "mensaje";
    private static final Object TEXTO = "texto";
    private static final Object LINK = "link";
    private static final Object TIEMPO = "tiempo";
    String url = "http://www.pegsoluciones.com/proyectos/PBJ/mensajes.php?idTelefono=";
    String versionApp = "&verdionApp=";
    String mail = "&mail=";
    
    public List<Mensaje> doGetMensajes(Context context) {
		URLConnection conn = null;
		URL urlM = null;
    	try {
			Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
			Account[] accounts = AccountManager.get(context).getAccounts();
			String possibleEmail = null;
			for (Account account : accounts) {
				if (emailPattern.matcher(account.name).matches()) {
					possibleEmail = account.name;	    	       
				}
			}
			TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			String uid = tManager.getDeviceId();
			List<Mensaje> v = new ArrayList<Mensaje>();
			url += uid;
			url += versionApp + "A0.1";
			url += mail + possibleEmail;
			
			urlM = new URL(url);
			conn = urlM.openConnection();
			conn.setConnectTimeout(5000);
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());
			Node root = doc.getFirstChild();
			NodeList items = root.getChildNodes();
			
			Mensaje m;
			
			for (int i=0;i<items.getLength();i++){
				Node item = items.item(i);                              
				String value,name = item.getNodeName();
				
				if(name.equals(MENSAJE)){
					m = new Mensaje();
					NodeList valoresMensaje = item.getChildNodes();
					for(int j = 0 ; j < valoresMensaje.getLength() ; j++){
						Node n = valoresMensaje.item(j);                                        
						name = n.getNodeName();
						
						if(name.equals("#text"))
							continue;
						value = n.getFirstChild().getNodeValue();
						if(name.equals(TEXTO)){
							m.setTexto(value);
						}else if(name.equals(LINK)){
							m.setLink(value);
						}else if(name.equals(TIEMPO)){
							m.setTime(Integer.parseInt(value));
						}
					}
					v.add(m);
				}
			}
			return v;
		} catch (Exception e) {
			throw new RuntimeException("Error mensajes service: " + e);			
		} finally{
			
		}
    	
    }

    public void getMensajes(final ServiceListener listener,final Context context){
    	execute(new Runnable() {			
			public void run() {				
				try {
					complete(listener, doGetMensajes(context));													
				} catch (Exception e) {
					
				}
			}
		});
    }

}
