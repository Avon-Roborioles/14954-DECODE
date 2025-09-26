package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveSubsystem extends SubsystemBase {
  private MecanumDrive drive;
  private Motor frontLeft, frontRight, backLeft, backRight;
  private Telemetry telemetry;
  public DriveSubsystem(Motor frontLeft, Motor frontRight, Motor backLeft, Motor backRight, Telemetry telemetry){
      frontRight.setInverted(true);
      backRight.setInverted(true);
      this.frontLeft = frontLeft;
      this.frontRight = frontRight;
      this.backLeft = backLeft;
      this.backRight = backRight;
      this.drive = new MecanumDrive(frontLeft,frontRight,backLeft,backRight);
      this.telemetry = telemetry;
  }
  public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed, double gyroAngle, boolean fieldCentric){
      if (fieldCentric) {
          drive.driveFieldCentric(strafeSpeed,forwardSpeed,turnSpeed,gyroAngle);
      } else {
          drive.driveRobotCentric(strafeSpeed,forwardSpeed,turnSpeed);
      }
  }
  public void getDriveTelemetry(){}

}
