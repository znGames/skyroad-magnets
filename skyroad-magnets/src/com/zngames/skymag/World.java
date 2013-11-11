package com.zngames.skymag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;

public class World {
	
	SkyMagGame game;
	WorldRenderer wr;
	Ship ship;
	Magnet leftMagnet;
	Magnet rightMagnet;
	float timeSinceLastCircle;
	public Circle testCircle;
	Array<Circle> holes;
	Array<Enemy> enemies;
	float muRadius;
	float sigmaRadius;
	float minDistanceBetweenHoles;
	final float fieldWidth = SkyMagGame.getWidth() * 0.5f; 
	final float maxRadius = fieldWidth * 0.2f;
	
	public World(SkyMagGame game){
		this.game = game;
		leftMagnet = new Magnet(new Vector2(SkyMagGame.getWidth() / 9, SkyMagGame.getHeight() / 2), 40, 40);
		rightMagnet = new Magnet(new Vector2(8*SkyMagGame.getWidth() / 9, SkyMagGame.getHeight() / 2), 40, 40);
		ship = new DiscShip(leftMagnet, rightMagnet);
		Gdx.input.setInputProcessor(new InputHandler(this));
		timeSinceLastCircle = 0;
		holes = new Array<Circle>(false, 16);
		enemies = new Array<Enemy>(false, 16);
		muRadius = maxRadius*0.25f;
		sigmaRadius = maxRadius*0.50f;
		minDistanceBetweenHoles = ship.getWidth();
		testCircle = new Circle(SkyMagGame.getWidth() / 2, SkyMagGame.getHeight()*1.2f, 50);
		// Creating a freezer enemy
		//enemies.add(new FreezerEnemy(SkyMagGame.getWidth() / 2, SkyMagGame.getHeight() / 3, 50, 50));
	}
	
	public void update(float delta){
		//System.out.println("Max radius : " + maxRadius);
		//System.out.println("Mu radius : " + muRadius);
		//System.out.println("Sigma radius : " + sigmaRadius);
		timeSinceLastCircle += delta; 
		if(MathUtils.random(timeSinceLastCircle) > 1.5){
			//System.out.println("GENERATE !");
			timeSinceLastCircle = 0;
			//holes.add(new Circle(MathUtils.random(SkyMagGame.getWidth()*0.25f, SkyMagGame.getWidth()*0.25f + fieldWidth), SkyMagGame.getHeight()+maxRadius, generateRadius()));
			float x;
			float y = SkyMagGame.getHeight()+maxRadius;
			float radius = maxRadius;
			boolean newCircleIsOk;
			int abortionCount = 0;
			do{
				newCircleIsOk = true;
				x = MathUtils.random(SkyMagGame.getWidth()*0.25f, SkyMagGame.getWidth()*0.25f + fieldWidth);
				radius = generateRadius(radius);
				ArrayIterator<Circle> iter = new ArrayIterator<Circle>(holes);
				while(iter.hasNext()){
					Circle circle = iter.next();
					if(Math.sqrt((circle.x-x)*(circle.x-x) + (circle.y-y)*(circle.y-y)) < radius + minDistanceBetweenHoles + circle.radius){
						newCircleIsOk = false;
						//System.out.println("NOT OK !!!!!!!!!!!!!");
						break;
					}
				}
				abortionCount++;
				if(abortionCount > 4){
					//System.out.println("ABORT !!!!!!");
					break;
				}
			}while(!newCircleIsOk);
			
			if(newCircleIsOk){
				holes.add(new Circle(x,y,radius));
			}
		}
		
		ArrayIterator<Circle> iterCircles = new ArrayIterator<Circle>(holes);
		while(iterCircles.hasNext()){
			Circle circle = iterCircles.next();
			if(circle.y + circle.radius < 0){
				iterCircles.remove();
			}
			circle.setPosition(circle.x, circle.y-1);
		}
		//testCircle.setPosition(testCircle.x, testCircle.y-1);
		//TODO
		if(muRadius < maxRadius){
			muRadius += maxRadius*0.01*delta;
		}
		else{
			sigmaRadius += sigmaRadius*0.001*delta;
		}
		
		// Updating the enemies
		ArrayIterator<Enemy> iterEnemies = new ArrayIterator<Enemy>(enemies);
		while(iterEnemies.hasNext()){
			Enemy enemy = iterEnemies.next();
			enemy.update(this, delta);
			// Removing the enemies from the array if they should stop existing
			if(enemy.shouldStopExisting(this)){
				iterEnemies.remove();
			}
		}
		
		// Making the ship advance
		ship.advance(delta);
	}
	
	public float generateRadius(float max){
		float z, u;
		int cpt = 0;
		do{
			z = MathUtils.random(max);
			u = MathUtils.random();
			cpt++;
		}while(cpt < 100 && u>Math.exp(-z*z*0.5));
		
		//System.out.println("CPT : " + cpt);
		return sigmaRadius*z + muRadius;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Magnet getLeftMagnet() {
		return leftMagnet;
	}

	public void setLeftMagnet(Magnet leftMagnet) {
		this.leftMagnet = leftMagnet;
	}

	public Magnet getRightMagnet() {
		return rightMagnet;
	}

	public void setRightMagnet(Magnet rightMagnet) {
		this.rightMagnet = rightMagnet;
	}
	
	public WorldRenderer getRenderer(){
		return wr;
	}
	
	public void setRenderer(WorldRenderer wr){
		this.wr = wr;
	}
	
	public Array<Circle> getHoles(){
		return holes;
	}
	
	public Array<Enemy> getEnemies(){
		return enemies;
	}
	
	public float getFieldWidth(){
		return fieldWidth;
	}
	
}
