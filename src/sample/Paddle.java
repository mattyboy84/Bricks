package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;

public class Paddle {

    Rectangle paddle;
    int width = 100;
    int height = 14;
    Vecc2f position;
    Timeline timeline;

    public Paddle(int WIDTH, int HEIGHT, Group group) {
        this.position = new Vecc2f((int) ((WIDTH / 2) - (this.width / 2)), (int) (((HEIGHT / 2) - (this.height / 2)) + (HEIGHT * 0.4)));

        this.paddle = new Rectangle(this.position.x, this.position.y, this.width, this.height);
        this.paddle.setCache(true);
        //
        int FPS = 60;
        this.timeline = new Timeline((new KeyFrame(Duration.seconds((float) 1 / FPS), event -> {

            this.position.x = (int) ((MouseInfo.getPointerInfo().getLocation().x) - this.width / 2);
            //this.position.x = (int) Ball.position.x - this.width / 2;
            this.paddle.relocate(this.position.x, this.position.y);

        })));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
        group.getChildren().add(this.paddle);
    }

    public Rectangle getPaddle() {
        return paddle;
    }
}
