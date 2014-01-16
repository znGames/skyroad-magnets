package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;

@SuppressWarnings("serial")
public class Hole extends Circle {
	boolean bridged;
	Polygon bridge;
	
	public Hole(float x, float y, float radius){
		super(x,y,radius);
		bridged = false;
	}
	
	public Hole(float x, float y, float radius, boolean bridged){
		super(x,y,radius);
		this.bridged = bridged;
		if(bridged){
			double angle;
			if(x-radius < World.getLeftBorderXCoordinate()){
				float leftLimit = (float) Math.min(Math.acos((World.getLeftBorderXCoordinate()-x)*1.0/radius), MathUtils.PI*3.0/4);
				angle = MathUtils.random(-leftLimit, leftLimit);
			} else if(x+radius > World.getRightBorderXCoordinate()){
				float rightLimit = (float) Math.max(Math.acos((World.getRightBorderXCoordinate()-x)*1.0/radius), MathUtils.PI*1.0/4);
				angle = MathUtils.random(rightLimit, -rightLimit);
			} else{
				angle = MathUtils.random((float) (MathUtils.PI*1.0/4), (float) (MathUtils.PI*3.0/4));
			}
			double cosAngle = Math.cos(angle);
			double sinAngle = Math.sin(angle);
			float bridgeStartX = (float) (x + radius*cosAngle);
			float bridgeStartY = (float) (y + radius*sinAngle);
			float bridgeEndX = (float) (x - radius*cosAngle);
			float bridgeEndY = (float) (y - radius*sinAngle);
			
			float[] bridgeVertices = { (float) (bridgeStartX - World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeStartY + World.bridgeWidth*cosAngle*0.5f),
									(float) (bridgeStartX + World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeStartY - World.bridgeWidth*cosAngle*0.5f),
									(float) (bridgeEndX + World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeEndY - World.bridgeWidth*cosAngle*0.5f),
									(float) (bridgeEndX - World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeEndY + World.bridgeWidth*cosAngle*0.5f) };
			
			bridge = new Polygon(bridgeVertices);
		}
	}
	public boolean isBridged(){
		return bridged;
	}
	
	public void advanceBridge(float offset){
		bridge.translate(0, -offset);
	}
	
	public boolean contains(float x, float y){
		return super.contains(x,y) && (!bridged || !bridge.contains(x, y));
	}
}
