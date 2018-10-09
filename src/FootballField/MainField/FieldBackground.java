package FootballField.MainField;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FieldBackground extends Canvas {

    public FieldBackground(){
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());

    }

    // draw football field
    private void draw() {
        double width = getWidth();
        double height = getHeight();
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeLine(width/2, 0,width/2,height);
        gc.strokeRect(0, 1.5*height/7, width/5, 4*height/7);
        gc.strokeRect(0, 2.5*height/7, width/10, 2*height/7);
        gc.strokeRect(4*width/5, 1.5*height/7, width/5, 4*height/7);
        gc.strokeRect(4.5*width/5, 2.5*height/7, width/10, 2*height/7);
        gc.strokeOval(width/2-width/10,height/2-width/10,width/5,width/5);
    }

}
