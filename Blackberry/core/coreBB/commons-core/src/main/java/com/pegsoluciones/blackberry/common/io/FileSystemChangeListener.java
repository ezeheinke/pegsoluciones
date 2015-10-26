package com.pegsoluciones.blackberry.common.io;


import net.rim.blackberry.api.invoke.CameraArguments;
import net.rim.device.api.io.file.FileSystemJournal;
import net.rim.device.api.io.file.FileSystemJournalEntry;
import net.rim.device.api.io.file.FileSystemJournalListener;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.EventInjector.KeyEvent;



	

public class FileSystemChangeListener implements FileSystemJournalListener {
	
	private long _lastUSN;

   public void fileJournalChanged() {
        long nextUSN = FileSystemJournal.getNextUSN();

        for (long lookUSN = nextUSN - 1; lookUSN >= _lastUSN; --lookUSN) {
            FileSystemJournalEntry entry = FileSystemJournal.getEntry(lookUSN);
            if (entry == null) {
                break; 
            }
            final String path = entry.getPath();
            if (path != null) {
                if (path.endsWith("png") || path.endsWith("jpg") || path.endsWith("bmp") || path.endsWith("gif") ){
                    switch (entry.getEvent()) {
                        case FileSystemJournalEntry.FILE_ADDED:{
                            //either a picture was taken or a picture was added to the BlackBerry device
                            break;
                        }
                        case FileSystemJournalEntry.FILE_DELETED:
                            //a picture was removed from the BlackBerry device;
                            break;
                    }
                }
            }
        }
    }
    
    boolean killCameraApp = false;
    final String mCameraModuleName = "net_rim_bb_camera";
    final CameraArguments args = new CameraArguments();
    
    
   
    public void killCamera() {
    	injectKey(Characters.ESCAPE);
    	killCameraApp = false;
    }
    
    
    private void injectKey(char key) {
    	KeyEvent inject = new KeyEvent(KeyEvent.KEY_DOWN, key, 0);
    	inject.post();
    }
    
    public boolean isCameraRunning() {
    	boolean result = false;
    	ApplicationManager appMan = 
    	    	ApplicationManager.getApplicationManager();
    	ApplicationDescriptor[] appDes = appMan.getVisibleApplications();
    	for (int i = 0; i < appDes.length; i++) {
    		result = mCameraModuleName.equalsIgnoreCase(appDes[i]
    				.getModuleName());
    		if (result)
    			break;
    	}
    	return result;
    }

}