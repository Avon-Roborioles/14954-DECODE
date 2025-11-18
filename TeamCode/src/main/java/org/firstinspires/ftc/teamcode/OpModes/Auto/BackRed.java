package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class BackRed extends AutoBase{
    Command MoveLaunchPreload, PrepareToGrab1, GrabSet1, MoveToMidpoint, MoveToLaunch1, PrepareToGrab2, GrabSet2, MoveToMidPoint2, MoveToLaunch2, leave;
    Path launchPreload, prepGrab1, grab1, midpoint, Launch1, prepGrab2, grab2, midpoint2, launch2, Leave;

    Pose startPose = new Pose(64, 7, Math.toRadians(90));
    Pose launchPreloadPose = new Pose(72, 23, Math.toRadians(55));
    Pose prepGrab1Pose = new Pose(46, 36, Math.toRadians(180));
    Pose grab1Pose = new Pose(14, 36, Math.toRadians(180));
    Pose midpointPose = new Pose(71, 60, Math.toRadians(90));
    Pose launch1Pose = new Pose(80, 81, Math.toRadians(45));
    Pose prepGrab2Pose = new Pose(45, 60, Math.toRadians(180));
    Pose grab2Pose = new Pose(16, 60, Math.toRadians(180));
    Pose midpoint2Pose = new Pose(71, 60, Math.toRadians(90));
    Pose launch2Pose = new Pose(80, 81, Math.toRadians(45));
    Pose leavePose = new Pose(59, 48, Math.toRadians(45));


    @Override
    public void initialize() {


        // Create The Path Commands


    }


    public void makeAuto(){
        //hardware map init
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        follower.setMaxPower(1);

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
        grab1 = new Path(new BezierLine(prepGrab1Pose, grab1Pose));
        grab1.setTimeoutConstraint(250);

        //midpoint
        midpoint = new Path(new BezierCurve(grab1Pose, midpointPose));
        midpoint.setLinearHeadingInterpolation(grab1Pose.getHeading(), midpointPose.getHeading());
        midpoint.setTimeoutConstraint(250);


        //launch1
        Launch1 = new Path(new BezierCurve(midpointPose, launch1Pose));
        Launch1.setLinearHeadingInterpolation(midpointPose.getHeading(), launch1Pose.getHeading());
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
        Leave = new Path(new BezierLine(launch2Pose, leavePose));
        Leave.setTimeoutConstraint(250);

    }

}
