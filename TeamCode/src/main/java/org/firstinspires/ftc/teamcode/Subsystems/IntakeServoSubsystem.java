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
        public void runServo1(){
            servo.setPower(-1);
        }
        public void runServo2(){
            servo2.setPower(1);
        }
        public void inverseRun1(){
            servo.setPower(1);
        }
        public void inverseRun2(){
            servo2.setPower(-1);
        }
        public void stopServo1(){
            servo.setPower(0);
        }
        public void stopServo2(){
            servo2.setPower(0);
        }
}
