package Tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import Sprites.ChestInteractiveObject;
import Sprites.DoorArea;
import Sprites.Ground;
import Sprites.Player;
import Sprites.Wall;

public class WorldCreator {
	public WorldCreator(World world,TiledMap map) {
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		//Set for Wall
		for(MapObject object : map.getLayers().get("wall-object").getObjects().getByType(RectangleMapObject.class)) {
			new Wall(world, map ,object);
		}
		
		//Set for ground-object
		for(MapObject object : map.getLayers().get("ground-object").getObjects().getByType(RectangleMapObject.class)) {			
			new Ground(world, map, object);
		}
	}
}
