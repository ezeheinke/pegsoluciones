package com.peg.blackberry.planetaboca.ui.field;

import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.UiApplication;

import com.peg.blackberry.planetaboca.application.ApplicationEvent;
import com.peg.blackberry.planetaboca.application.ApplicationFactory;
import com.peg.blackberry.planetaboca.domain.Banner;
import com.peg.blackberry.planetaboca.service.BannerService;
import com.pegsoluciones.blackberry.common.event.EventManager;
import com.pegsoluciones.blackberry.common.event.EventObserver;
import com.pegsoluciones.blackberry.common.service.ServiceError;
import com.pegsoluciones.blackberry.common.service.ServiceExecutionListener;
import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.ui.field.BitmapFieldConMarco;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;

/**
 * @author Pablo Poza
 *
 */
public class BannerField extends BitmapFieldConMarco implements ServiceExecutionListener
	, EventObserver{

	private static final int ALTO_BANNER = PosicionesUtil.UbicarEn(35, 45, 45, 10);
	static private BannerField instance;
	private int nroBanner = 0;
	Vector bannersDescargados = new Vector();
	int cantidadBanner;
	private Thread threadRotador;
	private boolean isFocusable = false;
	protected Banner banner;	
	
	private BannerField(){
		super(Color.BLUE);
		this.setBitmap(new Bitmap(ScreenInfo.getScreenWidth(), ALTO_BANNER));
		
		BannerService bannerService = ApplicationFactory.getInstance().getBannerService();
		bannerService.getBanners(this);
		
		threadRotador = new Thread(){
			public void run(){
				int i = 0;
				while(true){
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if(bannersDescargados.size() == 0)
						continue;
					nroBanner = i++ % bannersDescargados.size();
					
					banner = ((Banner)bannersDescargados.elementAt(nroBanner));
					synchronized(UiApplication.getUiApplication().getAppEventLock()){
						BannerField.this.setBitmap(banner.getImagen());
					}
									
				}
			}
		};		
		EventManager.getInstance().addObserver(this, ApplicationEvent.BANNER_DESCARGADO);
	}

	public boolean trackwheelClick(int status, int time ){    
		if(isFocusable)
			banner.onClick();
        return true;
	}	
	
	
	public int getAltoBanner() {
		return ALTO_BANNER;
	}
	

	static public BannerField getInstance(){
		if(instance == null)
			instance = new BannerField();
		return instance;
	}
	
	public void onError(ServiceError arg0) {		
	}
	
	public void onCallComplete(Object rta) {
		Vector banners = (Vector) rta;
		cantidadBanner = banners.size();				
	
		if (cantidadBanner > 0) {
			if(cantidadBanner > 0 && !threadRotador.isAlive())
				threadRotador.start();
			
			for(int i = 0 ; i < banners.size() ; i++){
				Banner banner = ((Banner)banners.elementAt(i));
				banner.descargarImagen();
			}
			isFocusable = true;		
		}
			
	}

	public void onEvent(String topic, Object parameter) {
		bannersDescargados.addElement(parameter);
		invalidate();
	}
	
	public boolean isFocusable() {
		return isFocusable ;
	}

}