package org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands;

import static java.lang.Math.PI;

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
    private Servo carrot;
    private double chassisAngle, servoAngle;
    private Pose currentPos, goal;
    private boolean isRed;


    public OTOsSmartLaunch(Servo turnservo, Follower follower, DcMotorEx motor1, DcMotorEx motor2, Servo carrot, boolean isRed){
        this.turnservo = turnservo;
        this.follower = follower;
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.carrot = carrot;
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
        double xd = goal.getX() - x;
        double yd = goal.getY() - y;
        double chassisAngle = currentPos.getHeading();
        //vectors by components
        double angleFromChassisToGoal = Math.tan(yd/xd);
        // 5 rotations = 1800 degrees = 10pi radians
        // .832 is when the servo faces pi/2 on the coordinate field
        double angleToTurn = chassisAngle - angleFromChassisToGoal;
        if(isRed) {
            turnservo.setPosition(0.832 - (angleToTurn / (10 * PI)));
        } else {
            turnservo.setPosition(0.832 + (angleToTurn / (10 * PI)));
        }
    }
    public void launch(){
        currentPos = follower.getPose();
        double distance = currentPos.distanceFrom(goal);
        double power = (2.9213*(distance) + 1148.9);
        motor1.setVelocity(power);
        motor2.setVelocity(power);
    }
    public void setCarrot(){
        currentPos = follower.getPose();
        double distance = currentPos.distanceFrom(goal);
        double newCarrotPos = (-.000001*Math.pow(distance, 2)) - (.0004*distance) + 0.2189;
        carrot.setPosition(newCarrotPos);
    }
    public void changeMode(){
        if(isRed){
            isRed = false;
        } else {
            isRed = true;
        }
    }
    public boolean getMode(){
        return isRed;
    }
}
