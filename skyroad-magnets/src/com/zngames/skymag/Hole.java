package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;

@SuppressWarnings("serial")
public class Hole extends Circle {
	boolean bridged;
	float bridgeStartX;
	float bridgeStartY;
	float bridgeEndX;
	float bridgeEndY;
	double angle;
	double cosAngle;
	double sinAngle;
	
	public Hole(float x, float y, float radius){
		super(x,y,radius);
		bridged = false;
	}
	
	public Hole(float x, float y, float radius, boolean bridged){
		super(x,y,radius);
		this.bridged = bridged;
		if(bridged){
			if(x-radius < World.getLeftBorderXCoordinate()){
				float leftLimit = (float) Math.min(Math.acos((World.getLeftBorderXCoordinate()-x)*1.0/radius), MathUtils.PI*3.0/4);
				angle = MathUtils.random(-leftLimit, leftLimit);
			} else if(x+radius > World.getRightBorderXCoordinate()){
				float rightLimit = (float) Math.max(Math.acos((World.getRightBorderXCoordinate()-x)*1.0/radius), MathUtils.PI*1.0/4);
				angle = MathUtils.random(rightLimit, -rightLimit);
			} else{
				angle = MathUtils.random((float) (MathUtils.PI*1.0/4), (float) (MathUtils.PI*3.0/4));
			}
			cosAngle = Math.cos(angle);
			sinAngle = Math.sin(angle);
			bridgeStartX = (float) (x + radius*cosAngle);
			bridgeStartY = (float) (y + radius*sinAngle);
			bridgeEndX = (float) (x - radius*cosAngle);
			bridgeEndY = (float) (y - radius*sinAngle);
		}
	}
	public boolean isBridged(){
		return bridged;
	}
}
