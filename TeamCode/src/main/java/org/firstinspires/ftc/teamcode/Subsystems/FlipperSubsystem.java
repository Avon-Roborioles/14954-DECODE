package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class FlipperSubsystem extends SubsystemBase {
    private Servo servo;

    public FlipperSubsystem(Servo servo) {

    this.servo = servo;
    }

    public void FlipperDown(){
        servo.setPosition(0.0);

    }
    public void FlipperUp(){
        servo.setPosition(0.15);
    }

}
