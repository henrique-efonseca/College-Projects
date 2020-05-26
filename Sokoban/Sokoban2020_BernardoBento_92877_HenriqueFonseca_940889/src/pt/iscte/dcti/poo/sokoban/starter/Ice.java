package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Ice extends AbstractObject implements InteractiveObject {

    public Ice(Point2D initialPosition) {
	super(initialPosition, "Ice", 3);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        Point2D newPosition = object.getPosition().plus(direction.asVector());
        Point2D position = this.getPosition();
        System.out.println(position);
        
       
        
         if(SokobanGame.getInstance().getObject(newPosition) instanceof Ice && SokobanGame.getInstance().getObject(newPosition.plus(direction.asVector())) instanceof Movable )
        {
            //((Movable) object).interaction(null, direction);
            ((InteractiveObject) SokobanGame.getInstance().getObject(position.plus(direction.asVector()))).interaction(object, direction);
           return;
        }
         
         if(SokobanGame.getInstance().getObject(newPosition) instanceof Movable && SokobanGame.getInstance().getObject(newPosition.plus(direction.asVector())) instanceof Ice )
         {
             ((Movable) object).interaction(null, direction);
             ((InteractiveObject) SokobanGame.getInstance().getObject(position.plus(direction.asVector()))).interaction(object, direction);
             ((Movable) object).interaction(object, direction);
            return;
         }
       
            
            
        else {
        while (SokobanGame.getInstance().getObject(position) instanceof Ice) {
            ((Movable) object).interaction(null, direction);
            position = position.plus(direction.asVector());
            
         
        }
        ((Movable) object).interaction(SokobanGame.getInstance().getObject(position), direction);
        
       
        
            
        }
        
       
       
      
        System.out.println(position);
        
      
        
        
       
            
            
            
            
    
    }

}
