package com.pegsoluciones.blackberry.common.io;

import java.util.Vector;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;


public class PersistentStoreFacade {	
	

	public static Vector getRegs(){
		return (Vector)PersistentStore.getPersistentObject(("registro" + IOUtils.getNombreApp()).hashCode()).getContents();
		
	}
	
	private static void addReg(String nombre){
		Vector registrosApp = getRegs();
		
		if(registrosApp == null)
			registrosApp = new Vector();		
		
		// Para que no agrege registros duplicados
		if(registrosApp.contains(nombre))
				return;
		
		PersistentObject store = PersistentStore.getPersistentObject(("registro" + IOUtils.getNombreApp()).hashCode());
		registrosApp.addElement(nombre);
		synchronized(store){
			store.setContents(registrosApp);
			store.commit();
		}
	} 
	
	public static void removeReg(String nombre){
		Vector registrosApp = getRegs();
		registrosApp.removeElement(nombre);
		PersistentObject store = PersistentStore.getPersistentObject(("registro" + IOUtils.getNombreApp()).hashCode());
		synchronized(store){
			store.setContents(registrosApp);
			store.commit();
		}		
	} 
	
	public static void clearRegs(){
		
		Vector registrosApp1 = getRegs();
		
		if(registrosApp1 == null)
			return;
		
		int cant = registrosApp1.size();
		
		for(int i =0; i < cant;i++){
			String s = (String)registrosApp1.elementAt(i);
			borrar(s);
		}
					
		//borro el vector
		PersistentStore.destroyPersistentObject(("registro" + IOUtils.getNombreApp()).hashCode());	
				
	}

	public static void borrar(String propiedad){
		PersistentStore.destroyPersistentObject((propiedad + IOUtils.getNombreApp()).hashCode());		
	}	
	
	public static Object get(String propiedad){
        PersistentObject store =PersistentStore.getPersistentObject((propiedad + IOUtils.getNombreApp()).hashCode());
        return store.getContents();
	}	
	
	public static void recordar(String propiedad, Object valor){
        PersistentObject store =PersistentStore.getPersistentObject((propiedad + IOUtils.getNombreApp()).hashCode());
        synchronized(store){
        	store.setContents(valor);
        	store.commit();
      }		        	        
      addReg(propiedad);
   }
	
}
