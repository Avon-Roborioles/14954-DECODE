package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class LaunchSubsystem extends SubsystemBase {
    private DcMotor launchMotor;
    private Servo launchServo;
    private Servo turnServo;
    private double launchPower;

    public LaunchSubsystem(DcMotor launchMotor, Servo launchServo, Servo turnServo){
        this.launchMotor = launchMotor;
        this.launchServo = launchServo;
        this.turnServo = turnServo;
        launchPower = 0.8;
    }
    public void setLaunchPower(double newPower){
        newPower = launchPower;
    }
    public void runMotor(){
        launchMotor.setPower(launchPower);
    }
    public void stopMotor(){
        launchMotor.setPower(0);
    }
    public void setAngle(double pos){
        launchServo.setPosition(pos);
    }
    public void setTurnServo(double pos){
        turnServo.setPosition(pos);
    }
    public double getPower(){
        return launchPower;
    }

}
