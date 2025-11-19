package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity.TAG;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class TurnTableSubsystem extends SubsystemBase {
    private Servo turntable;
    private double newPos;
    // It's better to manage the servo position within the follow method.
    // private double servoPos = 0.08;

    // Define the limits for your servo
    private static final double MIN_POS = 0.40;
    private static final double MAX_POS = 0.70;
    // Proportional gain for turning. Tune this value.
    private static final double Kp = 0.00006;

    public TurnTableSubsystem(Servo turntable) {
        this.turntable = turntable;
    }

    public void limelightFollow(double tx) {
        // Only adjust if a target is visible (tx is non-zero)
        if (tx != 0) {
            // Read the current servo position
            double currentPos = turntable.getPosition();
            Log.e("currentPos","current:" + currentPos );
            // Calculate the adjustment. The sign depends on your servo's orientation.
            // You may need to change '-' to '+'
             newPos -= (Kp * tx);

             Log.e("newpos", "newpos:" + newPos );

            // Clamp the new position to stay within the servo's safe range
            if (newPos > MAX_POS) {
                newPos = MAX_POS;
            } else if (newPos < MIN_POS) {
                newPos = MIN_POS;
            } else{
                turntable.setPosition(newPos);
            }


        }
        // If tx is 0 (no target), the servo will hold its last position.
    }

    // other methods from your class...
}

