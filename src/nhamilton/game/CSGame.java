package nhamilton.game;

import static com.tenikkan.abacus.graphics.AB.*;

import com.tenikkan.abacus.graphics.Display;
import com.tenikkan.abacus.input.Keyboard;
import com.tenikkan.abacus.input.Mouse;
import com.tenikkan.abacus.util.GameLoop;

public class CSGame extends GameLoop
{
    private Display display;
    private String title = "CS Game";
    
    @SuppressWarnings("unused")
    private Keyboard keyboard;
    @SuppressWarnings("unused")
    private Mouse mouse;
    
    public CSGame()
    {
        super(-1, 60);
    }

    @Override
    public void init()
    {
        display = new Display(title, 400, 300,400, 300);
        display.show();
        
        keyboard = display.getKeyboard();
        mouse = display.getMouse();
        
        initABContext();
    }
    
    private void initABContext() 
    {
        abSetContext(display.getScreen());
        
        abClearColor3i(16, 16, 16);
        
        abMatrixMode(AB_PROJECTION);
        abLoadIdentity();
        abPerspective(70, display.getRatio(), 0.1f, 1000f);
        
        abMatrixMode(AB_MODELVIEW);
        abLoadIdentity();
        
        abEnable(AB_COLOR);
        abEnable(AB_DEPTH_TESTING);
    }
    
    private float angle = 0;
    
    @Override
    public void update()
    {
        angle += 0.01f;
        display.setTitle(title + " - " + getData());
    }

    @Override
    public void render()
    {
        abClear(AB_FLAG_COLOR_BUFFER | AB_FLAG_DEPTH_BUFFER);
        abLoadIdentity();
        
        abRotate3f(angle*13.0f, angle*10.0f, angle*17.0f);
        abTranslate3f(-2.5f, 0.0f, 6.0f);
        abBegin(AB_QUADS_WIREFRAME);
            //front
            abColor3i(0, 255, 0);
            abVertex3f(-1,-1,-1);
            abVertex3f(-1, 1,-1);
            abVertex3f( 1, 1,-1);
            abVertex3f( 1,-1,-1);
            //back
            abColor3i(255, 0, 0);
            abVertex3f(-1,-1, 1);
            abVertex3f(-1, 1, 1);
            abVertex3f( 1, 1, 1);
            abVertex3f( 1,-1, 1);
            //right
            abColor3i(0, 0, 255);
            abVertex3f( 1,-1,-1);
            abVertex3f( 1, 1,-1);
            abVertex3f( 1, 1, 1);
            abVertex3f( 1,-1, 1);
            //left
            abColor3i(255, 0, 255);
            abVertex3f(-1,-1, 1);
            abVertex3f(-1, 1, 1);
            abVertex3f(-1, 1,-1);
            abVertex3f(-1,-1,-1);
            //top
            abColor3i(0, 255, 255);
            abVertex3f(-1, 1,-1);
            abVertex3f(-1, 1, 1);
            abVertex3f( 1, 1, 1);
            abVertex3f( 1, 1,-1);
            //bottom
            abColor3i(255, 255, 0);
            abVertex3f(-1,-1, 1);
            abVertex3f(-1,-1,-1);
            abVertex3f( 1,-1,-1);
            abVertex3f( 1,-1, 1);
        abEnd();
        
        abLoadIdentity();
        abRotate3f(0, angle*100, 0);
        abTranslate3f(1.5f, 0, 4);
        abBegin(AB_QUADS);
            abColor3i(255, 0, 0);
            abVertex3f(-1,-1, 0);
            abColor3i(0, 255, 0);
            abVertex3f(-1, 1, 0);
            abColor3i(0, 0, 255);
            abVertex3f( 1, 1, 0);
            abColor3i(255, 255, 0);
            abVertex3f( 1,-1, 0);
        abEnd();
        
        display.render();
    }
    
}
