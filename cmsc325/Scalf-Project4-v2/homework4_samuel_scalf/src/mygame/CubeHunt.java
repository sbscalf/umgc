package mygame;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;

public class CubeHunt extends SimpleApplication {
    
    public static void main(String[] args) {
        CubeHunt app = new CubeHunt();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(0);
        stateManager.detach(stateManager.getState(DebugKeysAppState.class));
        RunningState running = new RunningState();
        stateManager.attach(running);
    }
    
}
