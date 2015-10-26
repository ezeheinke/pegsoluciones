package com.pegsoluciones.blackberry.common.device;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

import com.pegsoluciones.blackberry.common.ui.screen.DialogClosedListenerImplement;

public abstract class ScreenUtil {

	public static int getScreenCount() {
		return UiApplication.getUiApplication().getScreenCount();
	}
	
	public static void closeActiveScreens(int cant) {
		for(int i = 1; i <= cant; i++)
			closeActiveScreen();		
	}
	
	public static void closeScreensHastaQueQueden(int i) {
		while(UiApplication.getUiApplication().getScreenCount() != 1)
			closeActiveScreen();
	}

    public static void pushScreen( Screen scr ) {
    	UiApplication.getUiApplication().pushModalScreen( scr );    
    }
    
    public static void salir(){
    	while(true)
    		getActiveScreen().close();
    }
    
    public static void Dialog( final String msg ) {
        
    	UiApplication.getUiApplication().invokeLater( new Runnable() {
            public void run() {
                Dialog.alert( msg );
            }
        } );
                
    }    
	
	public static Screen getActiveScreen(){
    	return UiApplication.getUiApplication().getActiveScreen();
    }
	
    public static void closeActiveScreen(){
    	UiApplication.getUiApplication().getActiveScreen().close();
    }	
	
	public static void showDialogYSalir(String msj) {
		Object[] opc = {"OK"}; 
		final Dialog dialog = new Dialog(msj, opc, null, -1, null);
		
		dialog.show();
		dialog.setDialogClosedListener(new DialogClosedListenerImplement(){
			public void onClose(Dialog dialog,int choice){
				salir();
			}
		});
	}    
	
    /**
     * 
     * @param pantalla que se sacara del stack de pantallas
     */
    public static void popScreen(Screen scr){
    	UiApplication.getUiApplication().popScreen(scr);
    }
    
    /**
     * 
     * Muestra popup pregntando si de desea salir, si acepta la app se cerrar�
     * en el caso contrario se cerrar� unicamente le popup
     * 
     */
    public static void deseaSalir(){    	
	    int res = Dialog.ask(Dialog.D_YES_NO, "Está seguro que desea salir?");
	    if (res == Dialog.YES)
	     salir();      	    
   	 }    
    
}
