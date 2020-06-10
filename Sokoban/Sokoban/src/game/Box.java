package game;

import gui.ImageMatrixGUI;
import utils.Point2D;
import utils.Vector2D;
import utils.Direction;


public class Box extends MovableObject implements InteractiveObject {

    public Box(Point2D initialPosition) {
        super(initialPosition, "Box", 4);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        Point2D newPosition = this.getPosition().plus(direction.asVector());
        
        if (object instanceof Player && this.isMovable(direction)) {
            this.move(direction);
            ((Player) object).move(direction);
            
        }
                 
        else {

            
            this.setPosition(newPosition);
        }
        
        
      

    }

}