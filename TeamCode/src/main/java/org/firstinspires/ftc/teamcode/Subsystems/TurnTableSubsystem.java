package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class TurnTableSubsystem extends SubsystemBase {
    private Servo turntable;

    public TurnTableSubsystem ( Servo turntable){
        this.turntable = turntable;
    }

    public void testmove1(){
        turntable.setPosition(0.01);
    }
    public void testmove2(){
        turntable.setPosition(0.08);
    }

}
