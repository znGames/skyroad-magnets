package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;

public class DiscShip extends Ship {
		
	public float[] gridCos = {1f,0.99144f,0.96593f,0.92388f,0.86603f,0.79335f,0.70711f,0.60876f,0.5f,0.38268f,0.25882f,0.13053f,-0f,-0.13053f,-0.25882f,-0.38268f,-0.5f,-0.60876f,-0.70711f,-0.79335f,-0.86603f,-0.92388f,-0.96593f,-0.99144f,-1f,-0.99144f,-0.96593f,-0.92388f,-0.86603f,-0.79335f,-0.70711f,-0.60876f,-0.5f,-0.38268f,-0.25882f,-0.13053f,-0f,0.13053f,0.25882f,0.38268f,0.5f,0.60876f,0.70711f,0.79335f,0.86603f,0.92388f,0.96593f,0.99144f};
	public float[] gridSin = {0f,0.13053f,0.25882f,0.38268f,0.5f,0.60876f,0.70711f,0.79335f,0.86603f,0.92388f,0.96593f,0.99144f,1f,0.99144f,0.96593f,0.92388f,0.86603f,0.79335f,0.70711f,0.60876f,0.5f,0.38268f,0.25882f,0.13053f,0f,-0.13053f,-0.25882f,-0.38268f,-0.5f,-0.60876f,-0.70711f,-0.79335f,-0.86603f,-0.92388f,-0.96593f,-0.99144f,-1f,-0.99144f,-0.96593f,-0.92388f,-0.86603f,-0.79335f,-0.70711f,-0.60876f,-0.5f,-0.38268f,-0.25882f,-0.13053f};
	
	public DiscShip(Magnet leftMagnet, Magnet rightMagnet){
		super(SkyMagGame.getWidth()/24, SkyMagGame.getWidth()/24, leftMagnet, rightMagnet);
	}
	
	public boolean isFalling(World world){
		float fieldWidth = world.getFieldWidth();
		if(position.x < (SkyMagGame.getWidth()-fieldWidth)/2 || position.x > (SkyMagGame.getWidth()+fieldWidth)/2){
			return true;
		}
		
		Array<Circle> holes = world.getHoles();
		ArrayIterator<Circle> iter = new ArrayIterator<Circle>(holes);
		
		while(iter.hasNext()){
			Circle circle = iter.next();
			if(circle.contains(position)){
				int nbConsecutivePoints = 0;
				for(int i=0;i<gridCos.length;i++){
					if(circle.contains(position.x + gridCos[i], position.y + gridSin[i])){
						nbConsecutivePoints++;
						if(nbConsecutivePoints == ((gridCos.length)/2)+1){
							return true;
						}
					}else{
						nbConsecutivePoints = 0;
						if(i >= (gridCos.length)/2){
							break;
						}
					}
				}
				return false;
			}
		}
		return false;
	}
}
