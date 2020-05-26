package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;


public abstract class Movable extends AbstractObject implements InteractiveObject {

    public Movable(Point2D initialPosition, String imageName, int layer) {
        super(initialPosition, imageName, layer);

    }

    public boolean isMovable(Direction direction) {
        Point2D position = SokobanGame.getInstance().getPlayer().getPosition();
        Point2D newPosition = getPosition().plus(direction.asVector());
        Point2D nextPosition = newPosition.plus(direction.asVector());
        Object object = SokobanGame.getInstance().getObject(nextPosition);

        if (SokobanGame.getInstance().getObject(newPosition) instanceof Wall
                
                || (SokobanGame.getInstance().getObject(newPosition) instanceof Movable && SokobanGame.getInstance().getObject(nextPosition) instanceof Movable)
                || (SokobanGame.getInstance().getObject(newPosition) instanceof Movable && SokobanGame.getInstance().getObject(nextPosition) instanceof Wall)
                || (SokobanGame.getInstance().getObject(newPosition) instanceof Movable && SokobanGame.getInstance().getObject(nextPosition) instanceof Battery)
                || (SokobanGame.getInstance().getObject(newPosition) instanceof Movable && SokobanGame.getInstance().getObject(nextPosition) instanceof Hammer)
                || (SokobanGame.getInstance().getObject(newPosition) instanceof Hole && SokobanGame.getInstance().hasObjects(newPosition) )
                || (SokobanGame.getInstance().getObject(newPosition) instanceof Movable && SokobanGame.getInstance().getObject(nextPosition) instanceof Hole && !((Hole) object).isOpened())
                || (SokobanGame.getInstance().getObject(newPosition) instanceof Player && SokobanGame.getInstance().getObject(nextPosition) instanceof Hole && !((Hole) object).isOpened())
                || (SokobanGame.getInstance().getObject(position) instanceof Player
                       && SokobanGame.getInstance().getObject( position.plus(direction.asVector()) ) instanceof Movable 
                       && SokobanGame.getInstance().getObject(position.plus(direction.asVector()).plus(direction.asVector()) ) instanceof Movable   )
                || SokobanGame.getInstance().levelState()            ) 
                 {
            return false;
        }
        else {
            return true;
        }
    }

   
    
    
    public void move(Direction direction) {
       
        
        Point2D newPosition = this.getPosition().plus(direction.asVector());
        Point2D nextPosition = newPosition.plus(direction.asVector());

        if (isMovable(direction) && SokobanGame.getInstance().getObject(newPosition) == null) {
            this.setPosition(newPosition);
            return;
        }
        
        if (isMovable(direction) && SokobanGame.getInstance().getObject(newPosition) != null ) {
           ((InteractiveObject) SokobanGame.getInstance().getObject(newPosition)).interaction(this, direction);
        }
        
  
        
      
        
        
       
        ImageMatrixGUI.getInstance().update();

        }
    
    
   
        

    
}
