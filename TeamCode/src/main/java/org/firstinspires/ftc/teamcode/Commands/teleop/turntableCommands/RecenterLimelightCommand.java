package org.firstinspires.ftc.teamcode.Commands.teleop.turntableCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class RecenterLimelightCommand extends CommandBase {
    private TurnTableSubsystem turnTableSubsystem;
    private boolean redAlliance;

    public RecenterLimelightCommand(TurnTableSubsystem turnTableSubsystem, boolean redAlliance){
        this.turnTableSubsystem = turnTableSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(turnTableSubsystem);
    }

    public void initialize(){
        turnTableSubsystem.recenterLimeLight(redAlliance);
    }
    public boolean isFinished(){
        return true;
    }
}
