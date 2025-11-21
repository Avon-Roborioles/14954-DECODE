package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class DistanceSubsystem extends SubsystemBase {
    private DigitalChannel fSensor, mSensor, bSensor;
    private double distanceToArtifact = 3.5; //distance in cm
    private Boolean intakeFromFront = null;

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
                "middle (cm): " + mSensor.getState(),
                "back (cm): " + bSensor.getState()};
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


    public boolean isFront(){
        return fSensor.getState();
    }
    public boolean isBack(){
        return fSensor.getState();
    }
    public Boolean isIntakingFromFront() {
        return intakeFromFront;
    }
    public void setIntakeFromFront(Boolean intakeFromFront){
        this.intakeFromFront = intakeFromFront;


    }
}
