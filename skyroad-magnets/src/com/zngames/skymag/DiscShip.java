package com.zngames.skymag;

public class DiscShip extends Ship {

	public DiscShip(Magnet leftMagnet, Magnet rightMagnet){
		super(SkyMagGame.getWidth()/24, SkyMagGame.getWidth()/24, leftMagnet, rightMagnet);
	}
	
	public boolean isFalling(){
		return false;
	}
}
