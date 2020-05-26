package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.util.ArrayList;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Teleport extends AbstractObject implements InteractiveObject {

    public Teleport(Point2D initialPosition) {
	super(initialPosition, "Portal", 2);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        int aux = 0;
     
        Point2D portalPosition = null;
        for (AbstractObject portal: SokobanGame.getInstance().getObjectsList() ) {
            if ( !(portal.getPosition().equals(this.getPosition())) && portal instanceof Teleport  ){
                 portalPosition = portal.getPosition();
            }
        }
        for (AbstractObject portal: SokobanGame.getInstance().getObjectsList() ) {
             if (portal.getPosition().equals(portalPosition))   {
                 aux++;
             }
        }
                
                
            
        
      
        if (aux <= 1) {
            object.setPosition(portalPosition);   
                       }
   
        else {
            
            ((Movable) object).interaction(SokobanGame.getInstance().getObject(portalPosition), direction);
            object.setPosition(portalPosition);   
        }
        
    }

  

    

}
