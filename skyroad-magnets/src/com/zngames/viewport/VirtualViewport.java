package com.zngames.viewport;

public class VirtualViewport {

	public float ui_virtual_width = 480;
	public float ui_virtual_height = 800;
	
	public float world_x = 10;
	public float world_y = 15;
	
	public static float unit_scale_x;
	public static float unit_scale_y;
	
	public static float unit_ui_scale_x;
	public static float unit_ui_scale_y;
	
	public VirtualViewport(float screenWidth,float screenHeight){
		unit_scale_x = screenWidth/world_x;
		world_y = screenHeight/unit_scale_x;
		unit_scale_y = screenHeight/world_y;
		
		unit_ui_scale_x = ui_virtual_width/world_x;
		ui_virtual_height = (ui_virtual_width*(screenHeight/screenWidth));
		unit_ui_scale_y = unit_ui_scale_x;
	}
	
	public static float convertWorldWidthToUnit(float pixelW){
		return pixelW/unit_scale_x;
	}
	
	public static float convertWorldHeightToUnit(float pixelH){
		return pixelH/unit_scale_y;
	}
	
	public static float convertWorldWidthToPixel(float unitW){
		return unitW*unit_scale_x;
	}
	
	public static float convertWorldHeightToPixel(float unitH){
		return unitH*unit_scale_y;
	}
	
	public static float convertUIWidthToUnit(float pixelW){
		return pixelW/unit_ui_scale_x;
	}
	
	public static float convertUIHeightToUnit(float pixelH){
		return pixelH/unit_ui_scale_y;
	}
	
	public static float convertUIWidthToPixel(float unitW){
		return unitW*unit_ui_scale_x;
	}
	
	public static float convertUIHeightToPixel(float unitH){
		return unitH*unit_ui_scale_y;
	}
	
}
