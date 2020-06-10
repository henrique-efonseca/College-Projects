package game;

import gui.ImageMatrixGUI;
import utils.Direction;
import utils.Point2D;


public abstract class MovableObject extends AbstractObject implements InteractiveObject {

    public MovableObject(Point2D initialPosition, String imageName, int layer) {
        super(initialPosition, imageName, layer);

    }

    public boolean isMovable(Direction direction) {
        Point2D position = Sokoban.getInstance().getPlayer().getPosition();
        Point2D newPosition = getPosition().plus(direction.asVector());
        Point2D nextPosition = newPosition.plus(direction.asVector());
        Object object = Sokoban.getInstance().getObject(nextPosition);

        if (Sokoban.getInstance().getObject(newPosition) instanceof Wall
                
                || (Sokoban.getInstance().getObject(newPosition) instanceof MovableObject && Sokoban.getInstance().getObject(nextPosition) instanceof MovableObject)
                || (Sokoban.getInstance().getObject(newPosition) instanceof MovableObject && Sokoban.getInstance().getObject(nextPosition) instanceof Wall)
                || (Sokoban.getInstance().getObject(newPosition) instanceof MovableObject && Sokoban.getInstance().getObject(nextPosition) instanceof Battery)
                || (Sokoban.getInstance().getObject(newPosition) instanceof MovableObject && Sokoban.getInstance().getObject(nextPosition) instanceof Hammer)
                || (Sokoban.getInstance().getObject(newPosition) instanceof Hole && Sokoban.getInstance().hasObjects(newPosition) )
                || (Sokoban.getInstance().getObject(newPosition) instanceof MovableObject && Sokoban.getInstance().getObject(nextPosition) instanceof Hole && !((Hole) object).isOpened())
                || (Sokoban.getInstance().getObject(newPosition) instanceof Player && Sokoban.getInstance().getObject(nextPosition) instanceof Hole && !((Hole) object).isOpened())
                || (Sokoban.getInstance().getObject(position) instanceof Player
                       && Sokoban.getInstance().getObject( position.plus(direction.asVector()) ) instanceof MovableObject 
                       && Sokoban.getInstance().getObject(position.plus(direction.asVector()).plus(direction.asVector()) ) instanceof MovableObject   )
                || Sokoban.getInstance().levelState()            ) 
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

        if (isMovable(direction) && (Sokoban.getInstance().getObject(newPosition) == null )) {
            this.setPosition(newPosition);
            return;
        }
        
        if (isMovable(direction) && Sokoban.getInstance().getObject(newPosition) != null ) {
           ((InteractiveObject) Sokoban.getInstance().getObject(newPosition)).interaction(this, direction);
           return;
        }
        
  
        
      
        
        
       
        ImageMatrixGUI.getInstance().update();

        }
    
    
   
        

    
}
