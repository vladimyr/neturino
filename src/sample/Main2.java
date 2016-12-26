package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;


public class Main2 extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");
        // stage.initStyle(StageStyle.TRANSPARENT);
        scene = new Scene(new Browser2(),750,500, Color.web("#666970"));
        stage.setScene(scene);

        final Delta dragDelta = new Delta();
        scene.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = stage.getX() - mouseEvent.getScreenX();
            dragDelta.y = stage.getY() - mouseEvent.getScreenY();
            scene.setCursor(Cursor.MOVE);
        });
        scene.setOnMouseReleased(mouseEvent -> scene.setCursor(Cursor.HAND));
        scene.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() + dragDelta.x);
            stage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });

        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

// records relative x and y co-ordinates.
class Delta { double x, y; }

class Browser2 extends Region {

    private final WebView browser = new WebView();

    Browser2() {
        // load the web page
        WebEngine webEngine = browser.getEngine();
        URL url = getClass().getClassLoader().getResource("index.html");
//        webEngine.load("http://www.oracle.com/products/index.html");
        webEngine.load(url.toExternalForm());
        //add the web view to the scene
        getChildren().add(browser);

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 750;
    }

    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}