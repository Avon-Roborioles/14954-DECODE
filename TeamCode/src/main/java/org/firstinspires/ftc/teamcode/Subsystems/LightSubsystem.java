package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class LightSubsystem extends SubsystemBase {
    private Servo light;


    public LightSubsystem(Servo light){
        this.light = light;
    }

    public void lightOff(){
        light.setPosition(0);
    }
    public void lightRed(){
        light.setPosition(0.277);
    }
    public void lightOrange(){
        light.setPosition(0.333);
    }
    public void lightYellow(){
        light.setPosition(0.388);
    }
    public void lightSage(){
        light.setPosition(0.444);
    }
    public void lightGreen(){
        light.setPosition(0.5);
    }
    public void lightAzure(){
        light.setPosition(0.555);
    }
    public void lightBlue(){
        light.setPosition(0.611);
    }
    public void lightIndigo(){
        light.setPosition(0.666);
    }
    public void lightViolet(){
        light.setPosition(0.722);
    }
    public void lightWhite(){
        light.setPosition(1);
    }

}
