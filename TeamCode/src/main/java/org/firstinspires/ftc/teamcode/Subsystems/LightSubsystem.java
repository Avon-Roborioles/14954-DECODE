package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Light;
import com.qualcomm.robotcore.hardware.Servo;

public class LightSubsystem extends SubsystemBase {
    private Servo light;
    private Servo light2;
    private boolean redAlliance;


    public LightSubsystem(Servo light){
        this.light = light;
    }
//    public LightSubsystem(Servo light, boolean redAlliance){
//        this.light = light;
//        this.redAlliance = redAlliance;
//
//        if(!redAlliance){
//            light.setPosition(0.611);
//        } else {
//            light.setPosition(0.28);
//        }
//    }

    public LightSubsystem(Servo light,Servo light2 ,boolean redAlliance){
        this.light = light;
        this.light2 = light2;
        this.redAlliance = redAlliance;

        if(!redAlliance){
            light.setPosition(0.611);
            light2.setPosition(0.611);
        } else {
            light.setPosition(0.28);
            light2.setPosition(0.28);
        }
    }

    public void lightOff(){
        light.setPosition(0);
        light2.setPosition(0);
    }
    public void lightRed(){
        light.setPosition(0.28);
        light2.setPosition(0.28);
    }
    public void lightOrange(){
        light.setPosition(0.333);
        light2.setPosition(0.333);
    }
    public void lightYellow(){
        light.setPosition(0.388);
        light2.setPosition(0.388);
    }
    public void lightSage(){
        light.setPosition(0.444);
        light2.setPosition(0.444);
    }
    public void lightGreen(){
        light.setPosition(0.5);
        light2.setPosition(0.5);
    }
    public void lightAzure(){
        light.setPosition(0.555);
        light2.setPosition(0.555);
    }
    public void lightBlue(){
        light.setPosition(0.611);
        light2.setPosition(0.611);
    }
    public void lightIndigo(){
        light.setPosition(0.666);
        light2.setPosition(0.666);
    }
    public void lightViolet(){
        light.setPosition(0.722);
        light2.setPosition(0.722);
    }
    public void lightWhite(){
        light.setPosition(1);
        light2.setPosition(1);
    }

}
