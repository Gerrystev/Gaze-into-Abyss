package Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.Player.State;

public class PistolBullet extends Sprite {
	public World world;
	public Body b2body;
	private TextureRegion bullet;
	private Vector2 position;
	private Vector2 nowPosition;
	
	private boolean toRight;
	private int damage;
	private boolean isHit;
	private boolean stopTimer;
	private boolean destroy;
	private float timer;
	private boolean miss;

	private Enemy enemy;
	private boolean hasDamaged;
	
	public PistolBullet(World world, Vector2 position, boolean miss) {
		super(new AtlasRegion(new TextureAtlas("Resources/Item/bullet.pack").findRegion("bullet")));
		this.world = world;		
		this.toRight = true;
		this.stopTimer = false;
		this.destroy = false;
		this.miss = miss;
		this.damage = 4;
		hasDamaged = false;
		isHit = false;
		timer = 0;
		this.position = position;
		bullet = new TextureRegion(getTexture(), 0,0,12,4);
		setBounds(0,0,12 / GazeintoAbyss.PPM,4 / GazeintoAbyss.PPM);
		definePistolBullet();
	}
	public void update(float dt) {
		setPosition(position.x - getWidth()/8,position.y - getHeight()/2);
		if(toRight) {
			b2body.setLinearVelocity(6f, 0);
		}
		else {
			b2body.setLinearVelocity(-6f,0);
		}
		if(hasDamaged) {
			enemy.setHP(enemy.getHP() - damage);
			hasDamaged = false;
		}
		
		if(!stopTimer) {
			timer++;
		}
		if(timer > 40f) {
			isHit = true;			
		}
		setPosition(new Vector2(b2body.getPosition().x, b2body.getPosition().y));
		nowPosition = new Vector2(b2body.getPosition().x * GazeintoAbyss.PPM, b2body.getPosition().y * GazeintoAbyss.PPM);
		if(isHit) {
			world.destroyBody(b2body);
			destroy = true;
			isHit = false;
			stopTimer = true;
			timer = 0; 
		}
	}
	public void defineHitBox(int x, int y) {
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(x / GazeintoAbyss.PPM, y / GazeintoAbyss.PPM);
		
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.isSensor = true;
		b2body.createFixture(fdef);
		b2body.createFixture(fdef).setUserData(this);
	}
	
	public void definePistolBullet() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(position.x / GazeintoAbyss.PPM,position.y / GazeintoAbyss.PPM);
		bdef.type = BodyDef.BodyType.KinematicBody;
		b2body = world.createBody(bdef);
		
		defineHitBox(3,1);
	}
	public void setToRight(boolean toRight) {
		this.toRight = toRight;
	}
	public boolean getDestroy() {
		return destroy;
	}
	
	public void onHit(Enemy enemy) {
		isHit = true;
		if(!miss) {
			this.enemy = enemy;
			hasDamaged = true;
		}
	}
	
	public void setHit(boolean hit) {
		this.isHit = hit;
	}
	public void setPosition(Vector2 positions) {
		this.position = positions;
	}
	
	public Vector2 getNowPosition() {
		return nowPosition;
	}
	public void setNowPosition(Vector2 nowPosition) {
		this.nowPosition = nowPosition;
	}
	public Vector2 getPosition() {
		return position;
	}
}
