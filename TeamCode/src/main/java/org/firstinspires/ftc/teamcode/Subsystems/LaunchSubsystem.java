package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class LaunchSubsystem extends SubsystemBase {
    private DcMotor launchMotor;
    private Servo launchAngle;
    private Servo turnServo;
    private CRServo launchServo;
    private double launchPower;
    private double Anglepos = 0.05;

    public LaunchSubsystem(DcMotor launchMotor, Servo launchAngle, Servo turnServo, CRServo launchServo){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        this.turnServo = turnServo;
        this.launchServo = launchServo;
        launchPower = 1;
    }
    public LaunchSubsystem(DcMotor launchMotor, Servo launchAngle){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        launchPower = 1;
    }
    public void setLaunchPower(double newPower){
        launchPower = newPower;
    }
    public void setLaunchAngle(double pos){
        launchAngle.setPosition(pos);
    }



    public double getAnglepos(){
        return launchAngle.getPosition();
    }
    public void runMotor(){
        launchMotor.setPower(launchPower);
        launchServo.setPower(1);
    }
    public void stopMotor(){
        launchMotor.setPower(0);
        launchServo.setPower(0);
    }
    public void setTurnServo(double pos){
        turnServo.setPosition(pos);
    }
    public double getPower(){
        return launchPower;
    }

}
