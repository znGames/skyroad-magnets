package com.zngames.skymag;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FreezerEnemy extends Enemy {

	public FreezerEnemy(TextureRegion region) {
		super(region);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	/*private float timeSinceCreation = 0;
	private boolean isFiring = false;
	public final float speed = 30;
	public final static float timeUntilFire = 5;
	public final static float firingTime = 2;
	
	public FreezerEnemy(Vector2 position, float width, float height){
		super(position, width, height);
	}
	
	public FreezerEnemy(float x, float y, float width, float height){
		this(new Vector2(x, y), width, height);
	}
	
	public FreezerEnemy(float x, float y){
		this(new Vector2(x, y), SkyMagGame.getWidth()/24, SkyMagGame.getWidth()/24);
	}
	
	public void update(World world, float delta){
		timeSinceCreation += delta;
		
		if(timeSinceCreation >= timeUntilFire){
			isFiring = true;
			return;
		}
		
		if(world.getShip().getY() < position.y){
			position.y -= speed*delta;
		} else if(world.getShip().getY() > position.y) {
			position.y += speed*delta;
		}

	}
	
	public boolean shouldStopExisting(World world){
		return timeSinceCreation >= timeUntilFire + firingTime;
	}
	
	public void actOn(Ship ship){
		if(isFiring && ship.overlapsRectangle(new Rectangle(World.getLeftBorderXCoordinate(), getY() - getHeight()/2, World.getFieldWidth(), getHeight()))){
			ship.freeze();
		}
	}
	
	public boolean isFiring(){
		return isFiring;
	}*/
}
