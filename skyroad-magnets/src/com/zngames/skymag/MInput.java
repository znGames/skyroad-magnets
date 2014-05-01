package com.zngames.skymag;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.zngames.viewport.VirtualViewport;

public class MInput implements InputProcessor{
	
	public static class Event {
		public static final int KEY_DOWN = 0;
		public static final int KEY_UP = 1;
		public static final int TOUCH_DOWN = 2;
		public static final int TOUCH_UP = 3;
		public static final int TOUCH_DRAGGED = 4;
		
		public int type;
	}
	
	public static class KeyEvent extends Event{
		public int keyCode;
		public char keyChar;
	}
	
	public static class TouchEvent extends Event{
		public float x, y;
		public int pointer;
	}
	
	List<Event> events;
	boolean multiTouch;
	boolean screenIsTouched = false;
	boolean keyIsTouched = false;
	boolean isLocked = false;
	int currentPointerUsed = -1;
	
	SkyMagGame game;
	
	public MInput(SkyMagGame game,boolean multiTouch){
		events = new ArrayList<Event>();
		
		this.game = game;
		this.multiTouch = multiTouch;
	}
	
	public List<Event> getEvents(){
		List<Event> touchEventsBuffer = new ArrayList<Event>();
		if(!isLocked){
			touchEventsBuffer.addAll(events);
			events.clear();
		}
		return touchEventsBuffer;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(!keyIsTouched && !screenIsTouched && !isLocked){
			keyIsTouched = true;
			KeyEvent event = new KeyEvent();
			event.keyCode = keycode;
			event.type = KeyEvent.KEY_DOWN;
		
			events.add(event);
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keyIsTouched && !isLocked){
			keyIsTouched = false;
			KeyEvent event = new KeyEvent();
			event.keyCode = keycode;
			event.type = KeyEvent.KEY_UP;
		
			events.add(event);
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!multiTouch && !isLocked){
			if(!screenIsTouched && !keyIsTouched){
				screenIsTouched = true;
				currentPointerUsed = pointer;
				
				TouchEvent event = new TouchEvent();
				event.pointer = pointer;
				event.x = VirtualViewport.convertWorldWidthToUnit(screenX);
				event.y = game.getVvp().world_y - VirtualViewport.convertWorldHeightToUnit(screenY);
				
				event.type = TouchEvent.TOUCH_DOWN;
				
				events.add(event);
			}
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(!multiTouch && !isLocked){
			if(screenIsTouched && pointer == currentPointerUsed){
				currentPointerUsed = -1;
				screenIsTouched = false;
				
				TouchEvent event = new TouchEvent();
				event.pointer = pointer;
				event.x = VirtualViewport.convertWorldWidthToUnit(screenX);
				event.y = game.getVvp().world_y - VirtualViewport.convertWorldHeightToUnit(screenY);
				event.type = TouchEvent.TOUCH_UP;
				
				events.add(event);
			}
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(!multiTouch && !isLocked){
			if(screenIsTouched && currentPointerUsed == pointer){
				TouchEvent event = new TouchEvent();
				event.pointer = pointer;
				event.x = VirtualViewport.convertWorldWidthToUnit(screenX);
				event.y = game.getVvp().world_y - VirtualViewport.convertWorldHeightToUnit(screenY);
				event.type = TouchEvent.TOUCH_DRAGGED;
				
				events.add(event);
			}
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public void clear(){
		events.clear();
	}
	
	public void lock(){
		clear();
		isLocked = true;
	}
	
	public void unlock(){
		clear();
		isLocked = false;
	}


}
