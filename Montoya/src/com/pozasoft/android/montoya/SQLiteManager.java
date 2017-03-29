package com.pozasoft.android.montoya;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.text.format.Time;

public class SQLiteManager extends SQLiteOpenHelper {

	private static SQLiteManager instance;
	
	public static SQLiteManager getInstance(Context context){
		if(instance == null)
			instance = new SQLiteManager(context);
		return instance;
	}
	
	private SQLiteManager(Context context) {
		super(context, "santiago_montoya", null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE altas    (nombre TEXT, apellido TEXT,mail TEXT,localidad TEXT, telefono TEXT)");
        db.execSQL("CREATE TABLE enviados (nombre TEXT, apellido TEXT,mail TEXT,localidad TEXT, codigo telefono)");
        db.execSQL("CREATE TABLE encuesta (pregunta INT,r1 INT,r2 INT,r3 INT,r4 INT,r5 INT,r6 INT,r7 INT,r8 INT,r9 INT)");
        db.execSQL("CREATE TABLE encuesta_enviadas (pregunta INT,r1 INT,r2 INT,r3 INT,r4 INT,r5 INT,r6 INT,r7 INT,r8 INT,r9 INT)");

		
		for(int i = 1; i < 6 ; i++){
			ContentValues values = new ContentValues();
			values.put("pregunta", i);
	        for(int j = 1; j <10 ; j++){ 
				values.put("r" + j  , 0); 
	        }
			db.insert("encuesta",null,values); 
			db.insert("encuesta_enviadas",null,values); 
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS altas");
		db.execSQL("DROP TABLE IF EXISTS enviados");
		db.execSQL("DROP TABLE IF EXISTS encuesta");
		db.execSQL("DROP TABLE IF EXISTS encuesta_enviadas");		
        this.onCreate(db);
	}
	
	

	public void borrarAltasYPasarAEnviados() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("insert into enviados select * from altas");
		db.execSQL("delete from altas");
		db.execSQL("insert into encuesta_enviadas select * from encuesta");
		db.execSQL("delete from encuesta");
		db.close();
	}
	
	// Alerta.
	public void addAlta(String nombre ,String apellido,String mail,String localidad,String telefono){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("nombre"  , nombre);
		values.put("apellido", apellido); 
		values.put("mail"    , mail); 
		values.put("localidad"    , localidad); 
		values.put("telefono"  , telefono); 

		db.insert("altas",null,values); 
		
		db.close(); 
	}
	
	public void addRespuesta(String pregunta ,String respuesta){
		String rta = "r" + respuesta;
        String query = "update encuesta set " + rta + " = 1 + " + rta +
        			   " where pregunta = " + pregunta;
        
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
	}	

	public List<String> getAllAltas(Context context) {
		
		String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        List<String> altas = new LinkedList<String>();
 
        String query = "SELECT * FROM altas";
        String encuestas = "select * from encuesta";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
        	
        	File file = new File(path + "/" + getTimeStamp() + "-santiago-montoya"  + ".txt");


    		FileOutputStream stream = null;
    		try {
    			stream = new FileOutputStream(file);
    			
    		    db = this.getWritableDatabase();
    		    Cursor c = db.rawQuery(encuestas, null);
    		    c.moveToFirst();
    			do{
	    		    stream.write(("Pregunta nro: " + c.getString(0)).getBytes());
	    		    stream.write(("  Respuestas 1:" + c.getString(1)).getBytes());
	    		    for (int i = 2; i < 10 ;i++) {
	    		    	stream.write((", " + (i) + ":" + c.getString(i)).getBytes());
					}
	    		    stream.write("\n".getBytes());
    			} while(c.moveToNext());
    			
    		    stream.write("\n\n\n".getBytes());
    			do {
                	stream.write((cursor.getString(0) + " , " +
                				  cursor.getString(1) + " , " +
                				  cursor.getString(2) + " , " +
                				  cursor.getString(3) + " , " +
                				  cursor.getString(4) +
                             	  "\n").getBytes());
                } while (cursor.moveToNext());
    			
    		}catch(Exception e){
    			System.out.println(e);
    		}finally {
    		    try {
    		    	if(stream != null)
    		    		stream.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
        	
            
        }
        db.close();
        return altas;
	}
	
	
	public List<String> getAllEnviados(Context context) {
		
		String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        List<String> altas = new LinkedList<String>();
 
        String query = "SELECT * FROM enviados";
        String encuestas = "select pregunta,sum(r1),sum(r2),sum(r3),sum(r4)," +
        		"sum(r5),sum(r6),sum(r7),sum(r8),sum(r9) from encuesta_enviadas" +
        		" group by pregunta";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
        	
        	File file = new File(path + "/" + getTimeStamp() + "-santiago-montoya-histotial"  + ".txt");


    		FileOutputStream stream = null;
    		try {
    			stream = new FileOutputStream(file);
    			
    		    db = this.getWritableDatabase();
    		    Cursor c = db.rawQuery(encuestas, null);
    		    c.moveToFirst();
    			do{
	    		    stream.write(("Pregunta nro: " + c.getString(0)).getBytes());
	    		    stream.write(("  Respuestas 1:" + c.getString(1)).getBytes());
	    		    for (int i = 2; i < 10 ;i++) {
	    		    	stream.write((", " + (i) + ":" + c.getString(i)).getBytes());
					}
	    		    stream.write("\n".getBytes());
    			} while(c.moveToNext());
    			
    		    stream.write("\n\n\n".getBytes());
    			do {
                	stream.write((cursor.getString(0) + " , " +
                				  cursor.getString(1) + " , " +
                				  cursor.getString(2) + " , " +
                				  cursor.getString(3) + " , " +
                				  cursor.getString(4) +
                             	  "\n").getBytes());
                } while (cursor.moveToNext());
    			
    		}catch(Exception e){
    			System.out.println(e);
    		}finally {
    		    try {
    		    	if(stream != null)
    		    		stream.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
        	
            
        }
        db.close();
        return altas;
	}
	
	public static String getTimeStamp() {
        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y_%m_%d_%H_%M");
        return sTime;
    }

}
