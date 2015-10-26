/**
 * 
 * 
 * Modificada para que la barra espaciadora haga click...
 * (se agrego keyChar)
 * 
 */


package com.pegsoluciones.blackberry.common.ui.field;
import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.BitmapField;

import com.pegsoluciones.blackberry.common.ui.ManagerBotones;
import com.pegsoluciones.blackberry.common.util.BitmapUtil;


/**
 * @author poza pablo
 * 
 * Boton que permite tener un imagenes para focus y unfocus
 * hay que sobrescribirle el metodo onClick para que haga algo al
 * hacerle click
 */
public class ImageButtonField extends BitmapField {
        
        public String nombre;
        public Vector presionados;
        public Bitmap bmpOFF;
        public Bitmap bmpON;
        private String tag;

        private boolean focusable = true;

        
        public boolean apretado = false;
		protected Bitmap bpmApretado;
		public ManagerBotones mb;
        

		/////////// Constructores ///////////////////////////// 		
      
		/**
		 * @param bmpOFF
		 * @param bmpON
		 */
		public ImageButtonField( Bitmap bmpOFF, Bitmap bmpON) {
            super( bmpOFF, BitmapField.FOCUSABLE );
            this.bmpOFF = bmpOFF;
            this.bmpON  = bmpON;
            apretado = false;
        }
		
        /**
         * @param bmpOFF
         * @param bmpON
         * 
         * bmpOFF y bmpON son los nombres de las images, que se encuentra en el
         * directorio seteado a ScreenInfo
         */
        public ImageButtonField( String bmpOFF, String bmpON ) {
            this( BitmapUtil.getImagen(bmpOFF), BitmapUtil.getImagen(bmpON));
        }        
     
        /**
         * Crea el boton usando las imagenes name + "ROLL.png" , name + "OFF.png" 
         * y name + "ON.png", la imagen "ON.png", no es obligatoria
         * */
        public ImageButtonField(String name) {
			this(name + "OFF.png",name + "ROLL.png");		
			bpmApretado = BitmapUtil.getImagen(name + "ON.png");
			bmpOFF=BitmapUtil.getImagen(name +"OFF.png");
			nombre=name;
		}        
     
        /**
         * @param name
         * @param mb ManagerBotones
         */
        public ImageButtonField(String name,ManagerBotones mb){
        	this(name);
        	setManagerBotones(mb);
        	mb.addElementBotton(this);
        }
       
        /**
         * @param bmpOFF
         * @param bmpON
         * @param mb2
         */
        public ImageButtonField(Bitmap bmpOFF, Bitmap bmpON, ManagerBotones mb2) {
			this(bmpOFF,bmpON);
			mb = mb2;
			mb.addElementBotton(this);
		}

        public void setBitmaps(String name) {
			setBitmaps(BitmapUtil.getImagen(name + "OFF.png"),BitmapUtil.getImagen(name + "ROLL.png"));		

		}        

		public boolean isFocusable() {
            return focusable;
        }
		
		public void setFocusable(boolean b){
			focusable = b;
		}

        public void onFocus(int direction) {           
            setBitmap( bmpON );
        }
    
        public boolean isApretado() {
			return apretado;
		}
        
        public boolean keyChar(char key,int status, int time){					 
			if (key == Characters.SPACE )		
				this.onClick();			
			return this.keyControl(key, status, time);
		}
        
        
        public void setManagerBotones(ManagerBotones mb){
        	this.mb = mb;
        }
               
        
        public void onUnfocus() {
            super.onUnfocus( );            
            setBitmap( bmpOFF );
        }
        
        public String getName(){
        	return nombre;
        }
        
        public String getTag() {
			return tag;
		}

		public void set_tag(String tag) {
			this.tag = tag;
		}                       
		
        protected  void drawFocus(Graphics graphics, boolean on) {
                drawHighlightRegion(graphics, Field.HIGHLIGHT_FOCUS, false, 0, 0, 0, 0);                  
        }

        public boolean trackwheelClick(int status, int time) {             
            onClick();            
            if(mb != null)
            	mb.OnClick(this);
            else{
                apretado = !apretado;
                if(bpmApretado != null){            		
                	Bitmap b = bmpOFF;
                	bmpOFF = bpmApretado;
                	bpmApretado = b;            		
                }
            }
            return true;
        }
        
        
        protected  boolean keyDown( int keycode, int time ) {            
            if ( Keypad.key( keycode ) == Keypad.KEY_ENTER )                    
            	return trackwheelClick(0,0);
            else
            	return super.keyDown(keycode, time);
        } 
        
        public void onClick(){
        	//sobre escribir
        }
        
        
		public Bitmap getBitmap(){            
            if (isFocus())
                return bmpON;
            else 
                return bmpOFF;         
        }

      public void setBitmaps( Bitmap off, Bitmap on ) {        
        bmpOFF = off;
        bmpON = on;
        setBitmap( bmpON );        
     }       
     
     public void setBitmaps(String off, String on ) {
    	setBitmaps(BitmapUtil.getImagen(off),BitmapUtil.getImagen(on));
     }           
      

	public void changeEstado() {
		Bitmap b = bmpOFF;
		bmpOFF = bpmApretado;
		bpmApretado = b;
		apretado = !apretado;
		setBitmap(bmpOFF);
	}

	public void apagate() {		
		if (isApretado())	
			changeEstado(); 
	}

	public void prendete() {
		if(!isApretado())
			changeEstado();
	}



}
