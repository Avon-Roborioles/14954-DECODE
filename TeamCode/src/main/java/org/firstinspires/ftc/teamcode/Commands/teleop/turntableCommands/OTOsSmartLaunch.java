package org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.pedropathing.follower.Follower;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class OTOsSmartLaunch extends SubsystemBase {
    private Servo turnservo;
    private Follower follower;
    private DcMotorEx motor1;
    private DcMotorEx motor2;
    private double chassisAngle, servoAngle;
    private Pose currentPos, goal;
    private boolean isRed;


    public OTOsSmartLaunch(Servo turnservo, Follower follower, DcMotorEx motor1, DcMotorEx motor2, boolean isRed){
        this.turnservo = turnservo;
        this.follower = follower;
        this.motor1 = motor1;
        this.motor2 = motor2;
        if(isRed){
            goal = new Pose(133, 135);
        } else {
            goal = new Pose(13, 135);
        }
    }
    public void turnToGoal(){
        currentPos = follower.getPose();
        double x = currentPos.getX();
        double y = currentPos.getY();
        double xd = Math.abs(x - goal.getX());
        double yd = y - goal.getY();
        double angleFromChassisToGoal = Math.tan(yd/xd);
        chassisAngle = currentPos.getHeading();
        servoAngle = turnservo.getPosition()*1800;
        double angleToTurn = angleFromChassisToGoal - chassisAngle - servoAngle;
        turnservo.setPosition(angleToTurn*1800);
    }
    public void launch(){
        currentPos = follower.getPose();
        double distance = currentPos.distanceFrom(goal);
        double power = ((1.15*distance) + 452.32); //converted to inches to match pedro
        motor1.setVelocity(power);
        motor2.setVelocity(power);
    }
}
