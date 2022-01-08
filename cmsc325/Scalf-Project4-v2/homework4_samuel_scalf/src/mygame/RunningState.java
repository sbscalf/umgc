/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author sam
 */
public class RunningState extends AbstractAppState {
    
    private final static Trigger TRIGGER_SHOOT =
            new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    private final static String MAPPING_SHOOT = "Shoot";
    
    private final ActionListener ACTION_LISTENER = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(MAPPING_SHOOT) && !isPressed) {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(cam.getLocation(), cam.getDirection());
                rootNode.collideWith(ray, results);
                if (results.size() > 0) {
                    Geometry target = results.getClosestCollision().getGeometry();
                    if (target.getControl(TargetControl.class) != null) {
                        target.getMaterial().setColor("Color", ColorRGBA.randomColor());
                    }
                }
            }
        }
    };
    
    private SimpleApplication app;
    private Camera cam;
    private Node rootNode;
    private Node guiNode;
    private AssetManager assetManager;
    private InputManager inputManager;
    private final Ray ray = new Ray();
    private static final Box mesh = new Box(1, 1, 1);
    
    private Geometry myCube(String name, Vector3f loc, ColorRGBA color) {
        Geometry geom = new Geometry(name, mesh);
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.setLocalTranslation(loc);
        return geom;
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.cam = this.app.getCamera();
        this.rootNode = this.app.getRootNode();
        this.guiNode = this.app.getGuiNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        
        makeCube("Left", new Vector3f(-5, -10, 0));
        makeCube("Right", new Vector3f(5, 0, 0));
        
        displayCenterMark(app);
        
        inputManager.addMapping(MAPPING_SHOOT, TRIGGER_SHOOT);
        inputManager.addListener(ACTION_LISTENER, MAPPING_SHOOT);
    }
    
    private void makeCube(String name, Vector3f loc) {
        Geometry geom = myCube(name, loc, ColorRGBA.randomColor());
        geom.addControl(new TargetControl());
        rootNode.attachChild(geom);
    }

    private void displayCenterMark(Application app1) {
        Geometry c = myCube("center mark", Vector3f.ZERO, ColorRGBA.White);
        c.scale(4);
        c.setLocalTranslation(app1.getCamera().getWidth() / 2, app1.getCamera().getHeight() / 2, 0);
        guiNode.attachChild(c);
    }
    
    @Override
    public void update(float tpf) {
        
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
        inputManager.deleteMapping(MAPPING_SHOOT);
        inputManager.removeListener(ACTION_LISTENER);
    }
}
