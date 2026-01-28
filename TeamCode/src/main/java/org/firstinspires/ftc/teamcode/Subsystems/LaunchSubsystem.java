package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LaunchSubsystem extends SubsystemBase {
    private DcMotorEx launchMotor;
    private DcMotorEx launchMotor2;
    private Servo launchAngleServo;
    private Servo turnServo;
    private CRServo launchServo;
    private double motorBoostSpeed = 0;
    private final double ANGLE_SERVO_ZERO = 0.6;

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
//        PIDController pid = new PIDController(0,0,0);
    }
    public LaunchSubsystem(DcMotorEx launchMotor, DcMotorEx launchMotor2, Servo launchAngleServo, Servo turnServo, CRServo launchServo) {
        this.launchMotor = launchMotor;
        this.launchMotor2 = launchMotor2;
        this.launchAngleServo = launchAngleServo;
        this.turnServo = turnServo;
        this.launchServo = launchServo;
        this.launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.launchMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(10,0,0,12.2);
        this.launchMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        this.launchMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        //13.29, 15

    }


    // --- MATH FROM NewLauncher ---

    public double getTargetRPM(){
        return TargetRPM;
    }
    public void setTargetRPM(double distance){
        TargetRPM = ((distance * 6) + 775);

        if (TargetRPM < 1200) {
            TargetRPM = 1200;
        }
    }

    public double distanceToHoodAngle(double distance){
        return (-0.00000004 * distance * distance + 0.0005 * distance +0.0494);
    }
    public void correctSpeed(){
        double currentSpeed = (launchMotor.getVelocity() + -launchMotor2.getVelocity()) /2;
        motorBoostSpeed = TargetRPM - currentSpeed;
    }

    public void closeBackSetPoint(){
        double Position;
        TargetRPM = 1800;
        double correctedSpeed = TargetRPM + motorBoostSpeed;


        launchMotor.setVelocity(correctedSpeed);
        launchMotor2.setVelocity(-correctedSpeed);
        Position = ANGLE_SERVO_ZERO - 0.08;
        launchAngleServo.setPosition(Position);


    }

   public void backMiddleSetPoint(){
       double Position;
       TargetRPM = 1850;
       double correctedSpeed = TargetRPM + motorBoostSpeed;


       launchMotor.setVelocity(correctedSpeed);
       launchMotor2.setVelocity(-correctedSpeed);
       Position = ANGLE_SERVO_ZERO - 0.08;
       launchAngleServo.setPosition(Position);


   }
   public void updateSpeed(){

   }
    public void backSetPoint(boolean auto){
        double Position;

        if (auto){
            TargetRPM = 1825;
            launchMotor.setVelocity(1850);
            launchMotor2.setVelocity(-1850);
            Position = ANGLE_SERVO_ZERO - 0.1;
            launchAngleServo.setPosition(Position);
        } else {
            TargetRPM = 1850;
            double correctedSpeed = TargetRPM + motorBoostSpeed;


            launchMotor.setVelocity(correctedSpeed);
            launchMotor2.setVelocity(-correctedSpeed);
            Position = ANGLE_SERVO_ZERO - 0.08;
            launchAngleServo.setPosition(Position);
        }

        //0.0 //0.13 // correct
    }
    public void midSetPoint() {
        double Position;
        TargetRPM = 1500;
        double correctedSpeed = TargetRPM + motorBoostSpeed;


        launchMotor.setVelocity(correctedSpeed);
        launchMotor2.setVelocity(-correctedSpeed);
        Position = ANGLE_SERVO_ZERO - 0.04;
        launchAngleServo.setPosition(Position); //0.03 //0.10 //correct 1500: 0.03
    }
    public void frontSetPoint(){
        double Position;
        TargetRPM = 1850;
        double correctedSpeed = TargetRPM + motorBoostSpeed;


        launchMotor.setVelocity(correctedSpeed);
        launchMotor2.setVelocity(-correctedSpeed);
        Position = ANGLE_SERVO_ZERO - 0.02;
        launchAngleServo.setPosition(Position); //0.06 //0.07 // correct 1300: 0.06 // 1.2
    }



    private double angleToServo(double angle){
        if(angle > 90) angle = 90;
        if(angle < 0) angle = 0;
        return ((90-angle)*0.300/90);
    }



    // --- CONTROL METHODS ---

    public void setLaunchAngleServo(double angleDegrees){
        Angle = angleDegrees;
        //90=0.0
        //0=0.290
        launchAngleServo.setPosition(angleToServo(90));

    }

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
        launchMotor2.setPower(0);
        launchAngleServo.setPosition(ANGLE_SERVO_ZERO);
    }

    public void setTurnServo(double pos){
        turnServo.setPosition(pos);
    }

    // UPDATED: Only checks the boolean flag to prevent auto-restart during spin down
    public boolean isMotorRunning(){
        return isRunning;
    }



    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("V1", launchMotor.getVelocity());
        telemetry.addData("V2 ", launchMotor2.getVelocity());
        telemetry.addData("Vavg ",(launchMotor.getVelocity() + -launchMotor2.getVelocity())/2);
        telemetry.addData("Target RPM", TargetRPM);
        telemetry.addData("Target Angle (Deg)", Angle);
        telemetry.addData("Servo Pos", launchAngleServo.getPosition());

    }
    public void compTelemetry(Telemetry telemetry) {

        telemetry.addData("V1", launchMotor.getVelocity());
        telemetry.addData("V2 ", launchMotor2.getVelocity());
        telemetry.addData("Vavg ",(launchMotor.getVelocity() + -launchMotor2.getVelocity())/2);
        telemetry.addData("correctedSpeed", motorBoostSpeed);
        telemetry.addData("Target RPM", TargetRPM);
        telemetry.addData("Target Angle (Deg)", Angle);
        telemetry.addData("Servo Pos", launchAngleServo.getPosition());

    }

}
