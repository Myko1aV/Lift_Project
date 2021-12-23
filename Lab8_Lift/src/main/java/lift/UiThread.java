package lift;

import java.awt.*;

public class UiThread extends Thread{
    public GUI gui;
    public Graphics grp;

    public UiThread(GUI gui, Graphics grp){
        this.grp = grp;
        this.gui = gui;
    }

    @Override
    public void run(){

    }
}
