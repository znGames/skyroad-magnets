package com.zngames.skymag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
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
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
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
        sRenderer.setColor(Color.ORANGE);
        sRenderer.rect(Gdx.graphics.getWidth()*0.25f, 0, Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight());
        sRenderer.setColor(Color.BLACK);
        ArrayIterator<Circle> iter = new ArrayIterator<Circle>(world.holes);
        while(iter.hasNext()){
        	Circle circle = iter.next();
        	sRenderer.circle(circle.x, circle.y, circle.radius);
        }
        //sRenderer.circle(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, 10);
        //System.out.println(Gdx.graphics.getWidth());
        //System.out.println(Gdx.graphics.getHeight());
        //sRenderer.circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/5, 20);
        sRenderer.setColor(Color.WHITE);
        sRenderer.circle(world.getShip().getX(), world.getShip().getY(), world.getShip().getWidth()/2);
        sRenderer.setColor(Color.RED);
        sRenderer.rect(world.getLeftMagnet().getX() - world.getLeftMagnet().getWidth() / 2, world.getLeftMagnet().getY() - world.getLeftMagnet().getHeight() / 2, world.getLeftMagnet().getWidth(), world.getLeftMagnet().getHeight());
        sRenderer.setColor(Color.GREEN);
        sRenderer.rect(world.getRightMagnet().getX() - world.getLeftMagnet().getWidth() / 2, world.getRightMagnet().getY() - world.getLeftMagnet().getHeight() / 2, world.getRightMagnet().getWidth(), world.getRightMagnet().getHeight());
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
        /*sRenderer.setColor(Color.WHITE);
        sRenderer.line((float) Gdx.graphics.getWidth()*0.25f, 0f, (float) Gdx.graphics.getWidth()*0.25f, (float) Gdx.graphics.getHeight());
        sRenderer.line((float) Gdx.graphics.getWidth()*0.75f, 0f, (float) Gdx.graphics.getWidth()*0.75f, (float) Gdx.graphics.getHeight());
        //sRenderer.circle(world.testCircle.x, world.testCircle.y, world.testCircle.radius);
        ArrayIterator<Circle> iter = new ArrayIterator<Circle>(world.holes);
        while(iter.hasNext()){
        	Circle circle = iter.next();
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
