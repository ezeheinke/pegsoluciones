package com.peg.blackberry.planetaboca.domain.bannerRunner;

import java.util.Calendar;
import java.util.Date;

import javax.microedition.pim.Event;
import javax.microedition.pim.EventList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMItem;

import net.rim.device.api.ui.component.Dialog;

import com.peg.blackberry.planetaboca.domain.Banner;
import com.peg.blackberry.planetaboca.domain.BannerRunner;
import com.peg.blackberry.planetaboca.util.Strings;

public class AgendarBannerRunner implements BannerRunner{

	public void run(Banner b) {
		try{
			long unDia = 24 * 60 * 60 * 1000;
			long start = Long.parseLong(b.getFecha());
			Date d = new Date(start + unDia);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			
			EventList el = (EventList)PIM.getInstance().openPIMList( PIM.EVENT_LIST, PIM.READ_WRITE );
			Event e = el.createEvent();
			e.addString( Event.SUMMARY, 0, b.getNombre() );
			e.addString( Event.LOCATION, 0, b.getDireccion());
			
			
			e.addDate(Event.START,0, start);      
			 if( el.isSupportedField( Event.ALARM ) )
			     e.addInt( Event.ALARM, PIMItem.ATTR_NONE, (int) (start - 60000) );
			Dialog.inform(Strings.AGENDAR_OK + b.getNombre());
		}catch (Throwable e) {
			Dialog.inform(Strings.AGENDAR_ERROR);
		}
	}

}
