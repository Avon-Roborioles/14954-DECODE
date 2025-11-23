package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.CRServo;

import java.util.concurrent.TimeUnit;

public class IntakeSubsystem extends SubsystemBase {
    public enum SubSystemServoId {
        frontIntake,
        frontPass,
        backPass,
        backIntake
    }

    private CRServo frontIntake;
    private CRServo.Direction frontIntakeDirection;
    private CRServo frontPass;
    private CRServo.Direction frontPassDirection;
    private CRServo backIntake;
    private CRServo.Direction backIntakeDirection;
    private CRServo backPass;
    private CRServo.Direction backPassDirection;
    private double power;


    public IntakeSubsystem(CRServo frontIntake, CRServo frontPass, CRServo backIntake, CRServo backPass) {
        this.frontIntake = frontIntake;
        this.frontPass = frontPass;
        this.backIntake = backIntake;
        this.backPass = backPass;

        frontIntake.setPower(0);
        frontPass.setPower(0);
        backIntake.setPower(0);
        backPass.setPower(0);

        frontIntake.setDirection(CRServo.Direction.FORWARD);
        frontPass.setDirection(CRServo.Direction.FORWARD);
        backIntake.setDirection(CRServo.Direction.FORWARD);
        backPass.setDirection(CRServo.Direction.FORWARD);

        power = 1;
    }

    public void CommandServo(SubSystemServoId servoId, boolean runServo, CRServo.Direction desiredDirection) {
        CRServo servo = null;
        double currentServoPower = 0.0;
        switch (servoId) {
            case frontIntake:
                servo = frontIntake;
                break;
            case frontPass:
                servo = frontPass;
                break;
            case backIntake:
                servo = backIntake;
                break;
            case backPass:
                servo = backPass;
                break;
        }
        currentServoPower = servo.getPower();

        // stop servo before changing direction
        if (desiredDirection != servo.getDirection()) {
            if (servo.getPower() != 0) {
                servo.setPower(0);
            }
            servo.setDirection(desiredDirection);
        }
        if (runServo && currentServoPower == 0) {
            servo.setPower(1);
        } else {
            servo.setPower(0);
        }
    }

    public void IntakeFrontToBack() {
        frontIntake.setPower(-1);
        frontPass.setPower(-1);
        backIntake.setPower(0);
        backPass.setPower(-1);
    }

    public void IntakeFrontToBack(long ms) {
        Timing.Timer timer = new Timing.Timer(ms, TimeUnit.MILLISECONDS);
        timer.start();
        while (timer.isTimerOn()) {
            frontIntake.setPower(-1);
            frontPass.setPower(-1);
            backIntake.setPower(1);
            backPass.setPower(1);
        }
    }

    public void IntakeFrontToCenter() {
        frontIntake.setPower(-1);
        frontPass.setPower(-1);
        backIntake.setPower(0);
        backPass.setPower(0);
    }

    public void IntakeFrontToCenter(long ms) {
        Timing.Timer timer = new Timing.Timer(ms, TimeUnit.MILLISECONDS);
        timer.start();
        while (timer.isTimerOn()) {
            frontIntake.setPower(1);
            frontPass.setPower(-1);
            backIntake.setPower(0);
            backPass.setPower(0);
        }
    }

    public void IntakeFrontOnly() {
        frontIntake.setPower(-1);
        frontPass.setPower(0);
        backIntake.setPower(0);
        backPass.setPower(0);
    }


    public void IntakeBackToFront() {
        frontIntake.setPower(0);
        frontPass.setPower(1);
        backIntake.setPower(1);
        backPass.setPower(1);
    }

    public void IntakeBackToFront(long ms) {
        Timing.Timer timer = new Timing.Timer(ms, TimeUnit.MILLISECONDS);
        while (timer.isTimerOn()) {
            frontIntake.setPower(0);
            frontPass.setPower(1);
            backIntake.setPower(1);
            backPass.setPower(-1);
        }
    }

    public void IntakeBackToCenter() {
        frontIntake.setPower(0);
        frontPass.setPower(0);
        backIntake.setPower(1);
        backPass.setPower(1);
    }

    public void IntakeBackToCenter(long ms) {
        Timing.Timer timer = new Timing.Timer(ms, TimeUnit.MILLISECONDS);
        while (timer.isTimerOn()) {
            frontIntake.setPower(0);
            frontPass.setPower(0);
            backIntake.setPower(1);
            backPass.setPower(-1);
        }
    }

    public void IntakeBackOnly() {
        frontIntake.setPower(0);
        frontPass.setPower(0);
        backIntake.setPower(1);
        backPass.setPower(0);
    }

    public void TransferToLauncher() {
        frontIntake.setPower(0);
        frontPass.setPower(-1);
        backIntake.setPower(0);
        backPass.setPower(1);
    }

    public void TransferToLauncher(long ms) {
        Timing.Timer timer = new Timing.Timer(ms, TimeUnit.MILLISECONDS);
        while (timer.isTimerOn()) {
            frontIntake.setPower(0);
            frontPass.setPower(-1);
            backIntake.setPower(0);
            backPass.setPower(1);
        }
    }

    public void stopAll() {
        frontIntake.setPower(0);
        frontPass.setPower(0);
        backIntake.setPower(0);
        backPass.setPower(0);
    }
    public void Puke(){
        frontIntake.setPower(1);
        frontPass.setPower(1);
        backIntake.setPower(-1);
        backPass.setPower(-1);
    }
    public void intakeOnly(){
        frontIntake.setPower(-1);
        backIntake.setPower(1);
    }


}
