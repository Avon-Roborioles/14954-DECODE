package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveSubsystem extends SubsystemBase {
  private MecanumDrive drive;
  private DcMotor frontLeft, frontRight, backLeft, backRight;
  private Motor fLeft, fRight, bLeft, bRight;
  private Telemetry telemetry;
  public DriveSubsystem(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight, Telemetry telemetry){
//      frontRight.setInverted(true);
//      backRight.setInverted(true);
      this.frontLeft = frontLeft;
      this.frontRight = frontRight;
      this.backLeft = backLeft;
      this.backRight = backRight;
      this.telemetry = telemetry;
  }
    public DriveSubsystem(Motor fLeft, Motor fRight, Motor bLeft, Motor bRight, Telemetry telemetry){
//      frontRight.setInverted(true);
//      backRight.setInverted(true);
        this.fLeft = fLeft;
        this.fRight = fRight;
        this.bLeft = bLeft;
        this.bRight = bRight;
        this.telemetry = telemetry;
    }
  public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed, double gyroAngle, boolean fieldCentric){
      if (fieldCentric) {
          drive.driveFieldCentric(strafeSpeed,forwardSpeed,turnSpeed,gyroAngle);
      } else {
          drive.driveRobotCentric(strafeSpeed,forwardSpeed,turnSpeed);
      }
  }
    private double applyDeadband(double value) {
        return (Math.abs(value) > 0.05) ? value : 0.0;
    }

    public void getDriveTelemetry(){}

}
