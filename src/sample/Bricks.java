package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Bricks {

    Rectangle brick;
    int brickSpacing = 5;
    int brickWidth;
    int numOfBricks;
    int brickHeight = 20;
    Random rand = new Random();

    public Bricks(int WIDTH, int HEIGHT, Group group, int i, int j, int rows, int cols) {
        this.numOfBricks = rows;
        this.brickWidth = (WIDTH / numOfBricks) - brickSpacing;
        this.brick = new Rectangle((int) (brickSpacing / 2) + (i * (brickWidth + brickSpacing)), 200 + (j * (brickHeight + brickSpacing)), brickWidth, brickHeight);
        this.brick.setFill(Color.rgb((int) (255 * rand.nextFloat()), (int) (255 * rand.nextFloat()), (int) (255 * rand.nextFloat())));
        group.getChildren().add(this.brick);

    }

    public Rectangle getBrick() {
        return brick;
    }
}
