package com.zngames.skymag;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class ZigzagEnemy extends Enemy {

	int acceleration = -1;
	Vector2 velocity = new Vector2(0,-0.5f);
	float horizontalSpeed = 2f;
	float power = 0.15f;
	
	public ZigzagEnemy(Vector2 position, float width, float height){
		super(position, width, height);
	}
	
	public ZigzagEnemy(float x, float y, float width, float height){
		this(new Vector2(x, y), width, height);
	}
	
	public ZigzagEnemy(float width, float height){
		this(new Vector2(World.getRightBorderXCoordinate(), SkyMagGame.getHeight()), width, height);
	}
	
	public ZigzagEnemy(float y){
		this(new Vector2(World.getRightBorderXCoordinate(), y), SkyMagGame.getWidth()/18, SkyMagGame.getWidth()/18);
	}
	
	public ZigzagEnemy(){
		this(new Vector2(World.getRightBorderXCoordinate(), SkyMagGame.getHeight()), SkyMagGame.getWidth()/12, SkyMagGame.getWidth()/12);
	}
	
	public void update(World world, float delta){
		velocity.add((new Vector2(acceleration*delta*horizontalSpeed,0)));
		position.add(velocity);
		float middleBorder = World.getMiddleBorderXCoordinate();
		if((acceleration < 0 && position.x <= middleBorder) || (acceleration > 0 && position.x >= middleBorder)){
			acceleration *= -1;
		}
	}
	
	public boolean shouldStopExisting(World world){
		return position.y + height <= 0;
	}
	
	public void actOn(Ship ship){
		if(ship.overlapsCircle(new Circle(position.x, position.y, width/2))){
			ship.changeVelocity(power*(ship.getX()-position.x), power*(ship.getY()-position.y));
		}
	}
	
}
