package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class IntakeServoSubsystem extends SubsystemBase{
        private CRServo fServo1;
    private CRServo fServo2;
    private CRServo bServo1;
    private CRServo bServo2;
    private double power;

        public IntakeServoSubsystem(CRServo fServo1, CRServo fServo2, CRServo bServo1, CRServo bServo2){
            this.fServo1 = fServo1;
            this.fServo2 = fServo2;
            this.bServo1 = bServo1;
            this.bServo2 = bServo2;
            power = 1;
        }
        public void toggleFrontDirection(){
            if(power>0){
                power = 0;
            } else {
                power = 1;
            }
        }
    public void toggleBackDirection(){
        if(power<0){
            power = 0;
        } else {
            power = -1;
        }
    }
        public void runServoF(){
            fServo1.setPower(power);
            fServo2.setPower(power);
        }
        public void runServoB(){
            bServo1.setPower(power);
            bServo2.setPower(power);
        }

        public void stopAll(){
            fServo1.setPower(0);
            fServo2.setPower(0);
            bServo1.setPower(0);
            bServo2.setPower(0);
        }

}
