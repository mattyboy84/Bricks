package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main extends Application {

    //int WIDTH = 1920;
    //int HEIGHT = 1080;

    static Rectangle2D screenBounds = Screen.getPrimary().getBounds();

    static int WIDTH = (int) screenBounds.getWidth();
    static int HEIGHT = (int) screenBounds.getHeight();

    int ballLives = 3;

    static Group group = new Group();
    static Scene scene = new Scene(group, WIDTH, HEIGHT);

    Paddle paddle = new Paddle(WIDTH, HEIGHT, group);
    static ArrayList<Bricks> bricks = new ArrayList<>();
    Ball ball = new Ball(WIDTH, HEIGHT, group, paddle, bricks, ballLives);

    static int rows = 15;
    static int columns = 4;

    static int score = 0;


    @Override
    public void start(Stage stage) {

        setup();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void writeToDatabase() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\password.txt"));//read in pw from a txt file
            String password = br.readLine();
            //
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", password);
            Statement statement = connection.createStatement();
            statement.execute("insert into bricksTable(Score) Values (" + score + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setup() {
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                bricks.add(new Bricks(WIDTH, HEIGHT, group, i, j, rows, columns));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
