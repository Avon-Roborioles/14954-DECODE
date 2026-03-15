package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
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

import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups.AutoLaunch;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(group = "Human", preselectTeleOp = "TeleOpBlue")
public class BackBlueHumanV3 extends AutoBase{
    Command MoveRight, PrepareToGrab1, GrabSet1, MoveToMidpoint, MoveToLaunch1, PrepareToGrab2, GrabSet2, MoveToMidPoint2, MoveToLaunch2, leave, BackUpToGrab, GrabSet1again;
    Path MoveRightPath, prepGrab1, grab1, midpoint, Launch1, prepGrab2, grab2, midpoint2, launch2, Leave, backUpGrab,GrabSetAgain;


    Pose startPose = new Pose(48, 7, Math.toRadians(0));
    Pose prepGrab1Pose = new Pose(48, 25, Math.toRadians(25)); //
    Pose grab1Pose = new Pose(13, 19, Math.toRadians(25)); //101,8,-90
    Pose grab1Pose2 = new Pose(11, 12, Math.toRadians(45));
    Pose midpointPose = new Pose(22, 9, Math.toRadians(0));
    Pose midpoint2Pose = new Pose(14, 9, Math.toRadians(0));
    Pose backUpGrabPose = new Pose(21,8,Math.toRadians(0));
    Pose grabAgainPose = new Pose(14,6,Math.toRadians(0));
    Pose MoveRightPose = new Pose(14, 8, Math.toRadians(0));
    Pose launch1Pose = new Pose(42, 15, Math.toRadians(0));
    Pose prepGrab2Pose = new Pose(48, 31, Math.toRadians(0));
    Pose grab2Pose = new Pose(15, 31, Math.toRadians(0));

    Pose launch2Pose = new Pose(44.5k5, 15, Math.toRadians(0));
    Pose leavePose = new Pose(41.5, 25, Math.toRadians(0));

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

        MoveRight = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(MoveRightPath, true);
        });

        PrepareToGrab1 = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(prepGrab1, true);
        });

        GrabSet1 = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(grab1, true);
        });

        MoveToMidpoint = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(midpoint, true);
        });

        MoveToLaunch1 = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(Launch1, true);
        });
        PrepareToGrab2 = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(prepGrab2, true);
        });


        GrabSet2 = new InstantCommand(() -> {
            follower.setMaxPower(0.65);
            autoDriveSubsystem.followPath(grab2, true);
        });


        MoveToMidPoint2 = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(midpoint2, true);
        });


        MoveToLaunch2 = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(launch2, true);
        });

        leave = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(Leave, true);
        });
        BackUpToGrab = new InstantCommand(() -> {
            follower.setMaxPower(1);
            autoDriveSubsystem.followPath(backUpGrab, true);
        });
        GrabSet1again = new InstantCommand(() -> {
            follower.setMaxPower(0.8);
            autoDriveSubsystem.followPath(GrabSetAgain, true);
        });



        SequentialCommandGroup number5IsAlive = new SequentialCommandGroup(
//                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry),
                new SequentialCommandGroup(
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoBackSetPoint(launch,turnTableSubsystem,false),
                        new AutoLaunch(distance,intake,launch,lightSubsystem,telemetry),
                        new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),
//                        MoveLaunchPreload,
//                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),

                        PrepareToGrab2,
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry),
                        new ParallelCommandGroup(
                                new AutoIntakeCommand(distance,intake,lightSubsystem).withTimeout(2000),
                                GrabSet2,
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem, telemetry)
                        ),




                        MoveToLaunch1,
                        new ParallelCommandGroup(
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoBackSetPoint(launch,turnTableSubsystem,false)
                        )),
                new AutoLaunch(distance,intake,launch,lightSubsystem,telemetry),
                new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),

                PrepareToGrab1,
                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),

                new ParallelCommandGroup(
                        new AutoIntakeCommand(distance,intake,lightSubsystem).withTimeout(4750),
                        new SequentialCommandGroup(
                                GrabSet1,
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry).withTimeout(2000),
                                MoveToMidpoint,
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
                                MoveToMidPoint2,
                                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry).withTimeout(2000)
