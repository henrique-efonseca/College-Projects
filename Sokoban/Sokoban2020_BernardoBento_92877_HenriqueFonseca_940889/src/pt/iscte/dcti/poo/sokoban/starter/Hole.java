package pt.iscte.dcti.poo.sokoban.starter;

import java.io.FileNotFoundException;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends AbstractObject implements InteractiveObject {
	private boolean opened;

    public Hole(Point2D initialPosition) {

        super(initialPosition, "Hole", 9);
        opened = true;
       
    }
    
    public boolean isOpened(){
    	return this.opened;
    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
       if (!(SokobanGame.getInstance().hasObjects(this.getPosition()))) {
           if(object instanceof Player && opened) {
               SokobanGame.getInstance().resetCurrentLevel();
               return;
               
           }
           
           if(object instanceof Player && !opened) {
        	   return;
        	}
           if(object instanceof Movable && opened ) {
               
               SokobanGame.getInstance().removeObject(direction);
               
           }
           if(object instanceof Movable && !opened ) {
        	   return;
           }
           
           if(object instanceof BigStone ) {
        	  
        	  
        	   this.setImageName("Wall");
        	   this.opened = false;
        	   ImageMatrixGUI.getInstance().setMessage("You have sent the Mandalorian to space. Now the Hole is closed");
           }
       }
    
        
       
         
        
       
    
        
    }


    
    


    
}
