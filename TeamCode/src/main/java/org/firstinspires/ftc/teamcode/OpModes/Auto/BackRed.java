package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.AutoDriveCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class BackRed extends AutoBase{
    Command MoveLaunchPreload, PrepareToGrab1, GrabSet1, MoveToMidpoint, MoveToLaunch1, PrepareToGrab2, GrabSet2, MoveToMidPoint2, MoveToLaunch2, leave;
    Path launchPreload, prepGrab1, grab1, midpoint, Launch1, prepGrab2, grab2, midpoint2, launch2, Leave;

    Pose startPose = new Pose(64, 7, Math.toRadians(0));
    Pose launchPreloadPose = new Pose(60, 10, Math.toRadians(-25));
    Pose prepGrab1Pose = new Pose(46, 30, Math.toRadians(0));
    Pose grab1Pose = new Pose(14, 32, Math.toRadians(0));
    Pose midpointPose = new Pose(71, 60, Math.toRadians(90));
    Pose launch1Pose = new Pose(55, 10, Math.toRadians(-35));
    Pose prepGrab2Pose = new Pose(45, 60, Math.toRadians(180));
    Pose grab2Pose = new Pose(16, 60, Math.toRadians(180));
    Pose midpoint2Pose = new Pose(71, 60, Math.toRadians(90));
    Pose launch2Pose = new Pose(80, 81, Math.toRadians(45));
    Pose leavePose = new Pose(59, 48, Math.toRadians(45));


    @Override
    public void initialize() {
        makeAuto();
        buildPath();
        register();

        MoveLaunchPreload = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(launchPreload, true);
        });

        PrepareToGrab1 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(prepGrab1, true);
        });

        GrabSet1 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(grab1, true);
        });

        MoveToMidpoint = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(midpoint, true);
        });

        MoveToLaunch1 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(Launch1, true);
        });
        PrepareToGrab2 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(prepGrab2, true);
        });


        GrabSet2 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(grab2, true);
        });


        MoveToMidPoint2 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(midpoint2, true);
        });


        MoveToLaunch2 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(launch2, true);
        });

        leave = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(Leave, true);
        });



        SequentialCommandGroup number5IsAlive = new SequentialCommandGroup(
                MoveLaunchPreload,
                new AutoDriveCommand(autoDriveSubsystem, telemetry),
                PrepareToGrab1,
                new AutoDriveCommand(autoDriveSubsystem, telemetry),
                GrabSet1,
                new AutoDriveCommand(autoDriveSubsystem, telemetry),
                MoveToLaunch1,
                new AutoDriveCommand(autoDriveSubsystem, telemetry)


        );




        // Create The Path Commands



        schedule(new SequentialCommandGroup(


                number5IsAlive

        ));
    }


    public void makeAuto(){
        //hardware map init
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        autoDriveSubsystem = new AutoDriveSubsystem(follower, telemetry, startPose);
        follower.setMaxPower(1);

        // launcher
        launchAngle = hardwareMap.get(Servo.class, "launchAngle");
        launchMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");
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
        distance = new DistanceSubsystem(fSensor, mSensor, bSensor);
        intake = new IntakeSubsystem(frontIntakeServo, frontPassServo, backIntakeServo, backPassServo);
        launch = new LaunchSubsystem(launchMotor, launchAngle, turnServo ,launchServo);
        limelight = new LimeLightSubsystem(Limelight);

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

        //midpoint
        midpoint = new Path(new BezierCurve(grab1Pose, midpointPose));
        midpoint.setLinearHeadingInterpolation(grab1Pose.getHeading(), midpointPose.getHeading());
        midpoint.setTimeoutConstraint(250);


        //launch1
        Launch1 = new Path(new BezierCurve(grab1Pose, launch1Pose));
        Launch1.setLinearHeadingInterpolation(grab1Pose.getHeading(), launch1Pose.getHeading());
        Launch1.setTimeoutConstraint(250);

        //prepGrab2
        prepGrab2 = new Path(new BezierCurve(launch1Pose, prepGrab2Pose));
        prepGrab2.setLinearHeadingInterpolation(launch1Pose.getHeading(), prepGrab2Pose.getHeading());
        prepGrab2.setTimeoutConstraint(250);

        //grab2
        grab2 = new Path(new BezierLine(prepGrab2Pose, grab2Pose));
        grab2.setTimeoutConstraint(250);


        //midpoint2
        midpoint2 = new Path(new BezierCurve(grab2Pose, midpoint2Pose));
        midpoint2.setLinearHeadingInterpolation(grab2Pose.getHeading(), midpoint2Pose.getHeading());
        midpoint2.setTimeoutConstraint(250);

        //launch2
        launch2 = new Path(new BezierCurve(midpoint2Pose, launch2Pose));
        launch2.setLinearHeadingInterpolation(midpoint2Pose.getHeading(), launch2Pose.getHeading());
        launch2.setTimeoutConstraint(250);

        //leave
        Leave = new Path(new BezierCurve(launch2Pose, leavePose));
        Leave.setLinearHeadingInterpolation(launch2Pose.getHeading(), leavePose.getHeading());
        Leave.setTimeoutConstraint(250);














    }

}
