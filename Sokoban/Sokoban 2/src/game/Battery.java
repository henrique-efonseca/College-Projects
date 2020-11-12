package game;

import gui.ImageMatrixGUI;
import utils.Point2D;
import utils.Vector2D;
import utils.Direction;

public class Battery extends AbstractObject implements InteractiveObject {

    public Battery(Point2D initialPosition) {
	super(initialPosition, "Battery", 5);
    }

    public void interaction(AbstractObject object, Direction direction) {
        if (object instanceof Player) {
            Sokoban.getInstance().removeObject(direction);
            ((Player) object).move(direction); 
            ((Player) object).resetEnergy();
            ImageMatrixGUI.getInstance().update();
            ImageMatrixGUI.getInstance().setMessage("You restored your Energy!");
           
        }
        if (object instanceof MovableObject) {
        	return;
        }
    }



}
