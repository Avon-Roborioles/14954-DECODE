package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoSubsystem extends SubsystemBase{
        private CRServo servo;
    private CRServo servo2;

        public ServoSubsystem (CRServo servo, CRServo servo2){
            this.servo = servo;
            this.servo2 = servo2;
        }
        public void runServo(double power){
            servo.setPower(power);
            servo2.setPower(-power);
        }
}
