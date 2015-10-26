package com.peg.android.pbj.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.MenuItem;

import com.peg.android.pbj.domain.Strings;

public class MenuExecutor {
    
    Activity activity;
    AlertDialog dialog;
    
    public MenuExecutor(Activity activity){
        this.activity = activity;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        dialog = builder.create();
        OnClickListener listenerOnClick = new OnClickListener() {                           
        public void onClick(DialogInterface dialog, int which) {
                                                            
        	}
        };
        dialog.setButton("OK",listenerOnClick );
    }
    
    
    public boolean onClickMenuItem(MenuItem item){
            String itemName = item.getTitle().toString();
            

            if(itemName.equals("Construido por PEG")){
                    dialog.setTitle("About");
                    dialog.setMessage(Strings.CONSTRUIDO_POR_PEG);
            } else{
                    if(itemName.equals("Copyright")){
                            dialog.setTitle("Copyright");
                            dialog.setMessage(Strings.COPYRIGHT);    
                    } else {                            
                            if(itemName.equals("Soporte")){
                            	sendMail("soporte@pegsoluciones.com","Soporte PBJ Android","");
                            	return true;
                            } else { 
                            	if(itemName.equals("Sobre PBJ")){
                                    dialog.setTitle("Sobre PBJ");
                                    dialog.setMessage(Strings.ACERCA_DE);
                            	} else { 
                                	if(itemName.equals("Publicite aquí")){
                                		sendMail("comercial@pegsoluciones.com","Publicidad PBJ Android","");
                                    	return true;
                                	}  else { 
                                    	if(itemName.equals("Contacto")){
                                    		sendMail("contacto@pegsoluciones.com","Contacto PBJ Android","");
                                        	return true;
                                    	}
                                    } 
                                }
                            } 
                    }
            }
            dialog.show();
            return true;
    }


	private void sendMail(String mail, String subject, String message) {
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});		  
		email.putExtra(Intent.EXTRA_SUBJECT, "Consulta PBJ Android");
		email.putExtra(Intent.EXTRA_TEXT, "");
		email.setType("message/rfc822");
		activity.startActivity(Intent.createChooser(email, "Choose an Email client :"));
	}
    
    
}
