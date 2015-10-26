package com.pegsoluciones.blackberry.common.ui.field;

import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.EditField;



public class ExEditField extends EditField {

    int _color = Color.BLACK;
    int _font = Font.BOLD;
	private boolean multiLine;

    /**
     * @param label
     * @param ini
     * @param max
     * @param color
     * @param style
     * @param size
     */
    public ExEditField( String label, String ini, int max, int color, int style,int size) {
       
    	super( label, ini, max, 0);
        setFont(Font.getDefault().derive(style,size));
        _color = color;        
    }
    
    /**
     * @param label
     * @param ini
     * @param max
     * @param color
     * @param style font
     * @param size font
     * @param flag field
     * @param margenX
     * @param margenY
     */
    public ExEditField( String label, String ini, int max, int color,
    		int style,int size, long flag,int margenX,int margenY) {
        
    	super( label, ini, max, flag);
        setFont(Font.getDefault().derive(style,size));
        _color = color;     
        this.setMargin(new XYEdges(margenY,margenX,margenY,margenX));
    }
    
    /**
     * @param label
     * @param ini
     */
    public ExEditField( String label, String ini) {
        super( label, ini);
    }
    
    /**
     * @param label
     * @param ini
     * @param size font
     */
    public ExEditField(String label, String ini, int size) {
    	super( label, ini);
    	setFont(Font.getDefault().derive(_font,size));
	}

	/**
	 * @param label
	 * @param ini
	 * @param max de caracteres soportados
	 * @param color 
	 * @param style font
	 * @param size font
	 * @param flag parametros del field
	 * @param edges
	 */
	public ExEditField(String label, String ini, int max, int color, int style,
			int size, long flag,XYEdges edges) {
		
			this(label,ini,max,color,style,size,flag,1,1);
			this.setMargin(edges);
	}

	void setMultiLine(boolean b){
    	multiLine = b;
    }
    
   public boolean keyChar(char key,int status, int time){
		if(key == Characters.ENTER){
			return true;
		}
    	return this.keyControl(key, status, time);
    }

 
    
    public void paint( Graphics g ) {
        g.setColor( _color );
        super.paint( g );
    }

    protected boolean navigationMovement(int dx, int dy, int status, int time ) {            
    	if(multiLine && 0 != dx)
    		return true;    	
    	return super.navigationMovement(dx,dy,status,time);
    } 
    
} 
