package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Target extends AbstractObject implements InteractiveObject {

	public Target(Point2D initialPosition) {
		super(initialPosition, "Target", 2);
	}

    @Override
    public void interaction(AbstractObject object, Direction direction) {
     if(object instanceof Box) {
    	 this.setImageName("Box_On_Target");
     }
        
    }

	

}
  