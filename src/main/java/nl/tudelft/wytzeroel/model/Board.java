package main.java.nl.tudelft.wytzeroel.model;


import java.util.Random;

public class Board{

    private Tile[][] tiles;
    private int gridDimension = 20;
    private int amountOfBombs = 2 * gridDimension;

    public Board(int dimension) {
        setGridDimensions(dimension);
        for (int i = 0; i < gridDimension; i++) {
            for (int j = 0; j < gridDimension; j++) {
                setTile(i, j, new Tile(i, j));
            }
        }
    }

    public void setGridDimensions(int newDimension) {
            gridDimension = newDimension;
            tiles = new Tile[gridDimension][gridDimension];
    }

    public int getGridDimensions() {
        return gridDimension;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile t) {
        tiles[x][y] = t;
    }

    public Board setRandomBoard() {
        Board board = new Board(gridDimension);
        int bombs = 0;
        while(bombs < amountOfBombs) {
            int i = new Random().nextInt(gridDimension);
            int j = new Random().nextInt(gridDimension);
            if(!getTile(i, j).checkIfBomb()){
                board.getTile(i, j).setBomb();
                bombs++;
            }
        }
        return board;
    }

    public String getAmount(int x, int y) {
        if(getTile(x, y).checkIfBomb()){
            return "*";
        }
        int i = 0;
        for (int j = x - 1; j <= x + 1; j++) {
            for (int k = y - 1; k <= y + 1; k++) {
                if(k >= 0 && j >= 0 && k < gridDimension && j < gridDimension && getTile(j, k).checkIfBomb()){
                    i++;
                }
            }
        }
        if(i > 0) {
            return "" + i;
        }
        else{
            return " ";
        }
    }

}
