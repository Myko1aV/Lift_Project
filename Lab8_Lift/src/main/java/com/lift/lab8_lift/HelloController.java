package com.lift.lab8_lift;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import lift.Building;
import lift.Entrance;
import lift.GUI;
import lift.PassangerManager;
import lift.strategy.Strategy1;
import lift.strategy.Strategy2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class HelloController{
    public Button createButton;
    public Slider floorsSlider;
    public Slider entranceSlider;
    public Slider speedSlider;
    public Text text;

    public Slider speed;
    public Slider floors;
    public Slider entrance;
    public Slider speedLift;
    public RadioButton radioButton;
    public RadioButton radioButton2;


    @FXML
    public GridPane grid;

    @FXML
    protected void onCreateButtonClick() {


        Building building = new Building(0, (int) floors.getValue(), (int) entranceSlider.getValue(), (int) speedLift.getValue());

        GUI gui = new GUI(building);
        Frame f = new Frame();

        f.add(gui);
        f.setSize(1800, 850);
        f.setBackground(Color.black);
        f.setVisible(true);

        for (Entrance ent : building.entrances) {

            if (radioButton.isSelected()) {
                ent.setStrategy(new Strategy1());
            } else {
                ent.setStrategy(new Strategy2());
            }
//
            ent.start();
//
//
            //strategy 1
            //ent.CreatePasseger(3, 5);
            //ent.CreatePasseger(3, 4);
            //ent.CreatePasseger(3, 5);
            //ent.CreatePasseger(3, 8);


            //strategy 2
            //ent.CreatePasseger(4, 5);
            //ent.CreatePasseger(3, 8);

            new PassangerManager(ent, (long) speedSlider.getValue()).start();
            System.out.println(ent.lift.currentFloor.id);
        }
    }

    @FXML
    protected void onHelloButtonClick() {

    }
}