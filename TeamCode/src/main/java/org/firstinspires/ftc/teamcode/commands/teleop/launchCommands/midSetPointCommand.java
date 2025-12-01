package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class midSetPointCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;

    public midSetPointCommand(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    public void initialize(){
        launchSubsystem.midSetPoint();
    }
}
