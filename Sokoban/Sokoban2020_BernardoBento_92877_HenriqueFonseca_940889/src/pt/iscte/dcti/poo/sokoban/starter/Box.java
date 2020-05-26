package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends Movable implements InteractiveObject {

    public Box(Point2D initialPosition) {
        super(initialPosition, "Box", 4);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {

        if (object instanceof Player && this.isMovable(direction)) {
            this.move(direction);
            ((Player) object).move(direction);
            

        }
                
        else {

            Point2D newPosition = this.getPosition().plus(direction.asVector());
            this.setPosition(newPosition);
        }
        
        
      

    }

}