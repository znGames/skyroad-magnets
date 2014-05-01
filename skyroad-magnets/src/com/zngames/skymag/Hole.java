package com.zngames.skymag;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zngames.assets.Assets;

public class Hole extends Entity {
	private boolean isBridged;
	private Sprite bridge;
	//Polygon bridge;
	
	public enum HoleState{
		idle,
		rotating
	}
	
	public HoleState state;
	
	public Hole(float x, float y, float radius, boolean isBridged){
		super(Assets.circle,new Vector2(x,y),radius*2,radius*2);
		setColor(0,0,0,1);
		this.isBridged = isBridged;
		this.state = HoleState.rotating;
		if(isBridged){
			/*double angle;
			if(x-radius < World.getLeftBorderXCoordinate()){
				float leftLimit = (float) Math.min(Math.acos((World.getLeftBorderXCoordinate()-x)*(1.0/radius)), MathUtils.PI*3.0/4);
				angle = MathUtils.random(MathUtils.PI-leftLimit, leftLimit);
			} else if(x+radius > World.getRightBorderXCoordinate()){
				float rightLimit = (float) Math.max(Math.acos((World.getRightBorderXCoordinate()-x)*(1.0/radius)), MathUtils.PI*1.0/4);
				angle = MathUtils.random(rightLimit, MathUtils.PI-rightLimit);
			} else{
				angle = MathUtils.random((float) (MathUtils.PI*1.0/4), (float) (MathUtils.PI*3.0/4));
			}
			double cosAngle = Math.cos(angle);
			double sinAngle = Math.sin(angle);
			float bridgeStartX = (float) (x + radius*cosAngle);
			float bridgeStartY = (float) (y + radius*sinAngle);
			float bridgeEndX = (float) (x - radius*cosAngle);
			float bridgeEndY = (float) (y - radius*sinAngle);
			
			float[] bridgeVertices = { (float) (bridgeStartX - World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeStartY + World.bridgeWidth*cosAngle*0.5f),
									(float) (bridgeStartX + World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeStartY - World.bridgeWidth*cosAngle*0.5f),
									(float) (bridgeEndX + World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeEndY - World.bridgeWidth*cosAngle*0.5f),
									(float) (bridgeEndX - World.bridgeWidth*sinAngle*0.5f),
									(float) (bridgeEndY + World.bridgeWidth*cosAngle*0.5f) };
			
			bridge = new Polygon(bridgeVertices);*/
			//bridge = new Entity(Assets.borderedSquare);
			//bridge.setRegion(Assets.borderedSquare);
			float bridgeWidth = radius/3f;
			
			bridge = new Sprite(Assets.borderedSquare);
			bridge.setSize(bridgeWidth,radius*2f);
			bridge.setPosition(x + radius - (bridgeWidth/2f), y);
			bridge.setOrigin(bridge.getWidth()/2f, bridge.getHeight()/2f);
		}
	}
	
	@Override
	public void draw(SpriteBatch batch){
		super.draw(batch);
		if(bridge != null){
			bridge.draw(batch);
		}
	}
	
	public boolean isBridged(){
		return isBridged;
	}
	
	/*public void advanceBridge(float offset){
		bridge.translate(0, -offset);
	}*/
	
	/*public boolean contains(float x, float y){
		return super.contains(x,y) && (!bridged || !bridge.contains(x, y));
	}*/

	@Override
	public void update(float delta) {
		if(state == HoleState.idle){
			updateIdle(delta);
		}
		else if(state == HoleState.rotating){
			updateRotating(delta);
		}
		scrollDown(delta);
	}
	
	public void move(float x,float y){
		setPosition(position.x + x,position.y + y);
		if(bridge != null){
			bridge.setPosition(bridge.getX()+x, bridge.getY()+y);
		}
	}
	
	private void scrollDown(float delta){
		move(0,delta*(-0.5f));
	}
	
	private void updateIdle(float delta){
		
	}
	
	private void updateRotating(float delta){
		if(bridge != null){
			bridge.rotate(1f);
		}
	}
}
