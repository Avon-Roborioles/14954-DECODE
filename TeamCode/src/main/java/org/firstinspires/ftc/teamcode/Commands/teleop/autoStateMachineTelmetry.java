package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class autoStateMachineTelmetry extends CommandBase {
    private Telemetry telemetry;
    private String string;


    public autoStateMachineTelmetry(String State){

    }
    public void execute(){
        telemetry.addData("State", string);
        telemetry.update();
    }


}
