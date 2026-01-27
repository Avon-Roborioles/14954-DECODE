package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static java.lang.Math.PI;

import android.widget.ToggleButton;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.PerpetualCommand;
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
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TelemetrySubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;;
import org.firstinspires.ftc.teamcode.commands.teleop.CompTelemetryCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.ManIntakeToLauncher;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.PukeCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.SmartLaunchSequence;
import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.OTOsSmartLaunch;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.function.Supplier;

@TeleOp (name = "TEST MY MATH")
public class TestMyMath extends CommandOpMode {
    // drive variables
    private Follower follower;
    public static Pose startingPose;
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private GamepadEx driverOp;

    // launcher variables
    private DcMotorEx launchMotor;
    private DcMotorEx launchMotor2;
    private Servo launchAngleServo;
    private OTOsSmartLaunch launchSubsystem;
    // intake variables
    private CRServo frontIntakeServo;
    private CRServo frontPassServo;
    private CRServo backIntakeServo;
    private CRServo backPassServo;
    private IntakeSubsystem intakeSubsystem;

    // Turntable Variables
    private Servo turnServo;



    @Override
    public void initialize() {
        // controlAssignments
        driverOp = new GamepadEx(gamepad1);

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
        launchMotor2 = hardwareMap.get(DcMotorEx.class, "launchMotor2");
        turnServo = hardwareMap.get(Servo.class, "turnServo");
        // intake
        frontIntakeServo = hardwareMap.get(CRServo.class, "frontIntake");
        frontPassServo = hardwareMap.get(CRServo.class, "frontPass");
        backIntakeServo = hardwareMap.get(CRServo.class, "backIntake");
        backPassServo = hardwareMap.get(CRServo.class, "backPass");

        //Subsystems
        intakeSubsystem = new IntakeSubsystem(frontIntakeServo, frontPassServo, backIntakeServo, backPassServo);
        launchSubsystem = new OTOsSmartLaunch(turnServo, follower, launchMotor, launchMotor2, launchAngleServo, true); // boolean is isRed

        driverOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                        .toggleWhenPressed(new ManIntakeToLauncher(intakeSubsystem));
        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                        .toggleWhenPressed(new PukeCommand(intakeSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new SmartLaunchSequence(launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new InstantCommand(() -> {
                    launchSubsystem.changeMode();
                }));
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

            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    false // Robot Centric
            );

            run();
            telemetryM.addData("robot heading = ", follower.getHeading());
            telemetryM.addData("launch power = ", launchMotor.getPower() + "/" + launchMotor2.getPower());
            telemetryM.addData("table pos = ", turnServo.getPosition());
            telemetryM.addData("carrot pos = ", launchAngleServo.getPosition());
            telemetryM.addData("red = ", launchSubsystem.getMode());

        }
        reset();
    }
}
