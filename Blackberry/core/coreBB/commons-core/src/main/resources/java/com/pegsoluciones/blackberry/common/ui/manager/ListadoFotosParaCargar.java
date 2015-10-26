package com.pegsoluciones.blackberry.common.ui.manager;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;

import com.pegsoluciones.blackberry.common.device.ScreenUtil;
import com.pegsoluciones.blackberry.common.ui.ScreenInfo;
import com.pegsoluciones.blackberry.common.ui.field.BitmapFieldConMarco;
import com.pegsoluciones.blackberry.common.ui.field.ImageButtonField;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;
import com.pegsoluciones.blackberry.common.web.ImagesDownloader;



public class ListadoFotosParaCargar extends Manager {
	
	HorizontalFieldManager fotos;
	String folderPath = null;
	public static int w = ScreenInfo.getScreenWidth() - 60 ;
	public static int h = ScreenInfo.getScreenHeight() - PosicionesUtil.UbicarEn(100, 150, 180, 20) ;	
	private String imagenSeleccionada = "...";
	public static int margen = 20;
	
	public String getImagenSeleccionada() {
		return imagenSeleccionada;
	}

	ListadoFotosParaCargar listadoFotosParaCargar = this;
	private Vector folderPaths;
	ImageButtonField cancelar;
	private ImageButtonField aceptar;
	//private int hBoton = UtilPosiciones.ubicarSegunSO(30,UtilPosiciones.ubicarEn(50, 25, 60, 12));
	//private int wBoton = UtilPosiciones.ubicarSegunSO(80,UtilPosiciones.ubicarEn(110, 15, 150, 12));
	private final static int sizeFont = PosicionesUtil.UbicarEn(14, 15, 16, 12);
	private final static int yListado = PosicionesUtil.UbicarEn(20, 40, 30, 25);
	private final static int wFotito = PosicionesUtil.UbicarEn(50, 60, 70, 30);
	private final static int hFotito = PosicionesUtil.UbicarEn(50, 60, 70, 30);
	/**
	 * @param nombre: nombre del titulo
	 * @param path: path carpeta
	 */
	public ListadoFotosParaCargar(final String nombreMedio, final String path){
		super(Field.FOCUSABLE);
		fotos = new HorizontalFieldManager(Manager.HORIZONTAL_SCROLL);
		add(fotos);
		drawManager();
		this.folderPath = path;
		addElementFotos();			
	}

	private void drawManager() {		
		
		add(cancelar = new ImageButtonField("album_detalle/pop_cancelar"){
			
			public void onClick(){
				ScreenUtil.closeActiveScreen();				
			}
			
		});		
		
		add(aceptar = new ImageButtonField("album_detalle/pop_aceptar"){
			
			public void onClick(){
				actionAceptar();
			}
		});
	}

	//Sobre escribir
	public void actionAceptar() {
		
	}
	
	public ListadoFotosParaCargar(final String nombreMedio, final Vector paths,int sobreCarga){
		super(Field.FOCUSABLE);
		fotos = new HorizontalFieldManager(Manager.HORIZONTAL_SCROLL);
		add(fotos);
		drawManager();
		this.folderPaths = paths;
		addElementFotos();		
	}
	
	
	private void addElementFotos() {
		
		Vector v = new Vector();
		
		if(this.folderPath != null)
			v= readRoots(this.folderPath);
		else
			addElementFotos(v);
		
		
		for(int i = 0 ; i < v.size() ; i++){
			String pathFoto = (String)v.elementAt(i);
			
			Bitmap b = ImagesDownloader.bitmapFromFileWithSize(pathFoto,wFotito,hFotito);
			fotos.add(new BitmapFieldConMarco( b,Color.WHITE,pathFoto){				
				public boolean trackwheelClick(int status, int time){
					String s = "...";
					String path = this.getPathFoto();
					if(!path.equals(imagenSeleccionada))
						s = path;				
					imagenSeleccionada = s;
					listadoFotosParaCargar.invalidate();
					return true;
				}
				
			});
		}
	}
		
	/**
	 * recibe el vector que va tener  s los paths
	 * recorre el vector folderPaths y obtiene el path
	 * de  s los archivos de esas carpetas
	 * 
	 * @param v
	 */
	private void addElementFotos(Vector v) {
		for(int i = 0 ; i < folderPaths.size() ; i++){
			Vector v1 = readRoots((String)folderPaths.elementAt(i));
			addElementAll(v,v1);
		}
			
	}
		
	public static void addElementAll(Vector v, Vector v1) {
		
		for(int i = 0 ; i < v1.size() ; i++){
			String s = (String)v1.elementAt(i);
			System.out.println(s);
			if(!s.endsWith("/"))
				v.addElement(s);
		}
	}

	public void paint(Graphics g){
		g.setBackgroundColor(Color.BLACK);
		g.clear();
		g.setColor(Color.WHITE);
		GraphicUtil.setSizeFont(g, 14);
		g.drawText("Imagen seleccionada: " + imagenSeleccionada, margen, h - cancelar.getBitmapHeight() - 15 - 12,0,w-margen*2);
		super.paint(g);
		GraphicUtil.drawMarco(g, w, h, Color.WHITE);
	}
		
	public void onUnfocus(){
		invalidate();
		super.onUnfocus();
	}
		
    public Vector readRoots(String root)
    {

    	Vector v = new Vector();

        FileConnection fc = null;
        Enumeration rootEnum = null;

        if (root != null) 
        {
            // Open the file system and get the list of directories/files.
            try 
            {
                fc = (FileConnection)Connector.open("file:///" + root);
                rootEnum = fc.list();
            } 
            catch (Exception ioex) 
            {
            	Status.show("Error: " + ioex.getMessage());
      
            	
            } 
            finally 
            {
                
                if (fc != null) 
                {   
                    // Everything is read, make sure to close the connection.
                    try 
                    {
                        fc.close();
                        fc = null;
                    } 
                    catch (Exception ioex) 
                    {
                    }
                }
            }
        }

        // There was no root to read, so now we are reading the system roots.
        if (rootEnum == null) 
        {
            rootEnum = FileSystemRegistry.listRoots();
        }

        // Read through the list of directories/files.
        while (rootEnum.hasMoreElements()) 
        {
            String file = (String)rootEnum.nextElement();
            System.out.println("File: " + file);
            if (root != null) 
            {
                file = root + file;
                
                v.addElement(file);
            }
            
        }
        
        
        return v;
    }

	protected void sublayout(int width, int height) {
		
		setPositionChild(cancelar,w/2 - cancelar.getBitmapWidth() - 5,this.h - cancelar.getBitmapHeight() - 5);
		layoutChild(cancelar,cancelar.getBitmapWidth(),cancelar.getBitmapHeight());
		
		setPositionChild(aceptar,w/2 + 5,this.h - cancelar.getBitmapHeight() - 5);
		layoutChild(aceptar,aceptar.getBitmapWidth(),aceptar.getBitmapWidth());
		
		setPositionChild(fotos,margen,yListado);
		layoutChild(fotos,w - 2*margen,hFotito);
		
		
		setExtent(w,h);		
	}
  
	
}
