package lift.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LiftRect extends JPanel {
    public int x;
    public int y;
    public int width;
    public int height;
    public int floorHeight = 10;
    public int floorWidth = 10;
    public Rectangle liftRect = new Rectangle();
    public List<FloorRect> floorsRect = new ArrayList<>();

    public LiftRect(int x, int y, int width, int height, int floorCount) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height + 5;
        liftRect = new Rectangle(x, y, width, height);
        System.out.println("Lift - " + this.x +" - "+ this.y+" "+ this.width+" "+ this.height);
        for (int i = 0; i < floorCount; i++) {
            floorsRect.add(new FloorRect(x, y - floorHeight * i, floorWidth, floorHeight));
        }
    }
}
