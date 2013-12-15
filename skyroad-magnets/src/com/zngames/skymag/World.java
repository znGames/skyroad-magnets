package com.zngames.skymag;

import java.util.Random;

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
	Array<Circle> holes;
	Array<Enemy> enemies;
	Array<Coin> coins;
	float globalSpeed;
	float muRadius;
	float sigmaRadius;
	float minDistanceBetweenHoles;
	float minDistanceBetweenHolesAndCoins;
	Circle pendingHole;
	float pendingDistance;
	float surplusDistance;
	static final float fieldWidth = SkyMagGame.getWidth() * 0.5f; 
	final float maxRadius = fieldWidth * 0.4f;
	final float minRadius = fieldWidth/8;
	final float chanceOfCoinGeneration = 0.25f;
	
	public World(SkyMagGame game){
		this.game = game;
		leftMagnet = new Magnet(new Vector2(SkyMagGame.getWidth() / 9, SkyMagGame.getHeight() / 2), 40, 40);
		rightMagnet = new Magnet(new Vector2(8*SkyMagGame.getWidth() / 9, SkyMagGame.getHeight() / 2), 40, 40);
		ship = new DiscShip(leftMagnet, rightMagnet);
		Gdx.input.setInputProcessor(new InputHandler(this));
		timeSinceLastCircle = 0;
		holes = new Array<Circle>(false, 16);
		enemies = new Array<Enemy>(false, 16);
		coins = new Array<Coin>(false, 16);
		globalSpeed = 60;
		muRadius = (maxRadius-minRadius)*0.25f + minRadius;
		sigmaRadius = maxRadius*0.50f;
		minDistanceBetweenHoles = ship.getWidth();
		minDistanceBetweenHolesAndCoins = Coin.coinRadius;
		pendingDistance = 0;
		surplusDistance = -1;
		// Creating a freezer enemy
		enemies.add(new FreezerEnemy(SkyMagGame.getWidth() / 2, SkyMagGame.getHeight() / 3));
	}
	
	public void update(float delta){
		// Updating the game global speed
		updateGlobalSpeed(delta);
		
		// Updating the holes (generating new holes, deleting old holes, etc.)
		updateHoles(delta);
		
		// Updating the coins
		updateCoins(delta);
		
		// Updating the enemies
		updateEnemies(delta);
		
		// Making the ship advance
		ship.advance(delta);
	}
	
	public void updateGlobalSpeed(float delta){
		globalSpeed+=0.3*delta;
	}
	
	public void updateHoles(float delta){
		timeSinceLastCircle += delta; 
		if(surplusDistance > 0){
			pendingDistance += globalSpeed*delta;
			if(pendingDistance >= surplusDistance){
				holes.add(pendingHole);
				if(MathUtils.randomBoolean(chanceOfCoinGeneration)){
					generateCircleOfCoins(pendingHole.x, pendingHole.y, pendingHole.radius);
				}
				surplusDistance = -1;
				pendingDistance = 0;
				timeSinceLastCircle = 0;
			}
		}
		// Generating a circle every 2 seconds approximately
		else if(MathUtils.random(timeSinceLastCircle) > 1.5){
			System.out.println("Generating");
			timeSinceLastCircle = 0;
			float x;
			float y = SkyMagGame.getHeight()+maxRadius;
			float radius;
			
			surplusDistance = -1;
			x = MathUtils.random(SkyMagGame.getWidth()*0.25f, SkyMagGame.getWidth()*0.25f + fieldWidth);
			radius = generateRadius(minRadius, maxRadius);
			ArrayIterator<Circle> iter = new ArrayIterator<Circle>(holes);
			while(iter.hasNext()){
				Circle circle = iter.next();
				if(Math.sqrt((circle.x-x)*(circle.x-x) + (circle.y-y)*(circle.y-y)) < radius + minDistanceBetweenHoles + circle.radius){
					surplusDistance = (float) Math.sqrt((radius + minDistanceBetweenHoles + circle.radius)*(radius + minDistanceBetweenHoles + circle.radius) - (circle.x-x)*(circle.x-x)) - Math.abs(circle.y-y);
				}
			}
			
			if(surplusDistance <= 0){
				holes.add(new Circle(x,y,radius));
				if(MathUtils.randomBoolean(chanceOfCoinGeneration)){
					generateCircleOfCoins(x, y, radius);
				}
			} else {
				pendingHole = new Circle(x,y,radius);
				pendingDistance = 0;
			}
		}
		
		// Removing old holes and making the current holes advance
		ArrayIterator<Circle> iterCircles = new ArrayIterator<Circle>(holes);
		while(iterCircles.hasNext()){
			Circle circle = iterCircles.next();
			if(circle.y + circle.radius < 0){
				iterCircles.remove();
			}
			circle.setPosition(circle.x, circle.y-globalSpeed*delta);
		}
		
		// Updating the hole generation gaussian parameters
		if(muRadius < maxRadius){
			muRadius += maxRadius*0.001*delta;
		} 
		else{
			sigmaRadius += sigmaRadius*0.0001*delta;
		}
	}
	
	public void updateEnemies(float delta){
		ArrayIterator<Enemy> iterEnemies = new ArrayIterator<Enemy>(enemies);
		while(iterEnemies.hasNext()){
			Enemy enemy = iterEnemies.next();
			enemy.update(this, delta);
			// Removing the enemies from the array if they should stop existing
			if(enemy.shouldStopExisting(this)){
				iterEnemies.remove();
			}
			enemy.actOn(ship);
		}
	}
	
	public void updateCoins(float delta){
        ArrayIterator<Coin> iterCoins = new ArrayIterator<Coin>(coins);
        while(iterCoins.hasNext()){
        	Coin coin = iterCoins.next();
        	if(coin.actOn(ship)){
        		iterCoins.remove();
        	} else{
        		coin.setPosition(coin.getX(), coin.getY()-globalSpeed*delta);
        	}
        }
	}
	
	public float generateRadius(float min, float max){
		float z;
		int cpt = 0;
		do{
			Random random = new Random();
			z = (float) random.nextGaussian()*sigmaRadius + muRadius;
			cpt++;
			if(cpt>100){
				return random.nextFloat()*(max - min) + min;
			}
		}while(z < min || z > max);
		
		return z;
	}

	public void generateCircleOfCoins(float x, float y, float radius){
		float distanceFromHoleCenterToCoinCenter = radius+Coin.coinRadius+this.minDistanceBetweenHolesAndCoins;
		
		int nbCircles = (int) ( (MathUtils.PI*distanceFromHoleCenterToCoinCenter) / Coin.coinRadius ); 
		float leftSpace = 2*( (MathUtils.PI*distanceFromHoleCenterToCoinCenter) % Coin.coinRadius );
		
		float spaceBetweenCircles = leftSpace/nbCircles;
		float angleBetweenCircles = (float) (2*Math.atan2(Coin.coinRadius+(spaceBetweenCircles*1.0f/2), distanceFromHoleCenterToCoinCenter));
		
		float currentAngle = 0;
		for(int i=0;i<nbCircles;i++){
			float xCoordinate = x + distanceFromHoleCenterToCoinCenter*MathUtils.cos(currentAngle);
			if(xCoordinate + Coin.coinRadius < World.getRightBorderXCoordinate() && xCoordinate - Coin.coinRadius > World.getLeftBorderXCoordinate()){
				coins.add(new Coin(xCoordinate, y + distanceFromHoleCenterToCoinCenter*MathUtils.sin(currentAngle)));
			}
			currentAngle += angleBetweenCircles;
		}
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
	
	public Array<Coin> getCoins(){
		return coins;
	}
	
	static public float getFieldWidth(){
		return fieldWidth;
	}
	
	static public float getLeftBorderXCoordinate(){
		return (SkyMagGame.getWidth()-fieldWidth)/2;
	}
	
	static public float getRightBorderXCoordinate(){
		return (SkyMagGame.getWidth()+fieldWidth)/2;
	}
	
}
