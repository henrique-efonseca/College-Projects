package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class CrackedWall extends AbstractObject implements InteractiveObject {

    public CrackedWall(Point2D initialPosition) {
        super(initialPosition, "CrackedWall", 5);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        System.out.println( ((Player) object).getHammer() );
        if (object instanceof Player && ((Player) object).getHammer()) {
            SokobanGame.getInstance().removeObject(direction);
            ((Player) object).move(direction);
            System.out.println("wall");
            

        }

    }

}
