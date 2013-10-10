package com.zngames.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zngames.skymag.SkyMagGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "skyroad-magnets";
		cfg.useGL20 = false;
		cfg.width = 720; //480 by default
		cfg.height = 480; //320 by default
		
		new LwjglApplication(new SkyMagGame(), cfg);
	}
}
