package com.pegsoluciones.blackberry.common.concurrent;

import java.util.Vector;

/**
 * This is a one-thread pool which implements a queue
 * of task.
 * 
 * @author PEG Soluciones S.A.
 * @since 1.0
 */
public class TaskWorker implements Runnable {

	private boolean quit = false;  
    private Vector queue = new Vector();  
	
    public TaskWorker() {  
        new Thread(this).start();  
    }  
  
    /**
     * Returns the next element if any.
     * @return the task
     */
    private Task getNext() {  
        
    	Task task = null;  
        if (!queue.isEmpty()) {  
            task = (Task) queue.firstElement();  
        }  
        return task;  
    }  
    
    /**
     * 
     */
    public void run() {
    	
        while (!quit) {  
        
        	Task task = getNext();
            if (task != null) {  
                task.execute();  
                queue.removeElement(task);  
            } else {
            	
            	// task is null and only reason 
            	// will be that vector has no more tasks  
                synchronized (queue) {  
                    try {  
                        queue.wait();  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
    }  
    
    /**
     * 
     * @param task
     */
    public void addTask(Task task) {  
    	synchronized (queue) {  
    		if (!quit) {  
    			queue.addElement(task);  
                queue.notify();  
            }  
  
        }  
    }  
    
    /**
     * 
     * 
     */
    public void quit() {  
        synchronized (queue) {  
            quit = true;  
            queue.notify();  
        }  
    }  
}  
