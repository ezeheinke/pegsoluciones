package com.pegsoluciones.blackberry.common.screenEnManager;

import java.util.Stack;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Screen;

/**
 * @author Poza Pablo
 *
 * Permite usar manger "como pantallas"
 * 
 * el fiel que se mostrara puede implementar onCloseableInterface
 * asi se le avisa cuando se va a "cerrar"
 *
 */
public class SimulaScreens {
	
	private static Screen mainScreen;
	private static Field fieldActivo;
	private static Stack fields = new Stack();
	
	/**
	 *  setea el field o manager "activo" en la pantalla tendria ser inicializado
	 *  una unica vez, este field ya tiene que estar agredado a la pantalla ya 
	 *  que este va ser el que se va ir reemplanzando por los fields a los que se
	 *  le hagan push o lo que queden en la pila despues de "cerrar" algun field. 
	 *  
	 *  Tiene que llamarse si o si antes de hacer un push
	 * 
	 * @param field
	 * @return 
	 */
   public static Field setInitialField(Field field) {
	  fields.push(field);
	  return fieldActivo = field;
   }

   public static boolean siCierroPantallaSeCierraApp(){
	   return fields.size() == 1;
   }   
   
	/**
	 * Retorna true si queda un ultimo manager en la pila
	 * Debe llamarse en el onClose de la pantalla que se le
	 * setea a SimulaScreen
	 * */
   public static boolean onClose(){
	   
		// le aviso al primer fiel de la pila, si es OnCloseable , que va a "se cierra"	    
		try{
			Field f = (Field) fields.lastElement();
			Object o = f;
			((OnCloseableInterface)o).onClose();
			f = null;
			o = null;			
			System.out.println("El field era onCloseable le aviso que va a cierrar");
		}catch(ClassCastException e){
			System.out.println("El field no era onCloseable");
		}
	   
	   if( fields.size() == 1)
			return true;
	   else{
		   fields.pop();
		   mainScreen.replace(fieldActivo, (Field) fields.lastElement());
		   fieldActivo = (Field) fields.lastElement();
		   fieldActivo.setFocus();
		   return false;
	   }
   }
	
	
	/**
	 * Setea el screen en donde se van ir cambiando los manager
	 * 
	 * hay que llamarla si o si antes de un push
	 * 
	 * @param scr
	 * @return retorna la scr que viene como parametro
	 */
	public static Screen setScreen(Screen scr){
		return mainScreen = scr;		
	}
	
	
	/**
	* pone a f en la pilla de fields, despues reemplaza al field 
	* activo por f 
	*/
	static public void pushManager(Field f) {
		fields.push(f);
		mainScreen.replace(fieldActivo, f );
		fieldActivo  = f;
	}
	
	/**
	* Saca a f de la pila, si existe otro en la pila lo pone como
	* activo
	*/
	static public void popManager(Field f){
		fields.removeElement(f);

		// le aviso al fiel, si es OnCloseable, que va a "se cierra"
		
		if( f instanceof OnCloseableInterface){
			Object o = f;
			((OnCloseableInterface)o).onClose();
		}
		
		if(fields.size() == 0)
			System.exit(0);
	}

	/**
	 * Levanta a f y despues saca de la stack al
	 * field que esta anterirormente como 
	 * activo
	 * 
	 * @param f
	 */
	static public void popActiveAndPush(Field f){
		Field fieldActivoAnterior = fieldActivo;  
		pushManager(f);
		popManager(fieldActivoAnterior);
	}

	/**
	 * saca fields/managers hasta que quede i en la pila 
	 * */
	public static void popHasta(int i) {
		while(fields.size() != i)
			onClose();		
	}

	/**
	 * @param i: cierra i "pantallas"
	 */
	public static void pop(int i) {
		for(int j = 1 ; j <= i ; j++)
			onClose();				
	}
	
	
}
