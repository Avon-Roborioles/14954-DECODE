package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoLaunch;
import org.firstinspires.ftc.teamcode.Commands.teleop.LimelightCommands.limelightAutoSpeed_TurnCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.runMotorAuto;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class BackRedHumanOnly9Ball extends AutoBase{

    Command MoveLaunchPreload, PrepareToGrab1, GrabSet1, MoveToMidpoint, MoveToLaunch1, PrepareToGrab, GrabSet, MoveToMidPoint2, MoveToLaunch2, leave;
    Path launchPreload, prepGrab1, grab1, midpoint, Launch1, prepGrab, grab, midpoint2, launch2, Leave;


    Pose startPose = new Pose(66, 7, Math.toRadians(0));
    Pose launchPreloadPose = new Pose(66, 9, Math.toRadians(0));
    Pose prepGrab1Pose = new Pose(66, 31, Math.toRadians(0)); //33y too far
    Pose grab1Pose = new Pose(101, 31, Math.toRadians(0));

    Pose launch1Pose = new Pose(66, 15, Math.toRadians(0));
    Pose prepGrabPose = new Pose(66, 12, Math.toRadians(0));
    Pose grabPose = new Pose(125, 12, Math.toRadians(-35));
    Pose midpointPose = new Pose(83, 5, Math.toRadians(0));
    Pose midpoint2Pose = new Pose(97, 7, Math.toRadians(-32));
    //    Pose grab2Pose2 = new
    Pose launch2Pose = new Pose(64, 15, Math.toRadians(0));
    Pose leavePose = new Pose(72, 7, Math.toRadians(0));

    @Override
    public void initialize(){

    }


    @Override
    public void runOpMode(){
        initialize();

        waitForStart();
        makeAuto();
        buildPath();
        register();

        MoveLaunchPreload = new InstantCommand(() -> {
            follower.setMaxPower(0.6);
            autoDriveSubsystem.followPath(launchPreload, true);
        });

        PrepareToGrab1 = new InstantCommand(() -> {
            follower.setMaxPower(0.8);
            autoDriveSubsystem.followPath(prepGrab1, true);
        });

        GrabSet1 = new InstantCommand(() -> {
            follower.setMaxPower(0.4);
            autoDriveSubsystem.followPath(grab1, true);
        });

        MoveToMidpoint = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(midpoint, true);
        });

        MoveToLaunch1 = new InstantCommand(() -> {
            follower.setMaxPower(0.7);
            autoDriveSubsystem.followPath(Launch1, true);
        });
        PrepareToGrab = new InstantCommand(() -> {
            follower.setMaxPower(0.7);
            autoDriveSubsystem.followPath(prepGrab, true);
        });


        GrabSet = new InstantCommand(() -> {
            follower.setMaxPower(0.55);
            autoDriveSubsystem.followPath(grab, true);
        });


        MoveToMidPoint2 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(midpoint2, true);
        });


        MoveToLaunch2 = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(launch2, true);
        });

        leave = new InstantCommand(() -> {
            follower.setMaxPower(0.5);
            autoDriveSubsystem.followPath(Leave, true);
        });



        SequentialCommandGroup number5IsAlive = new SequentialCommandGroup(



                MoveLaunchPreload,
                new limelightAutoSpeed_TurnCommand(limelight,turnTableSubsystem,launch,lightSubsystem,true),
                new ParallelCommandGroup(
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry),
//                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoBackSetPoint(launch,turnTableSubsystem,true)
                        new runMotorAuto(launch,lightSubsystem)
                ),



                new SequentialCommandGroup(

                        new AutoLaunch(distance, intake, launch, lightSubsystem,telemetry),
                        new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),
                        PrepareToGrab,
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry),
                        new ParallelCommandGroup(
                                new AutoIntakeCommand(distance,intake,lightSubsystem).withTimeout(3025),
                                GrabSet,
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry).withTimeout(3025)
                        ),
                        MoveToLaunch1,
                        new limelightAutoSpeed_TurnCommand(limelight,turnTableSubsystem,launch,lightSubsystem,true),
                        new ParallelCommandGroup(
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
//                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoBackSetPoint(launch,turnTableSubsystem,true)
                                new runMotorAuto(launch,lightSubsystem)
                        ),
                        new AutoLaunch(distance,intake,launch,lightSubsystem,telemetry),
                        new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),
                        PrepareToGrab,
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry),
                        new ParallelCommandGroup(
                                new AutoIntakeCommand(distance,intake,lightSubsystem).withTimeout(4000),
                                GrabSet,
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry).withTimeout(4000)
                        ),
                        MoveToLaunch2,
                        new ParallelCommandGroup(
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoBackSetPoint(launch,turnTableSubsystem,true)
                        ),
                        new AutoLaunch(distance,intake,launch,lightSubsystem,telemetry),
                        new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),
                        leave,
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry)



                ));




        // Create The Path Commands






        schedule(new SequentialCommandGroup(


                number5IsAlive

        ));

        // Create The Path Commands
        while (opModeIsActive() && !isStopRequested()){
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
        light2 = hardwareMap.get(Servo.class,"light2");
        //Subsystems
        distance = new DistanceSubsystem(fSensor, mSensor, bSensor);
        intake = new IntakeSubsystem(frontIntakeServo, frontPassServo, backIntakeServo, backPassServo);
        launch = new LaunchSubsystem(launchMotor, launchMotor2, launchAngle, turnServo ,launchServo);
        limelight = new LimeLightSubsystem(Limelight);
        lightSubsystem = new LightSubsystem(light, light2, true);

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

        //midpoint
        midpoint = new Path(new BezierCurve(grab1Pose, midpointPose));
        midpoint.setLinearHeadingInterpolation(grab1Pose.getHeading(), midpointPose.getHeading());
        midpoint.setTimeoutConstraint(250);


        //launch1
        Launch1 = new Path(new BezierCurve(grab1Pose, launch1Pose));
        Launch1.setLinearHeadingInterpolation(grab1Pose.getHeading(), launch1Pose.getHeading());
        Launch1.setTimeoutConstraint(250);

        //prepGrab2
        prepGrab = new Path(new BezierCurve(launch1Pose, prepGrabPose));
        prepGrab.setLinearHeadingInterpolation(launch1Pose.getHeading(), prepGrabPose.getHeading());
        prepGrab.setTimeoutConstraint(250);

        //grab2
//        grab2 = new Path(
//                new BezierCurve(
//                        prepGrab2Pose ,
//                        grab2Pose,
//                        midpointPose,
//                        midpoint2Pose,
//                        midpointPose,
//                        midpoint2Pose,midpointPose,grab2Pose,prepGrab2Pose,midpointPose,midpoint2Pose,grab2Pose,prepGrab2Pose,prepGrab2Pose,prepGrab2Pose,grab2Pose));
//        grab2.setLinearHeadingInterpolation(prepGrab2Pose.getHeading(), grab2Pose.getHeading());
//        grab2.setTimeoutConstraint(250);
        grab = new Path(
                new BezierCurve(
                        prepGrabPose,midpointPose, grabPose,midpointPose, grabPose, launch2Pose, grabPose));
        grab.setLinearHeadingInterpolation(prepGrabPose.getHeading(), grabPose.getHeading());
        grab.setTimeoutConstraint(250);


        //midpoint2
        midpoint2 = new Path(new BezierCurve(grabPose, midpoint2Pose));
        midpoint2.setLinearHeadingInterpolation(grabPose.getHeading(), midpoint2Pose.getHeading());
        midpoint2.setTimeoutConstraint(250);

        //launch2
        launch2 = new Path(new BezierCurve(grabPose, launch2Pose));
        launch2.setLinearHeadingInterpolation(grabPose.getHeading(), launch2Pose.getHeading());
        launch2.setTimeoutConstraint(250);

        //leave
        Leave = new Path(new BezierCurve(launch1Pose, leavePose));
        Leave.setLinearHeadingInterpolation(leavePose.getHeading(), leavePose.getHeading());
        Leave.setTimeoutConstraint(250);














    }
}
