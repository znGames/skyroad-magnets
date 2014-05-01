package com.zngames.skymag;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Sprite{
	
	public Vector2 position;
		
	public Entity(TextureRegion region){
		super(region);
		this.position = new Vector2(0,0);
	}
	
	public Entity(TextureRegion region,Vector2 position, float width, float height){
		super(region);
		this.position = new Vector2(0,0);
		
		setSize(width,height);
		setPosition(position.x,position.y);		
	}
	
	@Override
	public void setPosition(float x,float y){
		super.setPosition(x,y);
		position.set(x,y);
	}
	
	public abstract void update(float delta);

}
