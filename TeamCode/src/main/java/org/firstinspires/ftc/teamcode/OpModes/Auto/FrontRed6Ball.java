package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;
import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoLaunch;
import org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoFrontSetPoint;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "FrontRed6Ball", group = "Front Auto 6 Ball", preselectTeleOp = "TeleOpRed")
public class FrontRed6Ball extends AutoBase {
    Command MoveLaunchPreload, PrepareToGrab1, GrabSet1, MoveToMidpoint, MoveToLaunch1, PrepareToGrab2, GrabSet2, MoveToMidPoint2, MoveToLaunch2, leave;
    Path launchPreload, prepGrab1, grab1, midpoint, Launch1, prepGrab2, grab2, midpoint2, launch2, Leave;

    Pose startPose = new Pose(123, 124, Math.toRadians(-53));  // 126,122,40
    Pose launchPreloadPose = new Pose(85, 89, Math.toRadians(-45)); //98,98,45
    Pose prepGrab1Pose = new Pose(95, 84, Math.toRadians(0)); // 102,84,0
    Pose grab1Pose = new Pose(120, 84, Math.toRadians(0)); // 129,84,0
    Pose launch1Pose = new Pose(87, 89, Math.toRadians(-45)); // 98,98,45
    Pose prepGrab2Pose = new Pose(88,60,Math.toRadians(0));
    Pose Grab2Pose = new Pose(135,60,Math.toRadians(0));
    Pose launch2Pose = new Pose (81,83,Math.toRadians(-45));
    Pose leavePose = new Pose(90, 110, Math.toRadians(-180)); // 98,75,-180


    public void initialize(){

    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        makeAuto();
        buildPath();
        register();

        MoveLaunchPreload = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(launchPreload, true);
        });

        PrepareToGrab1 = new InstantCommand(() -> {
            follower.setMaxPower(0.7);
            autoDriveSubsystem.followPath(prepGrab1, true);
        });

        GrabSet1 = new InstantCommand(() -> {
            follower.setMaxPower(0.4);
            autoDriveSubsystem.followPath(grab1, true);
        });


        MoveToLaunch1 = new InstantCommand(() -> {
            follower.setMaxPower(0.7);
            autoDriveSubsystem.followPath(Launch1, true);
        });

        leave = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(Leave, true);
        });







        SequentialCommandGroup number5IsAlive = new SequentialCommandGroup(



                MoveLaunchPreload,
                new ParallelCommandGroup(
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry),
                        new AutoFrontSetPoint(launch,turnTableSubsystem,false)
                ),



                new SequentialCommandGroup(

                        new AutoLaunch(distance, intake, launch, lightSubsystem,telemetry),
                        new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),
                        PrepareToGrab1,
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry),
                        GrabSet1,
                        new ParallelCommandGroup(
                                new AutoIntakeCommand(distance,intake,lightSubsystem).withTimeout(3000),
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry)
                        ),
                        MoveToLaunch1,
                        new ParallelCommandGroup(
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
                                new AutoFrontSetPoint(launch,turnTableSubsystem,false)
                        ),
                        new AutoLaunch(distance,intake,launch,lightSubsystem,telemetry),
                        new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),
                        leave,
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry)



                ));

        schedule(new SequentialCommandGroup(


                number5IsAlive

        ));
        while (opModeIsActive()&& !isStopRequested()){
            run();
        }

        reset();
    }


    public void makeAuto(){
        //hardware map init
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        autoDriveSubsystem = new AutoDriveSubsystem(follower, telemetry);
        follower.setMaxPower(1);

        // launcher
        launchAngle = hardwareMap.get(Servo.class, "launchAngle");
        launchMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");
        launchMotor2 = hardwareMap.get(DcMotorEx.class, "launchMotor2");
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

        light = hardwareMap.get(Servo.class,"light");
        light2 = hardwareMap.get(Servo.class, "light2");
        //Subsystems
        distance = new DistanceSubsystem(fSensor, mSensor, bSensor);
        intake = new IntakeSubsystem(frontIntakeServo, frontPassServo, backIntakeServo, backPassServo);
        launch = new LaunchSubsystem(launchMotor, launchMotor2, launchAngle, turnServo ,launchServo);
        limelight = new LimeLightSubsystem(Limelight);
        lightSubsystem = new LightSubsystem(light,light2,true);

        //turntable
        turnTableSubsystem = new TurnTableSubsystem(turnServo);






    }
    public void buildPath(){
        //launch Preload
        launchPreload = new Path(new BezierCurve(startPose, launchPreloadPose));
        launchPreload.setLinearHeadingInterpolation(startPose.getHeading(), launchPreloadPose.getHeading());
        launchPreload.setTimeoutConstraint(250);

        //prepGrab1
        prepGrab1 = new Path(new BezierCurve(launchPreloadPose, prepGrab1Pose));
        prepGrab1.setLinearHeadingInterpolation(launchPreloadPose.getHeading(), prepGrab1Pose.getHeading());
        prepGrab1.setTimeoutConstraint(250);

        //grab1
        grab1 = new Path(new BezierCurve(prepGrab1Pose, grab1Pose));
        grab1.setLinearHeadingInterpolation(prepGrab1Pose.getHeading(), grab1Pose.getHeading());
        grab1.setTimeoutConstraint(250);

        Launch1 = new Path(new BezierCurve(grab1Pose, launch1Pose));
        Launch1.setLinearHeadingInterpolation(grab1Pose.getHeading(), launch1Pose.getHeading());
        Launch1.setTimeoutConstraint(250);



        //leave
        Leave = new Path(new BezierCurve(launchPreloadPose, leavePose));
        Leave.setLinearHeadingInterpolation(launchPreloadPose.getHeading(), leavePose.getHeading());
        Leave.setTimeoutConstraint(250);
    }

}
