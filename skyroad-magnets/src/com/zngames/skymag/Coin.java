package com.zngames.skymag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.zngames.assets.Assets;

public class Coin extends Entity {

	private int value;
	public static final float COIN_RADIUS = ((SkyMagGame)Gdx.app.getApplicationListener()).getVvp().world_x/2.5f;
	
	public Coin(Vector2 position){
		//super(Assets.coin,position, 2*COIN_RADIUS, 2*COIN_RADIUS);
		super(Assets.circle,position, 2*COIN_RADIUS, 2*COIN_RADIUS);
		setColor(1,1,0,1);
		value = 1;
	}
	
	public Coin(Vector2 position, int value){
		//super(Assets.coin,position, 2*COIN_RADIUS, 2*COIN_RADIUS);
		super(Assets.circle,position, 2*COIN_RADIUS, 2*COIN_RADIUS);
		setColor(1,1,0,1);
		this.value = value;
	}
	
	/*public boolean actOn(Ship ship){
		if(ship.overlapsCircle(new Circle(getX(), getY(), COIN_RADIUS))){
			ship.increaseCoinCounter(value);
			return true;
		}
		return false;
	}*/
	
	public int getValue(){
		return this.value;
	}

	@Override
	public void update(float delta) {
		
	}
	
}
