package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

public class IntakeServoSubsystem extends SubsystemBase{
        private CRServo IntakeServo1;
        private CRServo IntakeServo2;

        private CRServo PassthroughServo1;
        private CRServo PassthroughServo2;


        public IntakeServoSubsystem(CRServo IntakeServo1, CRServo IntakeServo2, CRServo PassthroughServo1, CRServo PassthoughServo2){
            this.IntakeServo1 = IntakeServo1;
            this.IntakeServo2 = IntakeServo2;
        }
        public void runServo(){
            IntakeServo1.setPower(-1);
            IntakeServo2.setPower(1);
        }
        public void stopServo(){
            IntakeServo1.setPower(0);
            IntakeServo2.setPower(0);
        }



}
