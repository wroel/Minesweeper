package main.java.nl.tudelft.wytzeroel.view;


import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.nl.tudelft.wytzeroel.model.Board;
import main.java.nl.tudelft.wytzeroel.model.Tile;

public class BoardView {

    private Board board;
    private TileView[][] tileViews;
    private int dimension = 20;
    private int MAX_DIMENSION = 600;

    public BoardView(int dimension) {
        setGridDimensions(dimension);
        this.board = new Board(dimension).setRandomBoard();
        for (int i = 0; i < board.getGridDimensions(); i++) {
            for (int j = 0; j < board.getGridDimensions(); j++) {
                setTileViews(i, j, new TileView(board.getTile(i, j), board.getAmount(i, j)));
            }
        }
    }

    public class TileView extends StackPane {

        private Tile tile;
        private Text text;
        private Rectangle border = new Rectangle(MAX_DIMENSION / dimension, MAX_DIMENSION / dimension);
        private String string;

        public TileView(Tile tile, String string) {

        this.tile = tile;
        this.text = new Text(" ");
        this.string = string;

        if(string.equals("1")){
            text.setFill(Color.BLACK);
        }
        else if(string.equals("2")){
            text.setFill(Color.DARKRED);
        }
        else if(string.equals("3")){
            text.setFill(Color.DARKGREEN);
        }
        else if(string.equals("4")){
            text.setFill(Color.DARKBLUE);
        }
        else if(string.equals("5")){
            text.setFill(Color.CYAN);
        }
        else if(string.equals("6")){
            text.setFill(Color.YELLOW);
        }
        else if(string.equals("7")){
            text.setFill(Color.ORANGE);
        }
        else if(string.equals("8")){
            text.setFill(Color.PURPLE);
        }

        text.setFont(Font.font(MAX_DIMENSION / dimension));
        border.setFill(Color.GRAY);
        border.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);

        if(tile.checkIfTurned()){
            text.setText(string);
            border.setFill(Color.LIGHTGRAY);
        }

        setChildren();

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!tile.checkIfFlagged()) {

                    leftClick();
                    expand(this);
                }
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                rightClick();
                tile.flag();
            }
        });
        }

        public void leftClick() {
            if(!getTile().checkIfBomb()) {
                border.setFill(Color.LIGHTGRAY);
                text.setText(string);
                tile.setTurned();
            }
            else {
                turnBomb();
                exposeBombs();
            }
        }

        public void turnBomb() {
            border.setFill(Color.DARKORANGE);
            text.setText(string);
            tile.setTurned();
        }

        private void rightClick() {
            if(tile.checkIfFlagged()) {
                border.setFill(Color.GRAY);
                text.setText(" ");
            }
            else{
                border.setFill(Color.DARKRED);
                text.setText("+");
            }
        }

        public void expose() {
            border.setFill(Color.LIGHTGRAY);
            text.setText(string);
            tile.setTurned();
        }

        public void setChildren() {
        getChildren().addAll(border, text);
    }

        public Tile getTile(){
        return tile;
    }

    }

    private void setGridDimensions(int newDimension) {
        dimension = newDimension;
        tileViews = new TileView[dimension][dimension];
    }

    public TileView getTileView(int x, int y){
        return tileViews[x][y];
    }

    public void setTileViews(int x, int y, TileView t){
        tileViews[x][y] = t;
    }

    public void expand(TileView t) {
        int a = 0;
        TileView[] tiles = new TileView[9];
        int x = t.getTile().returnX();
        int y = t.getTile().returnY();
        if(board.getAmount(x, y).equals(" ")) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if(i >= 0 && j >= 0 && i < board.getGridDimensions() && j < board.getGridDimensions()) {

                        if(board.getAmount(i, j).equals(" ") && !board.getTile(i, j).checkIfTurned()) {
                            tiles[a] = tileViews[i][j];
                            a++;
                        }
                        tileViews[i][j].expose();

                    }
                }
            }
        }
        if(a > 0) {
            for (int b = 0; b < a; b++) {
                expand(tiles[b]);
            }
        }

    }

    private void exposeBombs() {
        for (int i = 0; i < board.getGridDimensions(); i++) {
            for (int j = 0; j < board.getGridDimensions(); j++) {
                if(board.getTile(i, j).checkIfBomb()){
                    tileViews[i][j].turnBomb();
                }
            }
        }
    }


}
