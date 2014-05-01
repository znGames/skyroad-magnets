package com.zngames.skymag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zngames.assets.Assets;

public class Ship extends Entity {
	
	//Magnet leftMagnet;
	//Magnet rightMagnet;
	Vector2 velocity;
	float coinCounter = 0;
	float keyCounter = 0;
	static final float freezingTime = 2;
	float timeUntilUnfreezing = 0;

	public Ship(float width, float height/*, Magnet leftMagnet, Magnet rightMagnet*/){
		//super(Assets.ship);
		super(Assets.circle);
		setColor(0,0,1,1);
		setSize(width,height);
		
		SkyMagGame game = ((SkyMagGame)Gdx.app.getApplicationListener());
		setPosition(game.getVvp().world_x/2f - getWidth()/2f,2f);

		//this.leftMagnet = leftMagnet;
		//this.rightMagnet = rightMagnet;
		velocity = new Vector2(0,0);
	}
	
	/*public void advance(float delta){
		if(timeUntilUnfreezing >= 0){
			timeUntilUnfreezing -= delta;
			return;
		}
		
		//Vector2 totalAttraction = leftMagnet.getAttraction(this).cpy().add(rightMagnet.getAttraction(this)).scl(0.5f);
		
		if(totalAttraction.equals(position)){
			velocity.scl(0.97f);
		}
		
		velocity.add((new Vector2(totalAttraction.x - position.x, totalAttraction.y - position.y)).scl(delta/50));
		position.add(velocity);
	}*/
	
	/*public void increaseCoinCounter(int value){
		coinCounter += value;
	}
	
	public void increaseKeyCounter(){
		keyCounter++;
	}*/
	public void move(float x,float y){
		setPosition(position.x + x,position.y + y);
	}
	
	public void changeVelocity(float x, float y){
		velocity = new Vector2(x, y);
	}
	
	public void freeze(){
		timeUntilUnfreezing = freezingTime;
	}

	@Override
	public void update(float delta) {
		
	}
	
	/*public abstract boolean isFalling(World world);
	
	public abstract boolean overlapsRectangle(Rectangle rectangle);
	
	public abstract boolean overlapsCircle(Circle circle);*/
}
