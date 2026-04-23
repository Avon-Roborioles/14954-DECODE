package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.Timing;
import com.bylazar.configurables.PanelsConfigurables;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.panels.Panels;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;
import java.util.function.DoubleSupplier;

@Configurable
public class IntakeSubsystem extends SubsystemBase {
    public enum SubSystemServoId {
        frontIntake,
        frontPass,
        backPass,
        backIntake
    }

    private CRServo frontIntake;
    private CRServo.Direction frontIntakeDirection;
    //    private CRServo frontPass;
    private DcMotorEx frontPass;
    private CRServo.Direction frontPassDirection;
    private CRServo backIntake;
    private CRServo.Direction backIntakeDirection;
    //    private CRServo backPass;
    private DcMotorEx backPass;
    private CRServo.Direction backPassDirection;
    private double power;

    private static double PFront = 0;
    private static double FFront = 0;
    private static double PBack = 0;
    private static double FBack = 0;


    //    public IntakeSubsystem(CRServo frontIntake, CRServo frontPass, CRServo backIntake, CRServo backPass) {
//        this.frontIntake = frontIntake;
//        this.frontPass = frontPass;
//        this.backIntake = backIntake;
//        this.backPass = backPass;
//
//        frontIntake.setPower(0);
//        frontPass.setPower(0);
//        backIntake.setPower(0);
//        backPass.setPower(0);
//
//        frontIntake.setDirection(CRServo.Direction.FORWARD);
//        frontPass.setDirection(CRServo.Direction.FORWARD);
//        backIntake.setDirection(CRServo.Direction.FORWARD);
//        backPass.setDirection(CRServo.Direction.FORWARD);
//
//        power = 1;
//    }
    public IntakeSubsystem(CRServo frontIntake, DcMotorEx frontPass, CRServo backIntake, DcMotorEx backPass) {
        this.frontIntake = frontIntake;
        this.frontPass = frontPass;
        this.backIntake = backIntake;
        this.backPass = backPass;

//        frontIntake.setPower(0);
//        frontPass.setPower(0);
//        backIntake.setPower(0);
//        backPass.setPower(0);

        this.frontIntake.setDirection(CRServo.Direction.FORWARD);
        this.frontPass.setDirection(DcMotor.Direction.FORWARD);
        this.backIntake.setDirection(CRServo.Direction.FORWARD);
        this.backPass.setDirection(DcMotor.Direction.FORWARD);
//        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(PFront,0,0,FFront);
//        PIDFCoefficients pidfCoefficients2 = new PIDFCoefficients(PBack,0,0,FBack);
//        this.frontPass.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
//        this.backPass.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients2);
        
    }

//    public void CommandServo(SubSystemServoId servoId, boolean runServo, CRServo.Direction desiredDirection) {
//        CRServo servo = null;
//        double currentServoPower = 0.0;
//        switch (servoId) {
//            case frontIntake:
//                servo = frontIntake;
//                break;
//            case frontPass:
//                servo = frontPass;
//                break;
//            case backIntake:
//                servo = backIntake;
//                break;
//            case backPass:
//                servo = backPass;
//                break;
//        }
//        currentServoPower = servo.getPower();

//        // stop servo before changing direction
//        if (desiredDirection != servo.getDirection()) {
//            if (servo.getPower() != 0) {
//                servo.setPower(0);
//            }
//            servo.setDirection(desiredDirection);
//        }
//        if (runServo && currentServoPower == 0) {
//            servo.setPower(1);
//        } else {
//            servo.setPower(0);
//        }
//    }

    public void IntakeFrontToBack() {
        frontIntake.setPower(-1);
        frontPass.setPower(-.5);
        backIntake.setPower(0);
        backPass.setPower(-.5);
    }

    public void IntakeBackToFront() {
        frontIntake.setPower(0);
        frontPass.setPower(.5);
        backIntake.setPower(1);
        backPass.setPower(.5);
    }


    public void IntakeFrontToCenter() {
        frontIntake.setPower(-1);
        frontPass.setPower(-.5);
        backIntake.setPower(0);
        backPass.setPower(0);
    }


    public void IntakeFrontOnly() {
        frontIntake.setPower(-1);
        frontPass.setPower(0);
        backIntake.setPower(0);
        backPass.setPower(0);
    }

    public void IntakeBackToCenter() {
        frontIntake.setPower(0);
        frontPass.setPower(0);
        backIntake.setPower(1);
        backPass.setPower(.5);
    }


    public void IntakeBackOnly() {
        frontIntake.setPower(0);
        frontPass.setPower(0);
        backIntake.setPower(1);
        backPass.setPower(0);
    }

    public void TransferToLauncher() {
       frontIntake.setPower(0);
        frontPass.setPower(-.5);
       backIntake.setPower(0);
       backPass.setPower(.5);
    }


    public void stopAll() {
        frontIntake.setPower(0);
        frontPass.setPower(0);
        backIntake.setPower(0);
        backPass.setPower(0);
    }
    public void Puke(){
        frontIntake.setPower(1);
        frontPass.setPower(.5);
        backIntake.setPower(-1);
        backPass.setPower(-.5);
    }
    public void intakeOnly(){
        frontIntake.setPower(-1);
        backIntake.setPower(1);
    }
    public void stopPass(){
        frontPass.setPower(0);
        backPass.setPower(0);
    }

    public void manLaunch(){
        frontIntake.setPower(-1);
        frontPass.setPower(-.5);
        backIntake.setPower(.7);
        backPass.setPower(.5);
    }
    public void backSideLaunch(){
        frontPass.setPower(-.5);
        backIntake.setPower(1);
        backPass.setPower(.5);
    }
     public void frontSideLaunch(){
        frontPass.setPower(-1);
        backPass.setPower(.5);
        frontIntake.setPower(-1);
     }
    public void proportionalLaunch(float input){
        frontIntake.setPower(-input);
        frontPass.setPower(-input);
        backIntake.setPower(input);
        backPass.setPower(input);
    }

//    public void periodic(){
//        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(PFront,0,0,FFront);
//        PIDFCoefficients pidfCoefficients2 = new PIDFCoefficients(PBack,0,0,FBack);
//        this.frontPass.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
//        this.backPass.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients2);
//    }

    public void getTelemetry(Telemetry telemetry) {
        telemetry.addData("frontIntakePower", frontIntake.getPower());
        telemetry.addData("frontPassPower", frontPass.getPower());
        telemetry.addData("frontPassVelocity", frontPass.getVelocity());
        telemetry.addData("backIntakePower", backIntake.getPower());
        telemetry.addData("backPassPower", backPass.getPower());
    }
    public void compTelemtry(Telemetry telemetry){
        telemetry.addLine("Intake Data");
        telemetry.addData("frontIntakePower", frontIntake.getPower());
        telemetry.addData("frontPassPower", frontPass.getPower());
        telemetry.addData("frontPassVelocity", frontPass.getVelocity());
        telemetry.addData("backIntakePower", backIntake.getPower());
        telemetry.addData("backPassPower", backPass.getPower());
        telemetry.addData("backPassVelocity", backPass.getVelocity());
    }
}
