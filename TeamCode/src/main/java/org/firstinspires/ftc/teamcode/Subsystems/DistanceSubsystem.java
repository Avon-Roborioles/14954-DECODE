package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class DistanceSubsystem extends SubsystemBase {
    private DigitalChannel fSensor, mSensor, bSensor;
    private double distanceToArtifact = 3.5; //distance in cm

    public DistanceSubsystem(DigitalChannel fSensor, DigitalChannel mSensor, DigitalChannel bSensor){
        this.fSensor = fSensor; //front distance sensor
        this.mSensor = mSensor; //middle distance sensor
        this.bSensor = bSensor; //back distance sensor
    }

    public boolean checkFront(){
        if (fSensor.getState()){
            return true; //yes, there is an artifact
        } else {
            return false; //no, there is not an artifact
        }
    }
    public boolean checkMiddle(){
        if (mSensor.getState()){
            return true; //yes, there is an artifact
        } else {
            return false; //no, there is not an artifact
        }
    }
    public boolean checkBack(){
        if (bSensor.getState()){
            return true; //yes, there is an artifact
        } else {
            return false; //no, there is not an artifact
        }
    }
    public String[] reportDistanceAsString(){
        String[] currentDistances
                = {"front (cm): " + fSensor.getState(),
                "back (cm): " + mSensor.getState(),
                "middle (cm): " + bSensor.getState()};
        return currentDistances;
    }
    public int getArtifactNum(){
        int num = 0;
        if(checkFront()){
            num++;
        }
        if(checkBack()){
            num++;
        }
        if(checkMiddle()){
            num++;
        }
        return num;
    }
    public int[] getArtifactPos(){
        int[] pos = new int[3];
        if(checkFront()){
            pos[0] = 1;
        }
        if(checkMiddle()){
            pos[1] = 1;
        }
        if(checkBack()){
            pos[2] = 1;
        }
        return pos;
    }
}
