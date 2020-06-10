package game;

import gui.ImageMatrixGUI;
import utils.Point2D;
import utils.Vector2D;
import utils.Direction;;

public class Ice extends AbstractObject implements InteractiveObject {

    public Ice(Point2D initialPosition) {
        super(initialPosition, "Ice", 3);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        Point2D newPosition = object.getPosition().plus(direction.asVector());
        Point2D position = this.getPosition();
        System.out.println(position);
        
       
        
         if(Sokoban.getInstance().getObject(newPosition) instanceof Ice && Sokoban.getInstance().getObject(newPosition.plus(direction.asVector())) instanceof MovableObject )
        {
            //((Movable) object).interaction(null, direction);
            ((InteractiveObject) Sokoban.getInstance().getObject(position.plus(direction.asVector()))).interaction(object, direction);
           return;
        }
         
         if(Sokoban.getInstance().getObject(newPosition) instanceof MovableObject && Sokoban.getInstance().getObject(newPosition.plus(direction.asVector())) instanceof Ice )
         {
             ((MovableObject) object).interaction(null, direction);
             ((InteractiveObject) Sokoban.getInstance().getObject(position.plus(direction.asVector()))).interaction(object, direction);
             ((MovableObject) object).interaction(object, direction);
            return;
         }
       
            
            
        else {
        while (Sokoban.getInstance().getObject(position) instanceof Ice) {
            ((MovableObject) object).interaction(null, direction);
            position = position.plus(direction.asVector());
            
         
        }
        ((MovableObject) object).interaction(Sokoban.getInstance().getObject(position), direction);
        
       
        
            
        }
        
       
       
      
        System.out.println(position);
        
      
        
        
       
            
            
            
            
    
    }

}
