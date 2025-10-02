package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MotorSubsystem extends SubsystemBase {
    private MecanumDrive drive;
    private Motor frontLeft, frontRight, backLeft, backRight;
    private Telemetry telemetry;
    public MotorSubsystem(Motor frontLeft, Motor frontRight, Motor backLeft, Motor backRight){
          backLeft.setInverted(true);
          backRight.setInverted(true);
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.drive = new MecanumDrive(frontLeft,frontRight,backLeft,backRight);
    }
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed){

            drive.driveRobotCentric(strafeSpeed,forwardSpeed,turnSpeed);
        }
    }





