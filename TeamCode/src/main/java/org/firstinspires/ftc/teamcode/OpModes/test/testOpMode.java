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

@TeleOp
public class testOpMode extends LinearOpMode {

    private CRServo frontIntake;
    private CRServo backIntake;
    private CRServo frontPass;
    private CRServo backPass;
    private Servo flipper;
    private DcMotor shooterMotor;
    private CRServo shooterServo;
    private SparkFunOTOS spark;
    private DriveSubsystem driveSubsystem;
    private org.firstinspires.ftc.teamcode.Commands.DriveCommand driveCommand;


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
        shooterServo = hardwareMap.get(CRServo.class, "launchServo");
        spark = hardwareMap.get(SparkFunOTOS.class,"sparkfun");



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
                shooterServo.setPower(1);
            } else {
                shooterMotor.setPower(0);
                shooterServo.setPower(0);
            }


        }


    }
}
