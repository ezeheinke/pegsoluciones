package com.pegsoluciones.blackberry.common.device;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.WLANInfo;

public abstract class StringConexionUtil {

	   public static String urlConexion(String url){      
	        String connectionString = null;                
	        
	        // Simulator behavior is controlled by the USE_MDS_IN_SIMULATOR variable.
	        if(DeviceInfo.isSimulator())
	        {
	        	connectionString = ";deviceside=true";                 
	        }                 
	       
	        // Wifi is the preferred transmission method
	        else if(WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED)
	        {
	            connectionString = ";interface=wifi";
	        }               
	        // Is the carrier network the only way to connect?
	        else if((CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_DIRECT) == CoverageInfo.COVERAGE_DIRECT)
	        {
	            String carrierUid = getCarrierBIBSUid();
	            if(carrierUid == null) 
	            {
	                // Has carrier coverage, but not BIBS.  So use the carrier's TCP network
	                connectionString = ";deviceside=true";
	            }
	            else 
	            {
	                // otherwise, use the Uid to construct a valid carrier BIBS request
	                connectionString = ";deviceside=false;connectionUID="+carrierUid + ";ConnectionType=mds-public";
	            }
	        }
	        // Check for an MDS connection instead (BlackBerry Enterprise Server)
	        else if((CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_MDS) == CoverageInfo.COVERAGE_MDS)
	        {
	            connectionString = ";deviceside=false";
	        }
	        // If there is no connection available abort to avoid bugging the user unnecssarily.
	        else if(CoverageInfo.getCoverageStatus() == CoverageInfo.COVERAGE_NONE)
	        {
	        }
	        // In theory, all bases are covered so this shouldn't be reachable.
	        else
	        {
	            connectionString = ";deviceside=true";
	        }        
	        return url + connectionString;
	    }
	    
	    
	    
	    private static String getCarrierBIBSUid()
	    {
	        net.rim.device.api.servicebook.ServiceRecord[] records = ServiceBook.getSB().getRecords();
	        int currentRecord;        
	        for(currentRecord = 0; currentRecord < records.length; currentRecord++)
	        {
	            if(records[currentRecord].getCid().toLowerCase().equals("ippp"))
	            {
	                if(records[currentRecord].getName().toLowerCase().indexOf("bibs") >= 0)
	                {
	                    return records[currentRecord].getUid();
	                }
	            }
	        }        
	        return null;
	    }
	    
	
}
