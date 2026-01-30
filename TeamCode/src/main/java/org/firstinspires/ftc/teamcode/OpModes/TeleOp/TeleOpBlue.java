package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.Command;
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
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.Auto.AutoLaunch;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.CorrectMotorSpeedCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints.closeBackSetPointCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints.backMiddleSetPointCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints.midSetPointCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.TeleOpIntakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TelemetrySubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;
import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoIntakeToLauncher;
import org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups.CancelCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.CompTelemetryCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.ManJoystickPassCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeBackToFront;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeFrontToBack;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.ManIntakeToLauncher;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.PukeCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints.backSetPointCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.ManualTurntableCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.limelightTurnCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.function.Supplier;

@TeleOp
public class TeleOpBlue extends CommandOpMode {
    // drive variables
    private Follower follower;
    public static Pose startingPose; //See ExampleAuto to understand how to use this
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private GamepadEx driverOp;
    private GamepadEx operatorOp;

    // launcher variables
    private DcMotorEx launchMotor;
    private DcMotorEx launchMotor2;
    private Servo launchAngleServo;
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

    private boolean redAlliance = true;

    private TelemetrySubsystem telemetrySubsystem;

    private AutoDriveSubsystem autoDriveSubsystem;
    public Command compTel;


    @Override
    public void initialize() {
        // controlAssignments
        driverOp = new GamepadEx(gamepad1);
        operatorOp = new GamepadEx(gamepad2);

        //limelight
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        //Follower
        follower = Constants.createFollower(hardwareMap);

        follower.setStartingPose(new Pose(0, 0, PI));
        follower.setPose(new Pose(0, 0, PI));

        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        pathChain = () -> follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(45, 98, PI))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();



        // launcher
        launchAngleServo = hardwareMap.get(Servo.class, "launchAngle");
        launchMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");
        launchMotor2 = hardwareMap.get(DcMotorEx.class,"launchMotor2");
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
        launchSubsystem = new LaunchSubsystem(launchMotor, launchMotor2,launchAngleServo, turnServo ,launchServo);
        limelightSubsystem = new LimeLightSubsystem(limelight);

        //turntable
        TurnSubsystem = new TurnTableSubsystem(turnServo);

        telemetrySubsystem = new TelemetrySubsystem(telemetry,TurnSubsystem,limelightSubsystem,launchSubsystem,intakeSubsystem,distanceSubsystem);


        //Drive Subsystem
        autoDriveSubsystem = new AutoDriveSubsystem(follower, telemetry);


        // Driver commands
        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(
                        new AutoLaunch(distanceSubsystem,intakeSubsystem,launchSubsystem,telemetry))
                ;


        driverOp.getGamepadButton(GamepadKeys.Button.X) // Heading Reset
                .whenPressed(new InstantCommand(() -> {follower.setPose(new Pose(0, 0, PI));}));

        driverOp.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new CorrectMotorSpeedCommand(launchSubsystem));
//
//        driverOp.getGamepadButton(GamepadKeys.Button.A)
//                .whenPressed(new InstantCommand(() -> {
//                    launchAngleServo.setPosition(0);
//                }));
//        driverOp.getGamepadButton(GamepadKeys.Button.B)
//                .whenPressed(new InstantCommand(() -> {
//                    launchAngleServo.setPosition(0.1);
//                }));
        driverOp.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new CancelCommand(intakeSubsystem,launchSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .toggleWhenPressed(new ManIntakeToLauncher(intakeSubsystem), new IntakeStopServoCommand(intakeSubsystem));


        // Operator commands

        operatorOp.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new CancelCommand(intakeSubsystem,launchSubsystem));
        operatorOp.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new IntakeFrontToBack(intakeSubsystem, distanceSubsystem), new IntakeStopServoCommand(intakeSubsystem));
        operatorOp.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(new IntakeBackToFront(intakeSubsystem, distanceSubsystem), new IntakeStopServoCommand(intakeSubsystem));

        operatorOp.getGamepadButton(GamepadKeys.Button.BACK)
                .whenHeld(new PukeCommand(intakeSubsystem))
                .whenReleased(new IntakeStopServoCommand(intakeSubsystem));
        operatorOp.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new TeleOpIntakeCommand(distanceSubsystem,intakeSubsystem));

        TurnSubsystem.setDefaultCommand(new limelightTurnCommand(limelightSubsystem,TurnSubsystem, launchSubsystem, false));
        operatorOp.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .toggleWhenPressed(new ManualTurntableCommand(TurnSubsystem,limelightSubsystem,operatorOp::getLeftX), new limelightTurnCommand(limelightSubsystem, TurnSubsystem,launchSubsystem, false));

        operatorOp.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new closeBackSetPointCommand(launchSubsystem, TurnSubsystem , false));

        operatorOp.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new backMiddleSetPointCommand(launchSubsystem, TurnSubsystem, false));

        operatorOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new backSetPointCommand(launchSubsystem, TurnSubsystem, false));

        operatorOp.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new midSetPointCommand(launchSubsystem,TurnSubsystem));

        intakeSubsystem.setDefaultCommand(new ManJoystickPassCommand(intakeSubsystem, operatorOp::getRightY));

        // launch
        telemetry.addData("init complete", "init done");
        telemetry.update();

        follower.startTeleopDrive();
    }

    @Override
    public void runOpMode() {

        initialize();

        waitForStart();

        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            follower.update();
            telemetryM.update();

//            autoDriveSubsystem.setDefaultCommand(new TeleDriveCommand(autoDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, false));
//            driverOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
//                    .whenHeld(new TeleSlowDriveCommand(autoDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, false))
//                    .whenInactive(new TeleDriveCommand(autoDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, false));
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    false // Robot Centric
            );
            TurnSubsystem.setDefaultCommand(new limelightTurnCommand(limelightSubsystem,TurnSubsystem, launchSubsystem, false));

            run();
            telemetrySubsystem.setDefaultCommand(new CompTelemetryCommand(telemetrySubsystem));
        }
        reset();
    }
//    public void run() {
//        super.run(); // This is important to run the command scheduler
//
//        //Call this once per loop
//        follower.update();
//        telemetryM.update();
//
//        follower.setTeleOpDrive(
//                -gamepad1.left_stick_y,
//                -gamepad1.left_stick_x,
//                gamepad1.right_stick_x,
//                false // Robot Centric
//        );
//
//        telemetrySubsystem.setDefaultCommand(new CompTelemetryCommand(telemetrySubsystem));




//            limelightSubsystem.setDefaultCommand(new LimelightCommand(limelightSubsystem, limelightSubsystem.getResult(), telemetry));

        // print to console
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading", follower.getPose().getHeading());
//        telemetry.addData("|v|", follower.getVelocity().getMagnitude());
//        telemetry.addData("theta", follower.getVelocity().getTheta());
//        telemetry.addData("x-component", follower.getVelocity().getXComponent());
//        telemetry.addData("y-component", follower.getVelocity().getYComponent());
//        telemetry.update();
    }





