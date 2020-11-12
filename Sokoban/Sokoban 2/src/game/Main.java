
package game;


import gui.ImageMatrixGUI;

public class Main {


  
    public static void main(String[] args)  {

	Sokoban Game = Sokoban.getInstance();

	ImageMatrixGUI.getInstance().setSize(10, 10);
        ImageMatrixGUI.getInstance().setName("SokobanGame");
        
        ImageMatrixGUI.getInstance().registerObserver(Game);
       
        ImageMatrixGUI.getInstance().go();
        ImageMatrixGUI.getInstance().update();
        
        
    } 

}
