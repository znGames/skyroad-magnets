package com.zngames.skymag;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class InputHandler implements InputProcessor {

	World world;
	Vector3 touch = new Vector3();
	float leftMagnetLeftBound;
	float leftMagnetRightBound;
	float rightMagnetLeftBound;
	float rightMagnetRightBound;
	
	public InputHandler(World world){
		this.world = world;
		leftMagnetLeftBound = world.getLeftMagnet().getX() - world.getLeftMagnet().getWidth();
		leftMagnetRightBound = world.getLeftMagnet().getX() + world.getLeftMagnet().getWidth();
		rightMagnetLeftBound = world.getRightMagnet().getX() - world.getRightMagnet().getWidth();
		rightMagnetRightBound = world.getRightMagnet().getX() + world.getRightMagnet().getWidth();
		
		//System.out.println(leftMagnetLeftBound + " " + leftMagnetRightBound + " " + rightMagnetLeftBound + " " + rightMagnetRightBound );
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
			case Keys.I:
				world.getRenderer().startDrawingLeftLine();
				world.getLeftMagnet().activate();
				break;
			case Keys.P:
				world.getRenderer().startDrawingRightLine();
				world.getRightMagnet().activate();
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
			case Keys.I:
				world.getRenderer().stopDrawingLeftLine();
				world.getLeftMagnet().deactivate();
				break;
			case Keys.P:
				world.getRenderer().stopDrawingRightLine();
				world.getRightMagnet().deactivate();
				break;
			default:
				break;
		}
	return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touch.set(screenX, screenY, 0);
		world.getRenderer().getCamera().unproject(touch);
		if(touch.x >= leftMagnetLeftBound && touch.x <= leftMagnetRightBound){
			world.getLeftMagnet().getPosition().set(world.getLeftMagnet().getPosition().x, touch.y);
			world.getRenderer().startDrawingLeftLine();
			world.getLeftMagnet().activate();
			//System.out.println("Left magnet touched : " + touch.x + ";" + touch.y);
		}
		if(touch.x >= rightMagnetLeftBound && touch.x <= rightMagnetRightBound){
			world.getRightMagnet().getPosition().set(world.getRightMagnet().getPosition().x, touch.y);
			world.getRenderer().startDrawingRightLine();
			world.getRightMagnet().activate();
			//System.out.println("Right magnet touched : " + touch.x + ";" + touch.y);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touch.set(screenX, screenY, 0);
		world.getRenderer().getCamera().unproject(touch);
		if(touch.x >= leftMagnetLeftBound && touch.x <= leftMagnetRightBound){
			world.getRenderer().stopDrawingLeftLine();
			world.getLeftMagnet().deactivate();
		}
		if(touch.x >= rightMagnetLeftBound && touch.x <= rightMagnetRightBound){
			world.getRenderer().stopDrawingRightLine();
			world.getRightMagnet().deactivate();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touch.set(screenX, screenY, 0);
		world.getRenderer().getCamera().unproject(touch);
		if(touch.x >= leftMagnetLeftBound && touch.x <= leftMagnetRightBound){
			world.getLeftMagnet().getPosition().set(world.getLeftMagnet().getPosition().x, touch.y);
			world.getRenderer().startDrawingLeftLine();
			//System.out.println("Left magnet touched : " + touch.x + ";" + touch.y);
		}
		else{
			world.getRenderer().stopDrawingLeftLine();
			world.getLeftMagnet().deactivate();
		}
		if(touch.x >= rightMagnetLeftBound && touch.x <= rightMagnetRightBound){
			world.getRightMagnet().getPosition().set(world.getRightMagnet().getPosition().x, touch.y);
			world.getRenderer().startDrawingRightLine();
			//System.out.println("Right magnet touched : " + touch.x + ";" + touch.y);
		}
		else{
			world.getRenderer().stopDrawingRightLine();
			world.getRightMagnet().deactivate();
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		//System.out.println("Mouse moved : [" + screenX + "," + screenY + "]");
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
