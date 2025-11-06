package org.firstinspires.ftc.teamcode.OpModes.test;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

@TeleOp
public class testOpMode extends LinearOpMode {

    private CRServo frontIntake;
    private CRServo backIntake;
    private CRServo frontPass;
    private CRServo backPass;
    private Servo flipper;
    private DcMotor shooterMotor;
    private SparkFunOTOS spark;
    private DriveSubsystem driveSubsystem;
    private org.firstinspires.ftc.teamcode.Commands.DriveCommand driveCommand;

    private Servo launchAngle; // 0 to .4
    private double anglePos;
    private LaunchSubsystem launchSubsystem;


    @Override
    public void runOpMode() throws InterruptedException {
        frontIntake = hardwareMap.get(CRServo.class, "frontIntake");
        backIntake = hardwareMap.get(CRServo.class, "backIntake");
        frontPass = hardwareMap.get(CRServo.class, "frontPass");
        Motor frontLeft = new Motor(hardwareMap, "frontLeft");
        Motor frontRight = new Motor(hardwareMap, "frontRight");
        Motor backLeft = new Motor(hardwareMap, "backLeft");
        Motor backRight = new Motor(hardwareMap, "backRight");
        backPass = hardwareMap.get(CRServo.class, "backPass");
        shooterMotor = hardwareMap.get(DcMotor.class, "launcher");
        spark = hardwareMap.get(SparkFunOTOS.class,"sparkfun");
        launchAngle = hardwareMap.get(Servo.class, "launchAngle");
        anglePos = 0.05;
        launchSubsystem = new LaunchSubsystem(shooterMotor, launchAngle);
        launchSubsystem.setLaunchAngle(.05);

        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, telemetry);

        // Command: map gamepad1 joysticks
        driveCommand = new org.firstinspires.ftc.teamcode.Commands.DriveCommand(
                driveSubsystem,
                () -> gamepad1.left_stick_x,       // strafe
                () -> -gamepad1.left_stick_y,      // forward (negated because up stick is -1)
                () -> gamepad1.right_stick_x,      // turn
                () -> 0.0,                         // heading (put IMU yaw here later)
                false                              // field-centric off for now
        );

        CommandScheduler.getInstance().schedule(driveCommand);

        waitForStart();

        while (opModeIsActive()) {


            CommandScheduler.getInstance().run();
            if (gamepad1.a){
                frontIntake.setPower(-1);
            } else if (gamepad1.y) {
                backIntake.setPower(1);

            } else{
                frontIntake.setPower(0);
                backIntake.setPower(0);
            }

            if (gamepad1.b){
                frontPass.setPower(-1);
                backPass.setPower(1);
            } else if (gamepad1.dpad_left){
                frontPass.setPower(-1);
            } else if(gamepad1.dpad_right){
                backPass.setPower(1);
            } else if(gamepad1.dpad_down){
                frontPass.setPower(1);
                backPass.setPower(-1);
            } else {
                frontPass.setPower(0);
                backPass.setPower(0);
            }

            if (gamepad1.x){
                shooterMotor.setPower(0.7);
            } else {
                shooterMotor.setPower(0);
            }
            // test launch angle servo
            if (gamepad2.dpad_down){
                launchAngle.setPosition(launchAngle.getPosition()-.05);
                telemetry.addData("HoodServoPos:", launchAngle.getPosition());
                telemetry.update();
            } else if (gamepad2.dpad_up){
                launchAngle.setPosition(launchAngle.getPosition()+.05);
                telemetry.addData("HoodServoPos:", launchAngle.getPosition());
                telemetry.update();
            }
        }


    }
}
