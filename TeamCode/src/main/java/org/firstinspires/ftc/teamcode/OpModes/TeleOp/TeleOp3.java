package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
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
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
//import org.firstinspires.ftc.teamcode.commands.flipper.FlipItDown;
//import org.firstinspires.ftc.teamcode.commands.flipper.FlipItUp;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;
//import org.firstinspires.ftc.teamcode.commands.LimelightCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenterAndUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.PukeCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.turntable.limelightAngleCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToFront;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToBack;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor.DistanceIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.RunMotor;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.StopMotor;
//import org.firstinspires.ftc.teamcode.commands.teleop.turntable.TurntableTest1;
//import org.firstinspires.ftc.teamcode.commands.teleop.turntable.TurntableTest2;
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
    private CRServo launchServo;
    private LaunchSubsystem launchSubsystem;
    // intake variables
    private CRServo frontIntakeServo;
    private CRServo frontPassServo;
    private CRServo backIntakeServo;
    private CRServo backPassServo;
    private IntakeSubsystem intakeSubsystem;
    //Distance Sensor Variables
    private DigitalChannel fSensor, mSensor, bSensor;
    private DistanceSubsystem distanceSubsystem;
   // Turntable Variables
   private Servo turnServo;
    private TurnTableSubsystem TurnSubsystem;
    // Limelight Variables
    private LimeLightSubsystem limelightSubsystem;
    private Limelight3A limelight;


    @Override
    public void initialize() {
        // controlAssignments
        driverOp = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);

        //limelight
//        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        //Follower
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        pathChain = () -> follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(45, 98, PI))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();
        // launcher
        launchAngle = hardwareMap.get(Servo.class, "launchAngle");
        launchMotor = hardwareMap.get(DcMotor.class, "launchMotor");
        launchServo = hardwareMap.get(CRServo.class, "launchServo");
        turnServo = hardwareMap.get(Servo.class, "turnServo");
        // intake
        frontIntakeServo = hardwareMap.get(CRServo.class, "frontIntake");
        frontPassServo = hardwareMap.get(CRServo.class, "frontPass");
        backIntakeServo = hardwareMap.get(CRServo.class, "backIntake");
        backPassServo = hardwareMap.get(CRServo.class, "backPass");
        // distance Sensors
        fSensor = hardwareMap.get(DigitalChannel.class, "fSensor");
        mSensor = hardwareMap.get(DigitalChannel.class, "mSensor");
        bSensor = hardwareMap.get(DigitalChannel.class, "bSensor");
        fSensor.setMode(DigitalChannel.Mode.INPUT);
        mSensor.setMode(DigitalChannel.Mode.INPUT);
        bSensor.setMode(DigitalChannel.Mode.INPUT);

        //Subsystems
        distanceSubsystem = new DistanceSubsystem(fSensor, mSensor, bSensor);
        intakeSubsystem = new IntakeSubsystem(frontIntakeServo, frontPassServo, backIntakeServo, backPassServo);
        launchSubsystem = new LaunchSubsystem(launchMotor, launchAngle, turnServo ,launchServo);
//        limelightSubsystem = new LimeLightSubsystem(limelight);

        //turntable
        TurnSubsystem = new TurnTableSubsystem(turnServo);

        // button commands
        driverOp.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new IntakeFrontToBack(intakeSubsystem, distanceSubsystem), new IntakeStopServoCommand(intakeSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(new IntakeBackToFront(intakeSubsystem, distanceSubsystem), new IntakeStopServoCommand(intakeSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.B)
                .toggleWhenPressed(new IntakeToLauncher(intakeSubsystem), new IntakeStopServoCommand(intakeSubsystem));
//        driverOp.getGamepadButton(GamepadKeys.Button.X)
//                        .toggleWhenPressed(new IntakeFrontToCenter(intakeSubsystem), new IntakeStopServoCommand(intakeSubsystem));
//        driverOp.getGamepadButton(GamepadKeys.Button.X)
//                        .toggleWhenPressed(new TurntableTest1(TurnSubsystem), new TurntableTest2(TurnSubsystem));
//        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
//                        .whenHeld(new DistanceIntakeCommand(distanceSubsystem, intakeSubsystem, launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.X)
                        .whenHeld(new IntakeCommand(distanceSubsystem,intakeSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                        .whenHeld(new InstantCommand(() -> {
                            new DistanceIntakeCommand(distanceSubsystem, intakeSubsystem, launchSubsystem).schedule();
                        }));
        driverOp.getGamepadButton(GamepadKeys.Button.BACK)
                        .whenHeld(new PukeCommand(intakeSubsystem))
                                .whenReleased(new IntakeStopServoCommand(intakeSubsystem));



//        TurnSubsystem.setDefaultCommand(new limelightAngleCommand(limelightSubsystem,TurnSubsystem));
        // launch
        driverOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .toggleWhenPressed(new RunMotor(launchSubsystem), new StopMotor(launchSubsystem));

        telemetry.addData("init complete", "init done");
        telemetry.update();

        follower.startTeleopDrive();
    }

    @Override
    public void run() {
        super.run(); // This is important to run the command scheduler

        //Call this once per loop
        follower.update();
        telemetryM.update();

        follower.setTeleOpDrive(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                false // Robot Centric
        );
        driverOp.getGamepadButton(GamepadKeys.Button.BACK) // Heading Reset
                .whenPressed(new InstantCommand(() -> {follower.setPose(new Pose(0, 0, PI));}));


//            limelightSubsystem.setDefaultCommand(new LimelightCommand(limelightSubsystem, limelightSubsystem.getResult(), telemetry));

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



