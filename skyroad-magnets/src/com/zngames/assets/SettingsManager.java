package com.zngames.assets;

import com.zngames.viewport.VirtualViewport;

public class SettingsManager {
	public static final String LDPI = "ldpi";
	public static final String MDPI = "mdpi";
	public static final String HDPI = "hdpi";
	public static final String XHDPI = "xhdpi";
	
	public static String dpi;

	public static String getDpi(){
		float mdpi_value = 32f;
		float width_scale_value = VirtualViewport.unit_scale_x;
		
		/*if(width_scale_value < mdpi_value){
			dpi = LDPI;
		}
		else if(width_scale_value >= mdpi_value && width_scale_value < mdpi_value*1.5f){
			dpi = MDPI;
		}
		else if(width_scale_value >= mdpi_value*1.5f && width_scale_value < mdpi_value*2f){
			dpi = HDPI;
		}
		else if(width_scale_value >= mdpi_value*2f){
			dpi = XHDPI;
		}
		else{
			// DEFAULT
			dpi = MDPI;
		}
		
		return dpi;*/
		return HDPI;
	}
}
