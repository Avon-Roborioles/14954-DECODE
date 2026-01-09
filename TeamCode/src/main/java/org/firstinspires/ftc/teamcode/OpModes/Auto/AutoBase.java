package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public abstract class AutoBase extends CommandOpMode {
    protected Follower follower;
    protected Telemetry telemetry;
    protected IntakeSubsystem intake;
    protected LaunchSubsystem launch;
    protected DistanceSubsystem distance;
    protected TurnTableSubsystem turnTableSubsystem;
    protected AutoDriveSubsystem autoDriveSubsystem;
    protected Command MoveLaunchPreload, PrepareToGrab1, GrabSet1, MoveToMidpoint, MoveToLaunch1, PrepareToGrab2, GrabSet2, MoveToMidPoint2, MoveToLaunch2, leave;
    protected Path launchPreload, prepGrab1, grab1, midpoint, Launch1, prepGrab2, grab2, midpoint2, launch2, Leave;

    protected DcMotorEx launchMotor;
    protected Servo launchAngle;
    protected CRServo launchServo;
    // intake variables
    protected CRServo frontIntakeServo;
    protected CRServo frontPassServo;
    protected CRServo backIntakeServo;
    protected CRServo backPassServo;
    //Distance Sensor Variables
    protected DigitalChannel fSensor, mSensor, bSensor;
    // Turntable Variables
    protected Servo turnServo;

    public abstract void makeAuto();
    public abstract void buildPath();
}