//                                            MoveRight,
//                                            new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
//                                            BackUpToGrab,
//                                            new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
//                                            GrabSet1again,
//                                            new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry)
                        )
                ),
                MoveToLaunch2,
                new ParallelCommandGroup(
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry),
                        new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoBackSetPoint(launch,turnTableSubsystem,false)
                ),
                new AutoLaunch(distance,intake,launch,lightSubsystem,telemetry),
                new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.StopMotor(launch),
                leave,
                new org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands.AutoDriveCommand(autoDriveSubsystem,telemetry)





        );




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
        lightSubsystem = new LightSubsystem(light, light2, false);

        //turntable
        turnTableSubsystem = new TurnTableSubsystem(turnServo);






    }
    public void buildPath(){
        //launch Preload
        MoveRightPath = new Path(new BezierCurve(midpoint2Pose, MoveRightPose));
        MoveRightPath.setLinearHeadingInterpolation(midpoint2Pose.getHeading(), MoveRightPose.getHeading());

        MoveRightPath.setTimeoutConstraint(250);

        //prepGrab1
        prepGrab1 = new Path(new BezierCurve(startPose, prepGrab1Pose));
        prepGrab1.setLinearHeadingInterpolation(launch1Pose.getHeading(), prepGrab1Pose.getHeading());


        //grab1
        grab1 = new Path(new BezierCurve(prepGrab1Pose,grab1Pose,grab1Pose2));
        grab1.setLinearHeadingInterpolation(prepGrab1Pose.getHeading(), grab1Pose2.getHeading());

        //midpoint
        midpoint = new Path(new BezierCurve(grab1Pose2, midpointPose));
        midpoint.setLinearHeadingInterpolation(grab1Pose2.getHeading(), midpointPose.getHeading());



        //launch1
        Launch1 = new Path(new BezierCurve(grab1Pose, launch1Pose));
        Launch1.setLinearHeadingInterpolation(grab1Pose.getHeading(), launch1Pose.getHeading());
        Launch1.setTimeoutConstraint(250);

        //prepGrab2
        prepGrab2 = new Path(new BezierCurve(startPose, prepGrab2Pose));
        prepGrab2.setLinearHeadingInterpolation(startPose.getHeading(), prepGrab2Pose.getHeading());
        prepGrab2.setTimeoutConstraint(50);

        //grab2
        grab2 = new Path(new BezierLine(prepGrab2Pose, grab2Pose));
        grab2.setLinearHeadingInterpolation(prepGrab2Pose.getHeading(), grab2Pose.getHeading());


        //midpoint2
        midpoint2 = new Path(new BezierCurve(midpointPose, midpoint2Pose));
        midpoint2.setLinearHeadingInterpolation(midpointPose.getHeading(), midpoint2Pose.getHeading());



        backUpGrab = new Path(new BezierCurve(MoveRightPose,backUpGrabPose ));
        backUpGrab.setLinearHeadingInterpolation(MoveRightPose.getHeading(), backUpGrabPose.getHeading());

        GrabSetAgain = new Path(new BezierCurve(backUpGrabPose, grabAgainPose));
        GrabSetAgain.setLinearHeadingInterpolation(backUpGrabPose.getHeading(),grabAgainPose.getHeading());

        //launch2
        launch2 = new Path(new BezierLine(grab2Pose, launch2Pose));
        launch2.setConstantHeadingInterpolation(grab2Pose.getHeading());
        launch2.setTimeoutConstraint(250);

        //leave
        Leave = new Path(new BezierCurve(launch2Pose, leavePose));
        Leave.setLinearHeadingInterpolation(launch2Pose.getHeading(), leavePose.getHeading());
        Leave.setTimeoutConstraint(250);














    }
}
