package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Ball {

    Circle ball;
    public static Vecc2f position;
    Vecc2f velocity;
    int r;
    Timeline timeline;
    Random rand = new Random();
    int lives;

    public Ball(int WIDTH, int HEIGHT, Group group, Paddle paddle, ArrayList<Bricks> bricks, int ballLives) {
        this.r = 10;
        this.lives=ballLives;
        this.position = new Vecc2f(((int) (WIDTH / 2 - (this.r / 2))), (int) (HEIGHT / 2 - (this.r / 2)));
        //this.position=new Vecc2f(WIDTH/2,100);
        this.ball = new Circle(position.x, position.y, this.r, Color.RED);
        this.ball.setCache(true);
        this.velocity = new Vecc2f(((rand.nextInt(2)*2) - 1) * 4,4);
        int FPS = 60;
        this.timeline = new Timeline((new KeyFrame(Duration.seconds((float) 1 / FPS), event -> {
            //
            this.position.add(velocity);
            //
            this.ball.relocate(position.x, position.y);
            //

            //
            if ((this.position.y + (2 * this.r)) > HEIGHT) {//bottom wall
                position.set((((int) (WIDTH / 2 - (this.r / 2))) + 100), (int) (HEIGHT / 2 - (this.r / 2)));
                this.lives--;
            }
            if (this.position.y < 0) {//top wall
                this.velocity.y = (this.velocity.y * -1);
            }
            if (this.position.x < 0 || (this.position.x + (2 * this.r)) > WIDTH) {//left and right wall
                this.velocity.x = (this.velocity.x * -1);
            }
            if (this.ball.getBoundsInParent().intersects(paddle.getPaddle().getBoundsInParent())) {//paddle
                this.velocity.y = (this.velocity.y * -1);
            }
            for (int i = 0; i < bricks.size(); i++) {//checks all the bricks
                if (this.ball.getBoundsInParent().intersects(bricks.get(i).getBrick().getBoundsInParent())) {
                    group.getChildren().remove(bricks.get(i).getBrick());
                    bricks.remove(bricks.get(i));
                    this.velocity.y = (this.velocity.y * -1);
                    this.velocity.mult((float) 1.02);
                    Main.score++;
                }
            }
            if (bricks.size()==0){
                this.velocity.mult((float) 0.8);
                Main.columns++;
                Main.setup();
            }
            //
            if (this.lives<=0){
                Main.writeToDatabase();
                System.exit(0);
            }
            //
        })));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
        group.getChildren().add(this.ball);
    }
}