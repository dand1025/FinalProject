/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package finalproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.*;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;

public class FinalProject extends Application {

    static int frames, fps, components, counter = 0;
    static Timeline player;
    static Circle circ;
    static Line lin;
    static Rectangle rec;
    static ArrayList<String> arl = new ArrayList<>();
    static ArrayList<Integer> spaces = new ArrayList<>();
    static ArrayList<Integer> effects = new ArrayList<>();

    int integer(String str1) {
        int num;
        String str = str1.replaceAll("[^\\d]", " ");
        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();
        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");
        num = Integer.parseInt(str);
        return num;
    }

    ArrayList<Integer> find_space(ArrayList<String> word) {
        ArrayList<Integer> space = new ArrayList<>();
        for (int i = 0; i < word.size(); i++) {
            if ("".equals(arl.get(i))) {
                space.add(i);
            }

        }
        return space;
    }

    ArrayList<Integer> find_effect(ArrayList<String> word) {
        ArrayList<Integer> effect = new ArrayList<>();
        for (int i = 0; i < word.size(); i++) {
            if ("effect".equals(arl.get(i))) {
                effect.add(i);
            }

        }
        return effect;
    }

    @Override
    public void start(Stage stage) {
        //Drawing a Circle 
        if (arl.get(0).contains("frames") && arl.get(1).contains("fps")) {
            fps = integer(arl.get(1));
            frames = integer(arl.get(0));
            components = integer(arl.get(2));
        }

        //what you want to do
        for (int j = 0; j < components; j++) {
            //ArrayList<String> object= new ArrayList<>();
            spaces = find_space(arl);
            effects = find_effect(arl);
            if ("Circle".equals(arl.get(spaces.get(j) + 1))) {
                circ = new Circle();
                circ.setVisible(false);
                circ.setRadius(integer(arl.get(spaces.get(j) + 2)));
                circ.setCenterX(integer(arl.get(spaces.get(j) + 3)));
                circ.setCenterY(integer(arl.get(spaces.get(j) + 4)));
                Color color = Color.rgb(255, 0, 0);
                circ.setFill(color);

                if ("Show".equals(arl.get(effects.get(0) + 1))) {
                    
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            circ.setVisible(true);
                        }
                    }, ((((integer(arl.get(effects.get(0) + 2))) / fps) * 1000))+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                if ("Hide".equals(arl.get(effects.get(1) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            circ.setVisible(false);
                        }
                    }, (((integer(arl.get(effects.get(1) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                
                   if ("Jump".equals(arl.get(effects.get(0) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            circ.setCenterX((integer(arl.get(effects.get(3) + 3))));
                            circ.setCenterY((integer(arl.get(effects.get(3) + 4))));
                        }
                    }, (((integer(arl.get(effects.get(0) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                    if ("Change color".equals(arl.get(effects.get(1) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Color color = Color.rgb(255, 0, 0);
                            circ.setFill(color);
                        }
                    }, (((integer(arl.get(effects.get(1) + 2))) / fps) * 1000)+1000);
                }
            }
            if ("Rect".equals(arl.get(spaces.get(j) + 1))) {
                rec = new Rectangle();
                rec.setVisible(false);
                rec.setHeight(integer(arl.get(spaces.get(j) + 2)));
                rec.setWidth(integer(arl.get(spaces.get(j) + 3)));
                rec.setX(integer(arl.get(spaces.get(j) + 4)));
                rec.setY(integer(arl.get(spaces.get(j) + 5)));
                rec.setStrokeWidth(integer(arl.get(spaces.get(j) + 6)));

                if ("Show".equals(arl.get(effects.get(2) + 1))) {
                    
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            rec.setVisible(true);
                        }
                    }, (((integer(arl.get(effects.get(2) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                if ("Hide".equals(arl.get(effects.get(2) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            rec.setVisible(false);
                        }
                    }, (((integer(arl.get(effects.get(2) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                
                   if ("Jump".equals(arl.get(effects.get(3) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            rec.setX((integer(arl.get(effects.get(3) + 3))));
                            rec.setY((integer(arl.get(effects.get(3) + 4))));
                        }
                    }, (((integer(arl.get(effects.get(3) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                    if ("Change color".equals(arl.get(effects.get(3) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Color color = Color.rgb(255, 0, 0);
                            rec.setFill(color);
                        }
                    }, (((integer(arl.get(effects.get(3) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }

            }

            if ("Line".equals(arl.get(spaces.get(j) + 1))) {
                lin = new Line();
                lin.setVisible(false);
                lin.setStartX(integer(arl.get(spaces.get(j) + 2)));
                lin.setStartY(integer(arl.get(spaces.get(j) + 3)));
                lin.setEndX(integer(arl.get(spaces.get(j) + 4)));
                lin.setEndY(integer(arl.get(spaces.get(j) + 5)));
                lin.setStrokeWidth(integer(arl.get(spaces.get(j) + 6)));
                if ("Show".equals(arl.get(effects.get(1) + 1))) {
                    
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            rec.setVisible(true);
                        }
                    }, (((integer(arl.get(effects.get(1) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                if ("Hide".equals(arl.get(effects.get(0) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            rec.setVisible(false);
                        }
                    }, (((integer(arl.get(effects.get(0) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                
                   if ("Jump".equals(arl.get(effects.get(0) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            rec.setX((integer(arl.get(effects.get(3) + 3))));
                            rec.setY((integer(arl.get(effects.get(3) + 4))));
                        }
                    }, (((integer(arl.get(effects.get(0) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }
                    if ("Change color".equals(arl.get(effects.get(0) + 1))) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Color color = Color.rgb(255, 0, 0);
                            rec.setFill(color);
                        }
                    }, (((integer(arl.get(effects.get(0) + 2))) / fps) * 1000)+1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

                }

            }

        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            Group root = new Group();
            root.getChildren().add(rec);
            root.getChildren().add(circ);

            player = new Timeline(fps);
            player.play();
            Scene scene = new Scene(root, 400, 300);
            stage.setScene(scene);
            stage.show();

            counter++;

        }));
        timeline.setCycleCount(integer(arl.get(0)) / fps);//do it x times

        timeline.play();


    }

    public static void main(String args[]) {

        File file = new File("C:\\Users\\Muhammad\\Documents\\test.txt");
        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                arl.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        launch(args);
    }

}