package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Ground extends InteractiveTileObject{
	public Ground(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds, false);
		fixture.setUserData(this);
	}

	@Override
	public void onHit() {
		Gdx.app.log("Ground", "Collide");
	}
	
	
}
