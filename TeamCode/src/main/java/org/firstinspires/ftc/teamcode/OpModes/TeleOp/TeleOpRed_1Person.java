package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group = "1 Person Op Modes")
public class TeleOpRed_1Person extends TeleOpBase_1Person{
    @Override
    public Boolean redAlliance() {
        return true;
    }
}
