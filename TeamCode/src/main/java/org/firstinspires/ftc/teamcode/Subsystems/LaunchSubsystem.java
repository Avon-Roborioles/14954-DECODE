package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
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

    // Default starting value
    private double TargetRPM = 1900;

    // Telemetry variables
    private double Angle;

    private double newManControl;
    private boolean isRunning = false; // Track if motor is toggled on

    public LaunchSubsystem(DcMotorEx launchMotor, Servo launchAngle, Servo turnServo, CRServo launchServo){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        this.turnServo = turnServo;
        this.launchServo = launchServo;

        // Essential for setVelocity to work correctly
        this.launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public LaunchSubsystem(DcMotorEx launchMotor, Servo launchAngle){
        this.launchMotor = launchMotor;
        this.launchAngle = launchAngle;
        this.launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // --- MATH FROM NewLauncher ---

    public void setTargetRPM(double distance){
        TargetRPM = ((distance * 6) + 775);
    }

    public double distanceToHoodAngle(double distance){
        return (-0.0001 * distance * distance + 0.1361 * distance - 0.1845);
    }



    private double angleToServo(double angle){
        if(angle > 50) angle = 50;
        if(angle < 0) angle = 0;
        return (angle / 55.0) * 0.187;
    }

    public void adjustAngleM(double controller){
        newManControl = newManControl + controller;
        launchAngle.setPosition(newManControl);
    }

    // --- CONTROL METHODS ---

    public void setLaunchAngle(double angleDegrees){
        Angle = angleDegrees;
        launchAngle.setPosition(angleToServo(Angle));

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
        telemetry.addData("command pos", angleToServo(Angle));
        telemetry.addData("Servo Pos", launchAngle.getPosition());

    }

}
