package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LaunchSubsystem extends SubsystemBase {
    private DcMotorEx launchMotor;
    private Servo launchAngleServo;
    private Servo turnServo;
    private CRServo launchServo;

    // Default starting value
    private double TargetRPM = 1900;

    // Telemetry variables
    private double Angle;

    private double newManControl;
    private boolean isRunning = false; // Track if motor is toggled on

    public LaunchSubsystem(DcMotorEx launchMotor, Servo launchAngleServo, Servo turnServo, CRServo launchServo){
        this.launchMotor = launchMotor;
        this.launchAngleServo = launchAngleServo;
        this.turnServo = turnServo;
        this.launchServo = launchServo;

        // Essential for setVelocity to work correctly
        this.launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    // --- MATH FROM NewLauncher ---

//    public void setTargetRPM(double distance){
//        TargetRPM = ((distance * 6) + 775);
//
//        if (TargetRPM < 1200) {
//            TargetRPM = 1200;
//        }
//    }

//    public double distanceToHoodAngle(double distance){
//        return (-0.00000004 * distance * distance + 0.0005 * distance +0.0494);
//    }

    public void backSetPoint(){
        launchMotor.setVelocity(1850);
        launchAngleServo.setPosition(0.0); //0.0 //0.13 // correct
    }
    public void midSetPoint() {
        launchMotor.setVelocity(1500);
        launchAngleServo.setPosition(0.03); //0.03 //0.10 //correct 1500: 0.03
    }
    public void frontSetPoint(){
        launchMotor.setVelocity(1300);
        launchAngleServo.setPosition(0.06); //0.06 //0.07 // correct 1300: 0.06
    }



//    private double angleToServo(double angle){
//        if(angle > 90) angle = 90;
//        if(angle < 0) angle = 0;
//        return ((90-angle)*0.300/90);
//    }



    // --- CONTROL METHODS ---

//    public void setLaunchAngleServo(double angleDegrees){
//        Angle = angleDegrees;
//        //90=0.0
//        //0=0.290
//        launchAngleServo.setPosition(angleToServo(90));

//    }

    public void runMotor(){
        isRunning = true;
        launchMotor.setVelocity(TargetRPM);
        // If you need your intake/feeder servo to run, uncomment this:
        // if(launchServo != null) launchServo.setPower(1);
    }

    // UPDATED: Now correctly updates the boolean flag
    public void stopMotor(){
        isRunning = false;
        launchMotor.setPower(0);
        if(launchServo != null) launchServo.setPower(0);
    }

    public void setTurnServo(double pos){
        turnServo.setPosition(pos);
    }

    // UPDATED: Only checks the boolean flag to prevent auto-restart during spin down
    public boolean isMotorRunning(){
        return isRunning;
    }



    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Actual Velocity", launchMotor.getVelocity());
        telemetry.addData("Target RPM", TargetRPM);
        telemetry.addData("Target Angle (Deg)", Angle);
        telemetry.addData("Servo Pos", launchAngleServo.getPosition());

    }
    public void compTelemetry(Telemetry telemetry) {

        telemetry.addData("Actual Velocity", launchMotor.getVelocity());
        telemetry.addData("Target RPM", TargetRPM);
        telemetry.addData("Target Angle (Deg)", Angle);
        telemetry.addData("Servo Pos", launchAngleServo.getPosition());

    }

}
