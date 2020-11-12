package game;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import gui.ImageMatrixGUI;
import utils.Point2D;
import utils.Vector2D;
import utils.Direction;

public class Player extends MovableObject implements InteractiveObject {

    private int moves;
    private int energy;
    private boolean hasHammer;

    public Player(Point2D initialPosition) {

        super(initialPosition, "Player_U", 5);
        this.energy = 100;
        this.moves = 0;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getMoves() {
        return this.moves;
    }

    public void resetMoves() {
        this.moves = 0;
        this.hasHammer = false;
    }

    public void setHammer() {
        this.hasHammer = true;

    }

    public boolean getHammer() {
        return hasHammer;
    }

    public void resetEnergy() {
        this.energy = 100;
    }

    @Override 
    public void move(Direction direction) {
        Point2D newPosition = this.getPosition().plus(direction.asVector());
        if (isMovable(direction)
                
                && !(Sokoban.getInstance().getObject(getPosition().plus(direction.asVector())) instanceof Box)
                && !(Sokoban.getInstance().getObject(getPosition().plus(direction.asVector())) instanceof SmallStone)
                && !(Sokoban.getInstance().getObject(getPosition().plus(direction.asVector())) instanceof BigStone)) { 
            energy--;
            moves++;
        }
        super.move(direction);

        this.setImageName("Player_" + direction.name().charAt(0));

    }

    @Override
    public void interaction(AbstractObject object, Direction direction) {
        Point2D newPosition = this.getPosition().plus(direction.asVector());

        if (object instanceof InteractiveObject && !(object instanceof Player)) {
            ((InteractiveObject) object).interaction(this, direction);
        }

        else {
            this.setPosition(newPosition);
        }

    }

}
