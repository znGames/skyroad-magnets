package com.zngames.skymag;

import com.badlogic.gdx.math.Vector2;

public class Entity {

	Vector2 position;
	float width;
	float height;
	
	public Entity(Vector2 position, float width, float height){
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public Entity(float x, float y, float width, float height){
		this(new Vector2(x, y), width, height);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void setPosition(float x, float y){
		this.position.x = x;
		this.position.y = y;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
}
