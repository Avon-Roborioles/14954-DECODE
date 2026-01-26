package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class backMiddleSetPointCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private boolean redAlliance;


    public backMiddleSetPointCommand(LaunchSubsystem launchSubsystem, TurnTableSubsystem turnTableSubsystem, boolean redAlliance){
        this.launchSubsystem = launchSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(launchSubsystem, turnTableSubsystem);
    }

    public void initialize(){
        launchSubsystem.backMiddleSetPoint();
        turnTableSubsystem.BackMiddleSetPoints(redAlliance);

    }
}
