package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;

public class Hole extends Circle {
	boolean bridged;
	float bridgeStartX;
	float bridgeStartY;
	float bridgeEndX;
	float bridgeEndY;
	
	public Hole(float x, float y, float radius){
		super(x,y,radius);
		bridged = false;
	}
	
	public Hole(float x, float y, float radius, boolean bridged){
		super(x,y,radius);
		if(bridged){
			bridged = true;
			double angle = MathUtils.random(MathUtils.PI);
			double cosAngle = Math.cos(angle);
			double sinAngle = Math.sin(angle);
			bridgeStartX = (float) (x + radius*cosAngle);
			bridgeStartY = (float) (y + radius*sinAngle);
			bridgeEndX = (float) (x - radius*cosAngle);
			bridgeEndY = (float) (y - radius*sinAngle);
			//x1 = bridgeStartX - World.bridgeWidth*sinAngle*0.5f;
			//y1 = bridgeStartY + World.bridgeWidth*cosAngle*0.5f;
			//x1 = bridgeStartX + World.bridgeWidth*sinAngle*0.5f;
			//x1 = bridgeStartY - World.bridgeWidth*cosAngle*0.5f;
			//x1 = bridgeStopX - World.bridgeWidth*sinAngle*0.5f;
			//x1 = bridgeStopY + World.bridgeWidth*cosAngle*0.5f;
			//x1 = bridgeStopX + World.bridgeWidth*sinAngle*0.5f;
			//x1 = bridgeStopY - World.bridgeWidth*cosAngle*0.5f;
		}else{
			bridged = false;
		}
	}
}
