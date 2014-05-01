package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;

public class DiscShip extends Ship {

	public DiscShip(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
		
	/*public float[] gridCos = {1f,0.99144f,0.96593f,0.92388f,0.86603f,0.79335f,0.70711f,0.60876f,0.5f,0.38268f,0.25882f,0.13053f,-0f,-0.13053f,-0.25882f,-0.38268f,-0.5f,-0.60876f,-0.70711f,-0.79335f,-0.86603f,-0.92388f,-0.96593f,-0.99144f,-1f,-0.99144f,-0.96593f,-0.92388f,-0.86603f,-0.79335f,-0.70711f,-0.60876f,-0.5f,-0.38268f,-0.25882f,-0.13053f,-0f,0.13053f,0.25882f,0.38268f,0.5f,0.60876f,0.70711f,0.79335f,0.86603f,0.92388f,0.96593f,0.99144f};
	public float[] gridSin = {0f,0.13053f,0.25882f,0.38268f,0.5f,0.60876f,0.70711f,0.79335f,0.86603f,0.92388f,0.96593f,0.99144f,1f,0.99144f,0.96593f,0.92388f,0.86603f,0.79335f,0.70711f,0.60876f,0.5f,0.38268f,0.25882f,0.13053f,0f,-0.13053f,-0.25882f,-0.38268f,-0.5f,-0.60876f,-0.70711f,-0.79335f,-0.86603f,-0.92388f,-0.96593f,-0.99144f,-1f,-0.99144f,-0.96593f,-0.92388f,-0.86603f,-0.79335f,-0.70711f,-0.60876f,-0.5f,-0.38268f,-0.25882f,-0.13053f};
	
	public DiscShip(){
		//super(SkyMagGame.getWidth()/24, SkyMagGame.getWidth()/24, leftMagnet, rightMagnet);
	}
	
	public boolean isFalling(World world){
		if(position.x < World.getLeftBorderXCoordinate() || position.x > World.getRightBorderXCoordinate()){
			return true;
		}
		
		Array<Hole> holes = world.getHoles();
		ArrayIterator<Hole> iter = new ArrayIterator<Hole>(holes);
		
		while(iter.hasNext()){
			Hole circle = iter.next();
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
	
	public boolean overlapsRectangle(Rectangle rectangle){
		// see http://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection/402010#402010
		float circleRadius = getWidth()/2;
		Vector2 rectangleCenter = rectangle.getCenter(new Vector2());
		
		float circleDistanceX = Math.abs(getX() - rectangleCenter.x);
		float circleDistanceY = Math.abs(getY() - rectangleCenter.y);
		
		if(circleDistanceX > (rectangle.getWidth()/2 + circleRadius))
			return false;
		
		if(circleDistanceY > (rectangle.getHeight()/2 + circleRadius))
			return false;
		
		if(circleDistanceX <= (rectangle.getWidth()/2))
			return true;
		
		if(circleDistanceY <= (rectangle.getHeight()/2))
			return true;
		
		return (circleDistanceX - rectangle.getWidth()/2)*(circleDistanceX - rectangle.getWidth()/2) 
					+ (circleDistanceY - rectangle.getHeight()/2)*(circleDistanceY - rectangle.getHeight()/2) <= (circleRadius*circleRadius)/4;
	}
	
	public boolean overlapsCircle(Circle circle){
		return circle.overlaps(new Circle(getX(), getY(), getWidth()/2));
	}*/
}
