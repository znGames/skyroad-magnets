package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;

public class Coin extends Entity {

	public int value;
	public static final float coinRadius = SkyMagGame.getWidth()/120;
	
	public Coin(float x, float y){
		super(x, y, 2*coinRadius, 2*coinRadius);
		value = 1;
	}
	
	public Coin(float x, float y, int value){
		super(x, y, 2*coinRadius, 2*coinRadius);
		this.value = value;
	}
	
	public boolean actOn(Ship ship){
		if(ship.overlapsCircle(new Circle(getX(), getY(), coinRadius))){
			ship.increaseCoinCounter(value);
			return true;
		}
		return false;
	}
	
}
