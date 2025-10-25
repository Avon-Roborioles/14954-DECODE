package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

public class IntakeServoSubsystem extends SubsystemBase{
        private CRServo servo;
    private CRServo servo2;

        public IntakeServoSubsystem(CRServo servo, CRServo servo2){
            this.servo = servo;
            this.servo2 = servo2;
        }
        public void runServo(){
            servo.setPower(-1);
            servo2.setPower(1);
        }
        public void inverseRun(){
            servo.setPower(1);
            servo2.setPower(-1);
        }
        public void stopServo(){
            servo.setPower(0);
            servo2.setPower(0);
        }
}
