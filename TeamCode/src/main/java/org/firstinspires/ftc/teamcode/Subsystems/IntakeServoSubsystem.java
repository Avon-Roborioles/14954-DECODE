package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

public class IntakeServoSubsystem extends SubsystemBase {
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


    public IntakeServoSubsystem(CRServo frontIntake, CRServo frontPass, CRServo backIntake, CRServo backPass) {
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

        public void IntakeFrontToBack(){
            CommandServo(SubSystemServoId.frontIntake, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.frontPass, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backIntake, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backPass, true, CRServo.Direction.REVERSE);
        }
        public void IntakeFrontToCenter(){
            CommandServo(SubSystemServoId.frontIntake, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.frontPass, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backIntake, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backPass, false, CRServo.Direction.REVERSE);
        }
        public void IntakeFrontOnly(){
            CommandServo(SubSystemServoId.frontIntake, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.frontPass, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backIntake, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backPass, false, CRServo.Direction.REVERSE);
        }
        public void IntakeBackToFront(){
            CommandServo(SubSystemServoId.frontIntake, false, CRServo.Direction.REVERSE);
            CommandServo(SubSystemServoId.frontPass, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backIntake, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backPass, true, CRServo.Direction.FORWARD);
        }
        public void IntakeBackToCenter(){
            CommandServo(SubSystemServoId.frontIntake, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.frontPass, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backIntake, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backPass, true, CRServo.Direction.FORWARD);
        }
        public void IntakeBackOnly(){
            CommandServo(SubSystemServoId.frontIntake, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.frontPass, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backIntake, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backPass, false, CRServo.Direction.REVERSE);
        }
        public void TransferToLauncher(){
            CommandServo(SubSystemServoId.frontIntake, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.frontPass, true, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backIntake, false, CRServo.Direction.FORWARD);
            CommandServo(SubSystemServoId.backPass, true, CRServo.Direction.FORWARD);
        }
        public void stopAll(){
            frontIntake.setPower(0);
            frontPass.setPower(0);
            backIntake.setPower(0);
            backPass.setPower(0);
        }

}
