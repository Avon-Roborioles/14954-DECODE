package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.arcrobotics.ftclib.command.Command;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



@Autonomous
public class BackBlue extends AutoBase {
    Command MoveLaunchPreload, PrepareToGrab1, GrabSet1, MoveToMidpoint, MoveToLaunch1, PrepareToGrab2, GrabSet2, MoveToMidPoint2, MoveToLaunch2, leave;
    Path launchPreload, prepGrab1, grab1, midpoint, Launch1, prepGrab2, grab2, midpoint2, launch2, Leave;

    Pose startPose = new Pose(80, 7, Math.toRadians(90));
    Pose launchPreloadPose = new Pose(74, 19, Math.toRadians(115));
    Pose prepGrab1Pose = new Pose(99, 36, Math.toRadians(0));
    Pose grab1Pose = new Pose(132, 36, Math.toRadians(0));
    Pose midpointPose = new Pose(73, 54, Math.toRadians(90));
    Pose launch1Pose = new Pose(66, 80, Math.toRadians(125));
    Pose prepGrab2Pose = new Pose(99, 36, Math.toRadians(0));
    Pose grab2Pose = new Pose(126, 60, Math.toRadians(0));
    Pose midpoint2Pose = new Pose(73, 36, Math.toRadians(90));
    Pose launch2Pose = new Pose(66, 80, Math.toRadians(125));
    Pose leavePose = new Pose(72, 48, Math.toRadians(90));









    @Override
    public void initialize() {


        // Create The Path Commands


    }


    public void MakeAuto(){

        //hardware map init

    }
    public void BuildPath(){
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
        Leave = new Path(new BezierCurve(launch2Pose, leavePose));
        Leave.setLinearHeadingInterpolation(launch2Pose.getHeading(), leavePose.getHeading());
        Leave.setTimeoutConstraint(250);














    }

}
