package Tools;

import Sprites.*;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		if(fixA.getUserData() == "body" || fixB.getUserData() == "body") {
			Fixture body = fixA.getUserData() == "body" ? fixA : fixB;
			Fixture object = body == fixA ? fixB : fixA;
			
			//if object is interactive tile object
			if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
				((InteractiveTileObject) object.getUserData()).onHit();
			}
			if (object.getUserData() != null && Melee.class.isAssignableFrom(object.getUserData().getClass())) {
				((Melee) object.getUserData()).onHit();
			}
			if (object.getUserData() != null && Speed.class.isAssignableFrom(object.getUserData().getClass())) {
				((Speed) object.getUserData()).onHit();
			}
			if (object.getUserData() != null && Ranged.class.isAssignableFrom(object.getUserData().getClass())) {
				((Ranged) object.getUserData()).onHit();
			}
			if (object.getUserData() != null && EnemyFire.class.isAssignableFrom(object.getUserData().getClass())) {
				((EnemyFire) object.getUserData()).onHit();
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
