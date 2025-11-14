package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class REV2mDistanceSubsystem extends SubsystemBase {
    private DistanceSensor fSensor, mSensor, bSensor;
    private double distanceToArtifact = 3.5; //distance in cm

    public REV2mDistanceSubsystem(DistanceSensor fSensor, DistanceSensor mSensor, DistanceSensor bSensor){
        this.fSensor = fSensor; //front distance sensor
        this.mSensor = mSensor; //middle distance sensor
        this.bSensor = bSensor; //back distance sensor
    }

    public boolean checkFront(){
        if (fSensor.getDistance(DistanceUnit.CM) <= distanceToArtifact){
            return true; //yes, there is an artifact
        } else {
            return false; //no, there is not an artifact
        }
    }
    public boolean checkMiddle(){
        if (mSensor.getDistance(DistanceUnit.CM) <= distanceToArtifact){
            return true; //yes, there is an artifact
        } else {
            return false; //no, there is not an artifact
        }
    }
    public boolean checkBack(){
        if (bSensor.getDistance(DistanceUnit.CM) <= distanceToArtifact){
            return true; //yes, there is an artifact
        } else {
            return false; //no, there is not an artifact
        }
    }
    public String[] reportDistanceAsString(){
        String[] currentDistances
                = {"front (cm): " + fSensor.getDistance(DistanceUnit.CM),
                "back (cm): " + mSensor.getDistance(DistanceUnit.CM),
                "middle (cm): " + bSensor.getDistance(DistanceUnit.CM)};
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
