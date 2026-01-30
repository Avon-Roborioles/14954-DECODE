package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class midSetPointCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private TurnTableSubsystem turnTableSubsystem;

    public midSetPointCommand(LaunchSubsystem launchSubsystem, TurnTableSubsystem turnTableSubsystem){
        this.launchSubsystem = launchSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        addRequirements(launchSubsystem, turnTableSubsystem);
    }

    public void initialize(){
        launchSubsystem.midSetPoint();
        turnTableSubsystem.FrontSetPoint();
    }
}
