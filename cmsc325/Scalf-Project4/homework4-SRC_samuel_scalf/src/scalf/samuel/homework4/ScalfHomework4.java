package scalf.samuel.homework4;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;

/**
 * This is the program for my Homework 4 assignment in CMSC325.
 * @author Samuel Scalf
 */
public class ScalfHomework4 extends SimpleApplication
        implements AnalogListener {
    
    private static final ColorRGBA GOLD = new ColorRGBA(1.0f, 0.84313726f, 0.0f, 1.0f);
    
    private Geometry target;
    private BulletAppState bulletAppState;
    private Material mat_terrain;
    private TerrainQuad terrain;
    private RigidBodyControl landscape;
    private Node playerNode;
    private Spatial player;
    private Quaternion initialQ;
    private Node terrainNode;
    private Node targetNode;
    
    @Override
    public void simpleInitApp() {
        initializeState();
        setUpTarget();
        setUpTerrain();
        setUpPlayer();
        setUpKeys();
        setUpLight();
        
    }
    
    private void initializeState() {
        initialQ = new Quaternion();
        initialQ.fromAngles(0, 1.5708f, 0);
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
    }
    
    private void setUpTarget() {
        targetNode = new Node();
        Material mat = assetManager.loadMaterial("Materials/Target.j3m");
        Box box = new Box(1, 1, 1);
        target = new Geometry("Target", box);
        target.setMaterial(mat);
        target.setLocalRotation(new Quaternion(new float[] {0.785398f, 0, 0.785398f}));
        targetNode.setLocalTranslation(new Vector3f(-227, 80, 40));
        
        targetNode.attachChild(target);
        rootNode.attachChild(targetNode);
    }
    
    private void setUpTerrain() {
        terrainNode = new Node();
        mat_terrain = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");
        mat_terrain.setTexture("Alpha", assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));
        
        Texture grass = assetManager.loadTexture("Textures/Terrain/splat/grass.jpg");
        grass.setWrap(Texture.WrapMode.Repeat);
        mat_terrain.setTexture("Tex1", grass);
        mat_terrain.setFloat("Tex1Scale", 64f);
        
        Texture dirt = assetManager.loadTexture("Textures/Terrain/splat/dirt.jpg");
        dirt.setWrap(Texture.WrapMode.Repeat);
        mat_terrain.setTexture("Tex2", dirt);
        mat_terrain.setFloat("Tex2Scale", 32f);
        
        Texture rock = assetManager.loadTexture("Textures/Terrain/splat/road.jpg");
        rock.setWrap(Texture.WrapMode.Repeat);
        mat_terrain.setTexture("Tex3", rock);
        mat_terrain.setFloat("Tex3Scale", 128f);
        
        Texture heightMapImage = assetManager.loadTexture("Textures/Terrain/splat/mountains512.png");
        AbstractHeightMap heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        heightmap.load();
        
        int patchSize = 65;
        terrain = new TerrainQuad("my terrain", patchSize, 513, heightmap.getHeightMap());
        terrain.setMaterial(mat_terrain);
        terrain.setLocalTranslation(0, -100, 0);
        terrain.setLocalScale(2f, 2f, 2f);
        
        TerrainLodControl control = new TerrainLodControl(terrain, getCamera());
        terrain.addControl(control);
        
        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape(terrain);
        landscape = new RigidBodyControl(sceneShape, 0);
        terrain.addControl(landscape);
        
        terrainNode.attachChild(terrain);
        rootNode.attachChild(terrainNode);
        bulletAppState.getPhysicsSpace().add(landscape);
    }
    
    private void setUpPlayer() {
        playerNode = new Node();
        player = assetManager.loadModel("Models/Tank/tank.j3o");
        Material playerMat = assetManager.loadMaterial("Models/Tank/tank.j3m");
        player.setMaterial(playerMat);
        player.scale(0.5f);
        player.setLocalRotation(initialQ);
        CollisionShape playerShape = CollisionShapeFactory.createDynamicMeshShape(player);
        RigidBodyControl playerControl = new RigidBodyControl(playerShape, 1f);
        playerNode.addControl(playerControl);
        bulletAppState.getPhysicsSpace().add(playerControl);
        playerNode.attachChild(player);
        
        flyCam.setEnabled(false);
        CameraNode camNode = new CameraNode("Camera Node", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        playerNode.attachChild(camNode);
        
        camNode.setLocalTranslation(new Vector3f(-40, .35f, 0));
        camNode.setLocalRotation(initialQ);
        
        player.setLocalTranslation(0, -0.5f, 0);
        player.setLocalRotation(initialQ);
        playerControl.setGravity(new Vector3f(0, -30f, 0));
        playerControl.setPhysicsLocation(new Vector3f(-143, 38, 10));
        
        playerNode.setLocalTranslation(new Vector3f(-143, 45, 10));
        
        rootNode.attachChild(playerNode);
    }
    
    private void setUpKeys() {
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Left", "Right", "Up", "Down", "Jump");
    }
    
    @Override
    public void onAnalog(String name, float value, float tpf) {
        speed = 10;
        Vector3f currentDirection = playerNode.getLocalRotation().getRotationColumn(0);
        Vector3f movement = currentDirection.mult(tpf);
        float rotation = value * speed;
        
        switch (name) {
            case "Up":
                playerNode.move(movement.mult(speed));
                break;
            case "Down":
                playerNode.move(movement.mult(-speed));
                break;
            case "Left":
                playerNode.rotate(0, rotation, 0);
                break;
            case "Right":
                playerNode.rotate(0, -rotation, 0);
                break;
            default:
                break;
        }
    }
    
    private void setUpLight() {
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
        
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }
    
    public static void main(String... args) {
        (new ScalfHomework4()).start();
    }
    
}
