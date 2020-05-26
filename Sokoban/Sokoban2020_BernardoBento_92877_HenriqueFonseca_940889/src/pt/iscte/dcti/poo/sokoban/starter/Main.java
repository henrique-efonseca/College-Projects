package pt.iscte.dcti.poo.sokoban.starter;

import java.io.FileNotFoundException;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Main {


  
    public static void main(String[] args)  {

	SokobanGame Game = SokobanGame.getInstance();

	ImageMatrixGUI.setSize(10, 10);
        ImageMatrixGUI.getInstance().setName("Sokoban Wars");
        ImageMatrixGUI.getInstance().registerObserver(Game);
        ImageMatrixGUI.getInstance().go();
        ImageMatrixGUI.getInstance().setIcon("icon/Game_Icon.png");

    }

}
