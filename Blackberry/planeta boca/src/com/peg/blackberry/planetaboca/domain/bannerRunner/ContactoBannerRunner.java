package com.peg.blackberry.planetaboca.domain.bannerRunner;

import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;

import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.PhoneArguments;
import net.rim.device.api.ui.component.Dialog;

import com.peg.blackberry.planetaboca.domain.Banner;
import com.peg.blackberry.planetaboca.domain.BannerRunner;
import com.peg.blackberry.planetaboca.util.Strings;

public class ContactoBannerRunner implements BannerRunner {

	public void run(Banner b) {		
		ContactList contactList = null;
		try {
			contactList = null;

			contactList = (ContactList) PIM.getInstance().openPIMList(PIM.CONTACT_LIST, PIM.READ_WRITE);
		    Contact contact = contactList.createContact();
	
	
		    String[] name = new String[ contactList.stringArraySize( Contact.NAME ) ];
		    name[Contact.NAME_GIVEN] = b.getNombre();
		    contact.addStringArray(Contact.NAME, Contact.ATTR_NONE, name);
		    contact.addString(Contact.TEL,Contact.ATTR_NONE,b.getTelefono());
		    contact.commit();
			contactList.close();

			String msj = Strings.SE_GUARDO_CONTACTO + b.getNombre() + "\n" + Strings.DESEA_LLAMARLO_AHORA; 
			int ask = Dialog.ask(msj,Strings.OPCIONES_SI_O_NO,0);	
			if(ask == 0){
				Invoke.invokeApplication(Invoke.APP_TYPE_PHONE, 
						new PhoneArguments(PhoneArguments.ARG_CALL, b.getTelefono()));
			}
			
		}catch (Exception e) {
			Dialog.alert(Strings.ERROR_GUARDAR_CONTACTO);
		}finally{
			if(contactList != null){
				try {
					contactList.close();
				} catch (PIMException e) {					
					e.printStackTrace();
				}
			}
		}
	}
	
}
