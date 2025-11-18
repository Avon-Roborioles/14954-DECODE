package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.*;
import com.pedropathing.paths.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;



public class AutoDriveSubsystem extends SubsystemBase {
    private Follower follower;
    private Telemetry telemetry;

    public AutoDriveSubsystem(Follower follower, Telemetry telemetry, Pose startPose){
        this.follower = follower;
        this.telemetry = telemetry;
        setPose(startPose);
    }

    public void followPath(Path path, boolean holdEnd){
        follower.followPath(path, holdEnd);
    }
    public void followPath(PathChain pathChain, boolean holdEnd){
        follower.followPath(pathChain, holdEnd);
    }
    public void setMaxPower(double maxPower){
        follower.setMaxPower(maxPower);
    }
    public void setPose(Pose pose){
        follower.setPose(pose);
    }
    public void setStartingPose(Pose pose){
        follower.setStartingPose(pose);
    }
    public void holdPoint(BezierPoint point, double heading){
        follower.holdPoint(point, heading);
    }
    public void update(){
        follower.update();
      //  follower.drawOnDashBoard();
    }
    public boolean isBusy(){
        return follower.isBusy();
    }
    public void breakFollowing(){
        follower.breakFollowing();
    }
    public PathBuilder pathBuilder(){
        return follower.pathBuilder();
    }
    public Path getCurrentPath(){
        return follower.getCurrentPath();
    }
    public boolean atParametricEnd(){
        return follower.atParametricEnd();
    }
    public Pose getPose(){
        return follower.getPose();
    }
    public void startTeleopDrive(){
        follower.startTeleopDrive();
    }
    public void setTeleOpDrive(double forwardSpeed, double strafeSpeed, double heading, boolean robotCentric){
        follower.setTeleOpDrive(-strafeSpeed,forwardSpeed
                 , heading, false);
        follower.updatePose();
    }
    public void holdPosition(){
        follower.holdPoint(getPose());
    }
}
