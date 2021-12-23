package lift.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingRect extends JPanel {
    public int x;
    public int y;
    public int width;
    public int height;
    public Rectangle buildingRect;
    private List<LiftRect> liftsRect = new ArrayList<>();
    public int floorWidth = 10;
    public int floorHeight = 10;
    private static final int PREF_W = 1;
    private static final int PREF_H = PREF_W;

    public BuildingRect(int x, int y, int liftCount, int floorCount) {
        this.x = x;
        this.y = y;
        this.width = floorWidth * liftCount;
        this.height = floorHeight * floorCount;
        System.out.println("Building - " +this.x +" "+ this.y+" "+ this.width+" "+ this.height);
        buildingRect = new Rectangle(this.x, this.y, this.width, this.height);
        for (int i = 0; i < liftCount; i++) {
            liftsRect.add(new LiftRect(x + floorWidth * i, y, floorWidth, floorHeight * floorCount, floorCount));
        }
    }

    public void Display() {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.draw(buildingRect);

        for (LiftRect rect : liftsRect) {
            g2.draw(rect.liftRect);
        }

        g2.setColor(Color.green);
        for (LiftRect rect : liftsRect) {
            for (FloorRect rect1 : rect.floorsRect) {
                g2.draw(rect1.floorRect);
            }
        }
    }

}
