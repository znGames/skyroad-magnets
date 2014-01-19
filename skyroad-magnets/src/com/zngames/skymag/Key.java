package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;

public class Key extends Entity {

	public static final float keyRadius = SkyMagGame.getWidth()/120;
	
	public Key(float x, float y){
		super(x, y, 2*keyRadius, 2*keyRadius);
	}
	
	public boolean actOn(Ship ship){
		if(ship.overlapsCircle(new Circle(getX(), getY(), keyRadius))){
			ship.increaseKeyCounter();
			return true;
		}
		return false;
	}
}
