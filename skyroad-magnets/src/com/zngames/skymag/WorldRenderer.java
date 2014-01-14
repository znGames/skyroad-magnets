package com.zngames.skymag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array.ArrayIterator;

public class WorldRenderer {

	World world;
	OrthographicCamera cam;
	ShapeRenderer sRenderer;
	boolean drawLeftMagnetLine;
	boolean drawRightMagnetLine;
	
	public WorldRenderer(World world){
		this.world = world;
		world.setRenderer(this);
		
		cam = new OrthographicCamera(SkyMagGame.getWidth(), SkyMagGame.getHeight());
		cam.position.set(SkyMagGame.getWidth() / 2, SkyMagGame.getHeight() / 2, 0);
		cam.update();
		
		sRenderer = new ShapeRenderer();
		drawLeftMagnetLine = false;
		drawRightMagnetLine = false;
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        cam.update();
        sRenderer.setProjectionMatrix(cam.combined);
        sRenderer.begin(ShapeType.Filled);
        sRenderer.setColor(new Color(1, 0.54f, 0, 1));
        sRenderer.rect(SkyMagGame.getWidth()*0.25f, 0, SkyMagGame.getWidth()*0.5f, SkyMagGame.getHeight());
        sRenderer.setColor(Color.BLACK);
        ArrayIterator<Hole> iterCircles = new ArrayIterator<Hole>(world.getHoles());
        while(iterCircles.hasNext()){
        	Hole circle = iterCircles.next();
        	sRenderer.circle(circle.x, circle.y, circle.radius);
        }
        sRenderer.setColor(Color.YELLOW);
        ArrayIterator<Coin> iterCoins = new ArrayIterator<Coin>(world.getCoins());
        while(iterCoins.hasNext()){
        	Coin coin = iterCoins.next();
        	sRenderer.circle(coin.getX(), coin.getY(), coin.getWidth()/2);
        }
        //sRenderer.circle(SkyMagGame.getWidth()/4, SkyMagGame.getHeight()/4, 10);
        //System.out.println(SkyMagGame.getWidth());
        //System.out.println(SkyMagGame.getHeight());
        //sRenderer.circle(SkyMagGame.getWidth() / 2, SkyMagGame.getHeight()/5, 20);
        if(world.getShip().isFalling(world)){
        	sRenderer.setColor(Color.RED);
        } else {
        	sRenderer.setColor(Color.WHITE);
        }
        sRenderer.circle(world.getShip().getX(), world.getShip().getY(), world.getShip().getWidth()/2);
        sRenderer.setColor(Color.RED);
        sRenderer.rect(world.getLeftMagnet().getX() - world.getLeftMagnet().getWidth() / 2, world.getLeftMagnet().getY() - world.getLeftMagnet().getHeight() / 2, world.getLeftMagnet().getWidth(), world.getLeftMagnet().getHeight());
        sRenderer.setColor(Color.GREEN);
        sRenderer.rect(world.getRightMagnet().getX() - world.getRightMagnet().getWidth() / 2, world.getRightMagnet().getY() - world.getRightMagnet().getHeight() / 2, world.getRightMagnet().getWidth(), world.getRightMagnet().getHeight());
        ArrayIterator<Enemy> iterEnemy = new ArrayIterator<Enemy>(world.getEnemies());
        while(iterEnemy.hasNext()){
        	Enemy enemy = iterEnemy.next();
        	if(enemy.getClass().getName() == "com.zngames.skymag.FreezerEnemy"){
        		sRenderer.setColor(Color.CYAN);
        		/*
        		float[] vertices = {(float) (world.getLeftBorderXCoordinate() - enemy.getWidth() + Math.cos(5.0*Math.PI/6)), (float) (enemy.getY() + Math.sin(5.0*Math.PI/6)), 
        							(float) (world.getLeftBorderXCoordinate() - enemy.getWidth() + Math.cos(7.0*Math.PI/6)), (float) (enemy.getY() + Math.sin(7.0*Math.PI/6)),
        							world.getLeftBorderXCoordinate() - enemy.getWidth() +  1, enemy.getY()};
        		sRenderer.polygon(vertices);
        		*/
        		sRenderer.triangle((float) (World.getLeftBorderXCoordinate() - enemy.getWidth() + enemy.getWidth()*Math.cos(5.0*Math.PI/6)), (float) (enemy.getY() + enemy.getWidth()*Math.sin(5.0*Math.PI/6)),
        						   (float) (World.getLeftBorderXCoordinate() - enemy.getWidth() + enemy.getWidth()*Math.cos(7.0*Math.PI/6)), (float) (enemy.getY() + enemy.getWidth()*Math.sin(7.0*Math.PI/6)),
        						    World.getLeftBorderXCoordinate(), enemy.getY());
        		sRenderer.triangle((float) (World.getRightBorderXCoordinate() + enemy.getWidth() + enemy.getWidth()*Math.cos(Math.PI/6)), (float) (enemy.getY() + enemy.getWidth()*Math.sin(Math.PI/6)),
						   			(float) (World.getRightBorderXCoordinate() + enemy.getWidth() + enemy.getWidth()*Math.cos(-1.0*Math.PI/6)), (float) (enemy.getY() + enemy.getWidth()*Math.sin(-1.0*Math.PI/6)),
						   			World.getRightBorderXCoordinate(), enemy.getY());
        		if(((FreezerEnemy) enemy).isFiring()){
        			sRenderer.rect(World.getLeftBorderXCoordinate(), enemy.getY() - enemy.getWidth()/2, World.getFieldWidth(), enemy.getHeight());
        		}
        	}else if(enemy.getClass().getName() == "com.zngames.skymag.ZigzagEnemy"){
        		sRenderer.setColor(Color.MAGENTA);
        		sRenderer.circle(enemy.getX(), enemy.getY(), enemy.getWidth()/2);
        	}
        }
        sRenderer.end();
        
        sRenderer.begin(ShapeType.Line);
        if(drawLeftMagnetLine){
        	sRenderer.setColor(Color.RED);
        	sRenderer.line(world.getLeftMagnet().getPosition(), world.getShip().getPosition());
        }
        if(drawRightMagnetLine){
        	sRenderer.setColor(Color.GREEN);
        	sRenderer.line(world.getRightMagnet().getPosition(), world.getShip().getPosition());
        }
        sRenderer.setColor(Color.WHITE);
    	iterCircles = new ArrayIterator<Hole>(world.getHoles());
        while(iterCircles.hasNext()){
        	Hole circle = iterCircles.next();
        	if(circle.isBridged()){
        		sRenderer.polygon(circle.bridgeVertices);
        	}
        }
    	
        /*sRenderer.setColor(Color.WHITE);
        sRenderer.line((float) SkyMagGame.getWidth()*0.25f, 0f, (float) SkyMagGame.getWidth()*0.25f, (float) SkyMagGame.getHeight());
        sRenderer.line((float) SkyMagGame.getWidth()*0.75f, 0f, (float) SkyMagGame.getWidth()*0.75f, (float) SkyMagGame.getHeight());
        //sRenderer.circle(world.testCircle.x, world.testCircle.y, world.testCircle.radius);
        ArrayIterator<Hole> iter = new ArrayIterator<Hole>(world.holes);
        while(iter.hasNext()){
        	Hole circle = iter.next();
        	sRenderer.circle(circle.x, circle.y, circle.radius);
        }*/
        sRenderer.end();
		
	}
	
	public void dispose() {
        sRenderer.dispose();
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public void startDrawingLeftLine(){
		drawLeftMagnetLine = true;
	}
	
	public void startDrawingRightLine(){
		drawRightMagnetLine = true;
	}
	
	public void stopDrawingLeftLine(){
		drawLeftMagnetLine = false;
	}
	
	public void stopDrawingRightLine(){
		drawRightMagnetLine = false;
	}
	
}
