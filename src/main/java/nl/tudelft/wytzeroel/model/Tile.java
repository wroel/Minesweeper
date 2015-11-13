package main.java.nl.tudelft.wytzeroel.model;

public class Tile{

    private boolean isBomb = false;
    private boolean isTurned = false;
    private boolean isFlagged = false;

    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean checkIfBomb() {
            return isBomb;
    }

    public int returnX() {
        return x;
    }

    public int returnY() {
        return y;
    }


    public void setBomb() {
        isBomb = true;
    }

    public void setTurned() {
        isTurned = true;
    }

    public boolean checkIfTurned() {
        return isTurned;
    }

    public boolean checkIfFlagged() {
        return isFlagged;
    }

    public void flag() {
        if(isFlagged){
            isFlagged = false;
        }
        else{
            isFlagged = true;
        }
    }

}
