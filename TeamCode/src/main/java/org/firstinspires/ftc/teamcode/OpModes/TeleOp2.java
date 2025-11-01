package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.ToggleBackIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.ToggleForwardIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.DecreaseLaunchPower;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.IncreaseLaunchPower;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.LaunchCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.function.Supplier;
@Configurable
@TeleOp
public class TeleOp2 extends OpMode {
    // drive variables
    private Follower follower;
    public static Pose startingPose; //See ExampleAuto to understand how to use this
    private boolean automatedDrive;
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private GamepadEx driverOp;
    private GamepadEx operator;
    public boolean intakeIsRunning;

    // launcher variables
    private DcMotor launchMotor;
    private Servo launchServo;
    private Servo turnServo;
    private LaunchSubsystem launchSubsystem;
    // intake variables
    private CRServo intakeServo1;
    private CRServo intakeServo2;
    private IntakeServoSubsystem intakeSubsystem;


    @Override
    public void init() {
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
        launchServo = hardwareMap.get(Servo.class, "launchServo");
        launchMotor = hardwareMap.get(DcMotor.class, "launchMotor");
        turnServo = hardwareMap.get(Servo.class, "turnServo");
        // intake
        intakeServo1 = hardwareMap.get(CRServo.class, "intakeServo1");
        intakeServo2 = hardwareMap.get(CRServo.class, "intakeServo2");
        intakeSubsystem = new IntakeServoSubsystem(intakeServo1, intakeServo2);
        intakeIsRunning = false;
        launchSubsystem = new LaunchSubsystem(launchMotor, launchServo, turnServo);
    }
    @Override
    public void loop() {
        //Call this once per loop
        follower.update();
        telemetryM.update();
        // button commands
        driverOp.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new ToggleForwardIntakeCommand(intakeSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new ToggleBackIntakeCommand(intakeSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new LaunchCommand(launchSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new LaunchCommand(launchSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new DecreaseLaunchPower(launchSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new IncreaseLaunchPower(launchSubsystem));
        if (!automatedDrive) {
            //Make the last parameter false for field-centric
            //In case the drivers want to use a "slowMode" you can scale the vectors

            //This is the normal version to use in the TeleOp
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x,
                    false // Robot Centric
            );
        }

        //Automated PathFollowing
        if (gamepad1.aWasPressed()) {
            follower.followPath(pathChain.get());
            automatedDrive = true;
        }

        //Stop automated following if the follower is done
        if (automatedDrive && (gamepad1.bWasPressed() || !follower.isBusy())) {
            follower.startTeleopDrive();
            telemetryM.addLine("drive program started");
            automatedDrive = false;
        }

        // print to console
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("|v|", follower.getVelocity().getMagnitude());
        telemetry.addData("theta", follower.getVelocity().getTheta());
        telemetry.addData("x-component", follower.getVelocity().getXComponent());
        telemetry.addData("y-component", follower.getVelocity().getYComponent());
        telemetry.addData("automatedDrive", automatedDrive);
        telemetry.update();
    }
}
