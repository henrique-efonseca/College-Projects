package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends AbstractObject implements InteractiveObject {

    public Battery(Point2D initialPosition) {
	super(initialPosition, "Battery", 5);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        if (object instanceof Player) {
            SokobanGame.getInstance().removeObject(direction);
            ((Player) object).move(direction); 
            ((Player) object).resetEnergy();
            ImageMatrixGUI.getInstance().setMessage("Master Yoda helped you and restored your Force!");
            System.out.println("has battery");
        }
        if (object instanceof Movable) {
        	return;
        }
    }



}
