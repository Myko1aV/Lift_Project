package lift;

import java.awt.*;

public class GUI extends Panel{
    private Building building;
    private static final int padding = 25;
    private static final int flr_padding = 25;
    private static final int fontSize = Config.getFontSize();
    private static Font font = new Font("comic sans", 1, fontSize);
    private static Color color = Color.white;
    public static Color randomColor(){
        int R = (int) Math.round(Math.random() * 255);
        int G = (int) Math.round(Math.random() * 255);
        int B = (int) Math.round(Math.random() * 255);
        return new Color(R, G, B);
    }
    public void paint(Graphics grp)
    {
        grp.clearRect(0, 0, this.getWidth(), this.getHeight());
        grp.setFont(font);
        grp.setColor(color);
        //System.out.println("----\nDRAWING:\nENT:" + building.entrances.size());
        int ent_width = (this.getWidth() - padding*2)/building.entrances.size();
        int ent_start = padding;
        for (Entrance ent: building.entrances.subList(0, building.entrances.size())) {
            //System.out.println("Floors: " + ent.floorList.size());
            //System.out.println("Lift floor: " + ent.lift.currentFloor.id);
            //System.out.println("Pass: " + ent.floorList.stream().map(e -> e.waiters.size()).reduce(0, Integer::sum));
            int flr_height = (this.getHeight() - padding*2)/ent.floorList.size();
            int flr_start = padding;
            for (Floor flr: ent.floorList.stream().sorted((a, b) -> a.id < b.id ? 1 : -1).toList()) {
                int r = (int)(flr.id*1.0/ent.floorList.size()*255);
                int g = (int)(ent.id*1.0/building.entrances.size()*255);
                int b = 100;
                Color clr = new Color(r, g, b);
                if(ent.lift.currentFloor.id == flr.id){
                    grp.setColor(clr);
                    grp.fillRect(ent_start+ent_width/3, flr_start, ent_width/3, flr_height);
                    grp.setColor(color);
                    grp.drawString( "" + ent.lift.passangerList.size(), (int)(ent_start + ent_width/2) - fontSize / 2, fontSize / 2 + flr_start + flr_height / 2);
                    grp.setColor(color);
                }
                grp.setColor(clr);
                grp.drawRect(ent_start, flr_start, ent_width/3, flr_height);
                grp.drawRect(ent_start + ent_width/3, flr_start, ent_width/3, flr_height);
                grp.drawRect(ent_start + ent_width/3 + ent_width/3, flr_start, ent_width/3, flr_height);

                grp.drawString( "" + flr.waiters.size(), (int)(ent_start + ent_width /6)  - fontSize / 2, fontSize / 2 + flr_start + flr_height / 2);

                grp.drawString( "" + flr.leavers.size(), (int)(ent_start + ent_width - ent_width / 6) - fontSize / 2, fontSize / 2 + flr_start + flr_height / 2);


                flr_start += flr_height;
            }
            ent_start += ent_width;
        }
        try {
            Thread.sleep(1000);
            paint(grp);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public GUI (Building building) {
        this.building = building;
    }
}