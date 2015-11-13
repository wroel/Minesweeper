package main.java.nl.tudelft.wytzeroel;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.nl.tudelft.wytzeroel.model.Board;
import main.java.nl.tudelft.wytzeroel.view.BoardView;
import main.java.nl.tudelft.wytzeroel.view.BoardView.TileView;

public class Launcher extends Application {

    public static final int DEFAULT_SCREEN_WIDTH = 600;
    public static final int DEFAULT_SCREEN_HEIGHT = 600;

    private int gridDimension = 20;

    private BoardView boardView = new BoardView(gridDimension);


    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);

        for (int i = 0; i < gridDimension; i++) {
            for (int j = 0; j < gridDimension; j++) {

                TileView tileView = boardView.getTileView(i, j);

                tileView.setTranslateX(j * DEFAULT_SCREEN_HEIGHT / gridDimension);
                tileView.setTranslateY(i * DEFAULT_SCREEN_WIDTH / gridDimension);

                root.getChildren().add(tileView);

            }
        }

        return root;
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.setScene(new Scene(createContent()));
        stage.show();



    }


    public static void main(String[] args) {
        launch(args);

    }
}
