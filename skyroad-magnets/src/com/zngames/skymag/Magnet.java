package com.zngames.skymag;

import com.badlogic.gdx.math.Vector2;
import com.zngames.assets.Assets;

public class Magnet extends Entity {
	
	boolean active;
	//float moment;

	public Magnet(Vector2 position, float width, float height){
		//super(Assets.magnet,position, width, height);
		super(Assets.square,position,width,height);
		setColor(1,0,0,1);
		active = false;
		//moment = 0;
	}
	
	/*public Vector2 getAttraction(Entity entity){
		if(active){
			return getPosition();
		}
		
		//decreaseMoment();
		//return getPosition().cpy().lerp(entity.getPosition(), 1-moment);
		return entity.getPosition();
	}*/
	
	/*public void decreaseMoment(){
		moment -= 0.01;
		if(moment < 0){
			moment = 0;
		}
	}*/
	
	public void activate(){
		active = true;
		//moment = 1;
	}
	
	public void deactivate(){
		active = false;
	}
	
	public boolean isActive(){
		return active;
	}

	@Override
	public void update(float delta) {
		
	}
	
}
