package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

    public void adjustLaunchPower(double value){
        launchPower = launchPower + value;

        if(launchPower > 1){
            launchPower = 1;
        }else if(launchPower < 0){
            launchPower = 0;
        }
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
    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("launchPower", launchMotor.getPower());
        telemetry.addData("launchEncoder", launchMotor.getCurrentPosition());
        telemetry.addData("launchAngle", launchAngle.getPosition());
    }

}
