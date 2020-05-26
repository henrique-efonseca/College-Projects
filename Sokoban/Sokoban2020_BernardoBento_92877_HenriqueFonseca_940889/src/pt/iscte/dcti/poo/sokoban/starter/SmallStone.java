package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends Movable implements InteractiveObject {

    public SmallStone(Point2D initialPosition) {
        super(initialPosition, "SmallStone", 4);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        if (object instanceof Player && isMovable(direction)) {
            
          
        
            this.move(direction);
            ((Player) object).move(direction);
        }

    }

}
