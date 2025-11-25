package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import static java.lang.Math.E;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LaunchSubsystem extends SubsystemBase {
    private DcMotorEx launchMotor;
    private Servo launchAngle;
    private Servo turnServo;
    private CRServo launchServo;
    private double launchPower;
    private double Anglepos = 0.05;
    private double angleAngle;

    public LaunchSubsystem(DcMotorEx launchMotor, Servo launchAngle, Servo turnServo, CRServo launchServo){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        this.turnServo = turnServo;
        this.launchServo = launchServo;
        launchPower = 1;
    }
    public LaunchSubsystem(DcMotorEx launchMotor, Servo launchAngle){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        launchPower = 1;
    }

    public void adjustLaunchPower(double value){
        launchPower = launchPower + value;

    }
    public void setLaunchPower(double power){
        launchPower = power;
    }





    public double getAnglepos(){
        return launchAngle.getPosition();
    }
    public void setLaunchAngle(double pos){
        launchAngle.setPosition(pos);
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
        telemetry.addData("launchPower", launchMotor.getVelocity());
        telemetry.addData("launchEncoder", launchMotor.getCurrentPosition());
        telemetry.addData("launchAngle", launchAngle.getPosition());
        telemetry.addData("angle", angleAngle);
    }
    public double distanceToSpeed(double distance){
        return  ((distance * 1.6109) + 1395.5);
    }

    public double distanceToHoodAngle(double distance){
        angleAngle = (-0.0034 * distance * distance + 0.0005 * distance - 0.501);
        return angleAngle;
    }

}
