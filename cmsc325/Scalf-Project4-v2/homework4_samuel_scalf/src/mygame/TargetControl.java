/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import java.util.Random;

/**
 *
 * @author sam
 */
public class TargetControl extends AbstractControl {
    
    int speed = 7;
        
    @Override
    protected void controlUpdate(float tpf) {
        Vector3f loc = spatial.getLocalTranslation();
        if (loc.y > 20) {
            spatial.setLocalTranslation(loc.x, -20, loc.z);
            speed = (int)(Math.random() * (20 - 7) + 7);
        }
        Vector3f dir = spatial.getLocalRotation().mult(new Vector3f(0, speed, 0).mult(tpf));
        spatial.move(dir);
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
    
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
