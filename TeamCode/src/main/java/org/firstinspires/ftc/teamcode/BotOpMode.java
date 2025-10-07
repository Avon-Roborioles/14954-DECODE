package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.IntakeMotorCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeServoCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;
@TeleOp


public class BotOpMode extends LinearOpMode {
    public DcMotor shooterMotor = null;
    private double shooterPower;
    public Servo shooterAngle = null;

    private IntakeMotorSubsystem motorSubsystem;
    private IntakeServoSubsystem servoSubsystem;
    private IntakeMotorCommand motorCommand;
    private IntakeServoCommand servoCommand;
    private IntakeStopServoCommand stopServoCommand;



    @Override
    public void runOpMode() {
        final double motorIncrement = 0.1;
        double newShooterPower = 0.0;
        shooterPower = 0.8;
        double launchServoAngle = 0.0;
        final double servoAngleChange = 0.002;
        double newServoAngle = 0.0;


        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);

        shooterAngle = hardwareMap.get(Servo.class, "shooterAngle");
        Motor frontLeft = new Motor(hardwareMap,"frontLeft");
        Motor frontRight = new Motor(hardwareMap,"frontRight");
        Motor backLeft = new Motor(hardwareMap, "backLeft");
        Motor backRight = new Motor(hardwareMap, "backRight");
        motorSubsystem = new IntakeMotorSubsystem(frontLeft,frontRight,backLeft,backRight);

        servoSubsystem = new IntakeServoSubsystem(hardwareMap.get(CRServo.class, "servo"), hardwareMap.get(CRServo.class, "servo2"));

        motorCommand = new IntakeMotorCommand(
                motorSubsystem,
                () -> gamepad1.left_stick_x,
                () -> gamepad1.left_stick_y,
                () -> gamepad1.right_stick_x
        );

        servoCommand = new IntakeServoCommand(servoSubsystem);
        stopServoCommand = new IntakeStopServoCommand(servoSubsystem);

        CommandScheduler.getInstance().schedule(motorCommand);

        waitForStart();

        shooterAngle.setDirection(Servo.Direction.REVERSE);
        shooterAngle.setPosition(0.0);
        launchServoAngle = shooterAngle.getPosition();


        while (opModeIsActive()) {

            CommandScheduler.getInstance().run();
            

            newShooterPower = shooterPower;

            if (gamepad1.dpadUpWasPressed()) {
                newShooterPower += motorIncrement;
            } else if (gamepad1.dpadDownWasPressed()) {
                newShooterPower -= motorIncrement;
            }

            if ((newShooterPower > 0.0) && (newShooterPower < 1.0)) {
                shooterPower = newShooterPower;
                shooterMotor.setPower(shooterPower);
            }

            if (gamepad1.dpadRightWasPressed()) {
                newServoAngle += servoAngleChange;
            } else if (gamepad1.dpadLeftWasPressed()) {
                newServoAngle -= servoAngleChange;
            }

            if (gamepad1.a) {
                shooterPower += .05;
                shooterMotor.setPower(shooterPower);
            }

//            if (gamepad1.a) {
//                shooterPower = 1.0;
//                shooterMotor.setPower(shooterPower);
//            } else if(){
//                shooterPower = 0.8;
//                shooterMotor.setPower(shooterPower);
//            }


            if ((newServoAngle > 0.0) && (newServoAngle < 1.0)) {
                launchServoAngle = newServoAngle;
                shooterAngle.setPosition(launchServoAngle);
            }


            telemetry.addData("shooterMotorSpeed", "%.2f", shooterPower);
            telemetry.addData("shooterLaunchPosition", "%.3f", launchServoAngle);
            telemetry.update();
        }
    }
}


