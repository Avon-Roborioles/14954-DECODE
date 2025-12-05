package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class backSetPointCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private boolean redAlliance;


    public backSetPointCommand(LaunchSubsystem launchSubsystem, TurnTableSubsystem turnTableSubsystem, boolean redAlliance){
        this.launchSubsystem = launchSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(launchSubsystem, turnTableSubsystem);
    }

    public void initialize(){
        launchSubsystem.backSetPoint();
        turnTableSubsystem.BackSetPoints(redAlliance);

    }
}
