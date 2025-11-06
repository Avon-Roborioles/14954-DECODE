package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class LaunchSubsystem extends SubsystemBase {
    private DcMotor launchMotor;
    private Servo launchAngle;
    private Servo turnServo;
    private double launchPower;

    public LaunchSubsystem(DcMotor launchMotor, Servo launchAngle, Servo turnServo){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        this.turnServo = turnServo;
        launchPower = 0.8;
    }
    public LaunchSubsystem(DcMotor launchMotor, Servo launchAngle){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        launchPower = 0.8;
    }
    public void setLaunchPower(double newPower){
        launchPower = newPower;
    }
    public void setLaunchAngle(double pos){
        launchAngle.setPosition(pos);
    }
    public void runMotor(){
        launchMotor.setPower(launchPower);
    }
    public void stopMotor(){
        launchMotor.setPower(0);
    }
    public void setTurnServo(double pos){
        turnServo.setPosition(pos);
    }
    public double getPower(){
        return launchPower;
    }

}
