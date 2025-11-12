package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.FlipperSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
//import org.firstinspires.ftc.teamcode.commands.flipper.FlipItDown;
//import org.firstinspires.ftc.teamcode.commands.flipper.FlipItUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToFront;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToBack;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.RunMotor;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.StopMotor;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.function.Supplier;

@TeleOp
public class TeleOp3 extends CommandOpMode {
    // drive variables
    private Follower follower;
    public static Pose startingPose; //See ExampleAuto to understand how to use this
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private GamepadEx driverOp;
    private GamepadEx operator;

    // launcher variables
    private DcMotor launchMotor;
    private Servo launchAngle;
    private Servo turnServo;
    private LaunchSubsystem launchSubsystem;
    // intake variables
    private CRServo frontIntakeServo;
    private CRServo frontPassServo;
    private CRServo backIntakeServo;
    private CRServo backPassServo;
    private IntakeServoSubsystem intakeSubsystem;
    //Flipper

    private Servo flipperServo;
    private FlipperSubsystem flipper;


    @Override
    public void initialize() {
        // drive
        driverOp = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        pathChain = () -> follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(45, 98))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();
        // launcher
        launchAngle = hardwareMap.get(Servo.class, "launchAngle");
        launchMotor = hardwareMap.get(DcMotor.class, "launchMotor");
        turnServo = hardwareMap.get(Servo.class, "turnServo");
        // intake
        frontIntakeServo = hardwareMap.get(CRServo.class, "frontIntake");
        frontPassServo = hardwareMap.get(CRServo.class, "frontPass");
        backIntakeServo = hardwareMap.get(CRServo.class, "backIntake");
        backPassServo = hardwareMap.get(CRServo.class, "backPass");
        intakeSubsystem = new IntakeServoSubsystem(frontIntakeServo, frontPassServo, backIntakeServo, backPassServo);
        launchSubsystem = new LaunchSubsystem(launchMotor, launchAngle, turnServo);
        //flipper
//        flipperServo = hardwareMap.get(Servo.class, "flipper");
//        flipper = new FlipperSubsystem(flipperServo);
        telemetry.addData("init complete", "init done");
        telemetry.update();
        telemetry.addData("run mode call", "ok");

        //Call this once per loop
        follower.update();
        telemetryM.update();
        // button commands
                  // intake toggles => side one
        telemetry.addData("op mode active", "ok");
        driverOp.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new IntakeFrontToBack(intakeSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new IntakeBackToFront(intakeSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new IntakeToLauncher(intakeSubsystem));
                  // launch
        driverOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .toggleWhenPressed(new RunMotor(launchSubsystem), new StopMotor(launchSubsystem));
//        driverOp.getGamepadButton(GamepadKeys.Button.BACK)
//                .toggleWhenPressed(new FlipItUp(flipper), new FlipItDown(flipper));

            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x,
                    false // Robot Centric
            );


            // print to console
            telemetry.addData("x", follower.getPose().getX());
            telemetry.addData("y", follower.getPose().getY());
            telemetry.addData("heading", follower.getPose().getHeading());
            telemetry.addData("|v|", follower.getVelocity().getMagnitude());
            telemetry.addData("theta", follower.getVelocity().getTheta());
            telemetry.addData("x-component", follower.getVelocity().getXComponent());
            telemetry.addData("y-component", follower.getVelocity().getYComponent());
            telemetry.update();
        }
    }


