package org.firstinspires.ftc.teamcode.commands.teleop.Auto;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class autoStateMachineTelmetry extends CommandBase {
    private Telemetry telemetry;
    private String State;


    public autoStateMachineTelmetry(String State){
        this.telemetry = telemetry;
        this.State = State;
    }
    public void execute(){
        telemetry.addData("State", State);
        telemetry.update();
    }


}
