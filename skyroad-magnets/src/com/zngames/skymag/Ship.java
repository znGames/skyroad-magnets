package com.zngames.skymag;

import com.badlogic.gdx.math.Vector2;

public abstract class Ship extends Entity {
	
	Magnet leftMagnet;
	Magnet rightMagnet;
	Vector2 velocity;

	public Ship(float width, float height, Magnet leftMagnet, Magnet rightMagnet){
		super(new Vector2(SkyMagGame.getWidth() / 2, SkyMagGame.getHeight() / 5 ), width, height);
		this.leftMagnet = leftMagnet;
		this.rightMagnet = rightMagnet;
		velocity = new Vector2(0,0);
	}
	
	public void advance(float delta){
		//Vector2 leftAttraction;
		//Vector2 rightAttraction;
		Vector2 totalAttraction;
		
		//leftAttraction = leftMagnet.getAttraction(this);
		//rightAttraction = rightMagnet.getAttraction(this);
		//totalAttraction = leftAttraction.cpy().add(rightAttraction).scl(0.5f);
		//totalAttraction = leftAttraction.cpy().add(rightAttraction).scl(0.5f);
		totalAttraction = leftMagnet.getAttraction(this).cpy().add(rightMagnet.getAttraction(this)).scl(0.5f);
		
		//System.out.println("Left attraction : " + leftAttraction.toString());
		//System.out.println("Right attraction : " + rightAttraction.toString());
		//System.out.println("Total attraction : " + totalAttraction.toString());
		
		//	System.out.println("Left moment : " + leftMagnet.getMoment());
		//System.out.println("Right moment : " + rightMagnet.getMoment());
		
		//this.position.lerp(totalAttraction, delta/2);
		//this.position.set(Interpolation.sineOut.apply(this.position.x, totalAttraction.x, delta),Interpolation.sineOut.apply(this.position.y, totalAttraction.y, delta));
		if(totalAttraction.equals(getPosition())){
			velocity.scl(0.97f);
		}
		
		velocity.add((new Vector2(totalAttraction.x - position.x, totalAttraction.y - position.y)).scl(delta/50));
		//System.out.println("Velocity : " + velocity.toString());
		position.add(velocity);
	}
	
	public abstract boolean isFalling();
	
}
