package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hammer extends AbstractObject implements InteractiveObject {

    public Hammer(Point2D initialPosition) {
	super(initialPosition, "Hammer", 5);
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
      if(object instanceof Player) {
         
          SokobanGame.getInstance().removeObject(direction);
          ((Player) object).setHammer();
          ((Player) object).move(direction);
          ImageMatrixGUI.getInstance().setMessage("You found chewie! Now you can destroy grey walls");
          System.out.println("has hammer");
      }
      if (object instanceof Movable) {
      	return;
      }
        
    }

  
}
