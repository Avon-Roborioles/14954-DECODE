package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group = "1 Person Op Modes")
public class TeleOpBlue_1Person extends TeleOpBase_1Person{
    @Override
    public Boolean redAlliance() {
        return false;
    }
}
