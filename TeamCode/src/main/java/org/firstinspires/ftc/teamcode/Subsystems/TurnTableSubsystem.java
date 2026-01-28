package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity.TAG;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class TurnTableSubsystem extends SubsystemBase {
    private Servo turntable;
    private Limelight3A limelight3A;
    private IMU imu;
    private LLResult result;
    private double  newPos;
    private Pose3D botpose;
    // It's better to manage the servo position within the follow method.
    // private double servoPos = 0.08;

    // Define the limits for your servo
    private static final double MIN_POS = 0.67;
    private static final double MAX_POS = 1;
    private double angleOffset = 201.72+3.78;
    private double pos = 0.3;
    // Proportional gain for turning. Tune this value.
    private static final double Kp = -0.0002;
    private static final double MANUAL_SPEED_MULTIPLIER = 0.003;

    //0.0

    public TurnTableSubsystem(Servo turntable) {
        this.turntable = turntable;
    }


    public void AutoBackSetPoints(boolean redAlliance){
        if (redAlliance){
            turntable.setPosition(0.81);
        } else if (!redAlliance){
            turntable.setPosition(0.8525);
        }
    }
    public void BackSetPoints(boolean redAlliance){
        if (redAlliance){
            turntable.setPosition(0.795);
        } else if (!redAlliance){
            turntable.setPosition(0.93);
        }
    }
    public void BackMiddleSetPoints(boolean redAlliance){
        if (redAlliance){
            turntable.setPosition(0.81);
        } else if (!redAlliance){
            turntable.setPosition(0.915);
        }
    }
    public void closeBackSetPoints(boolean redAlliance){
        if (redAlliance){
            turntable.setPosition(0.82);
        } else if (!redAlliance){
            turntable.setPosition(0.895);
        }
    }
    public void FrontSetPoints(boolean redAlliance){
//        if (redAlliance){
//
//            turntable.setPosition(0.65);
//        } else if (!redAlliance){
//            turntable.setPosition(1);
//        }
    }

    public void moveManual(double inputSpeed) {
        // Get the current position
        double currentPos = turntable.getPosition();

        // Calculate new position: current + (joystick_value * speed_factor)
        // If inputSpeed is 0, the position won't change.
        double targetPos = pos + (inputSpeed * MANUAL_SPEED_MULTIPLIER);

        // Ensure we don't go past the physical limits (0.0 to 1.0 are standard servo limits)
        // If you have specific limits like 0.2 to 0.8, change the numbers below.
        if (targetPos > MAX_POS) {
            targetPos = MAX_POS;
        } else if (targetPos < MIN_POS) {
            targetPos = MIN_POS;
        }

        turntable.setPosition(targetPos);

        // Update 'pos' variable to track this new manual position
        pos = targetPos;
    }
    public void limelightFollow(double tx) {
        // Only adjust if a target is visible (tx is non-zero)
        if (tx != 0) {
            // Read the current servo position
            double currentPos = turntable.getPosition();

            // Calculate the adjustment. The sign depends on your servo's orientation.
            // You may need to change '-' to '+'
            newPos = currentPos + (Kp * tx);


            // Clamp the new position to stay within the servo's safe range
            if (newPos > MAX_POS) {
                newPos = MAX_POS;
            } else if (newPos < MIN_POS) {
                newPos = MIN_POS;
            } else{
                turntable.setPosition(newPos);
            }
            turntable.setPosition(newPos);

        }
        // If tx is 0 (no target), the servo will hold its last position.
    }


    public void getTelemetry(Telemetry telemetry){
        double currentPos = turntable.getPosition();
        telemetry.addData("Commanded Turntable Position", newPos);
        telemetry.addData("turntable position", currentPos);
    }

    public void compTelemetry(Telemetry telemetry){
        double currentPos = turntable.getPosition();
        telemetry.addData("turntable position", currentPos);

    }
}

