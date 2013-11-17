package com.zngames.skymag;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Ship extends Entity {
	
	Magnet leftMagnet;
	Magnet rightMagnet;
	Vector2 velocity;
	static final float freezingTime = 2;
	float timeUntilUnfreezing = 0;

	public Ship(float width, float height, Magnet leftMagnet, Magnet rightMagnet){
		super(new Vector2(SkyMagGame.getWidth() / 2, SkyMagGame.getHeight() / 5 ), width, height);
		this.leftMagnet = leftMagnet;
		this.rightMagnet = rightMagnet;
		velocity = new Vector2(0,0);
	}
	
	public void advance(float delta){
		if(timeUntilUnfreezing >= 0){
			timeUntilUnfreezing -= delta;
			return;
		}
		
		Vector2 totalAttraction = leftMagnet.getAttraction(this).cpy().add(rightMagnet.getAttraction(this)).scl(0.5f);
		
		if(totalAttraction.equals(getPosition())){
			velocity.scl(0.97f);
		}
		
		velocity.add((new Vector2(totalAttraction.x - position.x, totalAttraction.y - position.y)).scl(delta/50));
		position.add(velocity);
	}
	
	public void changeVelocity(float x, float y){
		velocity = new Vector2(x, y);
	}
	
	public void freeze(){
		timeUntilUnfreezing = freezingTime;
	}
	
	public abstract boolean isFalling(World world);
	
	public abstract boolean overlapsRectangle(Rectangle rectangle);
}
