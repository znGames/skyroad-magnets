package com.zngames.skymag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.zngames.assets.Assets;

public class Key extends Entity {

	public static final float KEY_RADIUS = ((SkyMagGame)Gdx.app.getApplicationListener()).getVvp().world_x/2.5f;
	
	public Key(Vector2 position){
		//super(Assets.key, position, 2*KEY_RADIUS, 2*KEY_RADIUS);
		super(Assets.circle, position, 2*KEY_RADIUS, 2*KEY_RADIUS);
		setColor(1,1,1,1);
	}
	
	/*public boolean actOn(Ship ship){
		if(ship.overlapsCircle(new Circle(getX(), getY(), KEY_RADIUS))){
			ship.increaseKeyCounter();
			return true;
		}
		return false;
	}*/

	@Override
	public void update(float delta) {
		
	}
}
