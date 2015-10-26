package com.pegsoluciones.blackberry.common.ui.screen;

import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.WLANInfo;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.container.FullScreen;
/**
 * 
 */
public class ConexionesTestScreen extends FullScreen{
    
    CheckboxField c1;
    CheckboxField c2;
    CheckboxField c3;
    CheckboxField c4;
    CheckboxField c5;
    CheckboxField c6;
    static String url = "";
    static String contra = "";
    static String usuario = "";
    
  
    public ConexionesTestScreen() {    
        super( DEFAULT_CLOSE | DEFAULT_MENU ); 
        add(c1 = new CheckboxField("WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED",WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED));
        add(c2 = new CheckboxField("CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_BIS_B)",CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_BIS_B)));
        add(c3 = new CheckboxField("RadioInfo.getSignalLevel( RadioInfo.WAF_WLAN ) != RadioInfo.LEVEL_NO_COVERAGE" ,RadioInfo.getSignalLevel( RadioInfo.WAF_WLAN ) != RadioInfo.LEVEL_NO_COVERAGE));       
        add(c4 = new CheckboxField("CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_MDS) == CoverageInfo.COVERAGE_MDS",(CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_MDS) == CoverageInfo.COVERAGE_MDS));
        add(c5 = new CheckboxField("(CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_DIRECT) == CoverageInfo.COVERAGE_DIRECT",(CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_DIRECT) == CoverageInfo.COVERAGE_DIRECT));
        add(c6 = new CheckboxField("CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_BIS_B)",CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_BIS_B)));
    }
    

} 
