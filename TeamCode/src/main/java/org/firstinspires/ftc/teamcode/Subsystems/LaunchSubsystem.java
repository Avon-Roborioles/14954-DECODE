package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class LaunchSubsystem extends SubsystemBase {
    private DcMotor launchMotor;
    private CRServo launchServo;
    private Servo turnServo;
    private double launchPower;

    public LaunchSubsystem(DcMotor launchMotor, CRServo launchServo, Servo turnServo){
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
        launchServo.setPower(1);

    }
    public void stopMotor(){
        launchMotor.setPower(0);
        launchServo.setPower(0);
    }
    public void setAngle(double pos){
        launchServo.setPower(pos);
    }
    public void setTurnServo(double pos){
        turnServo.setPosition(pos);
    }
    public double getPower(){
        return launchPower;
    }

}
