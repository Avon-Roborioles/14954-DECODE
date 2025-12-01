package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;

public class frontSetPointCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;

    public frontSetPointCommand(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    public void initialize(){
        launchSubsystem.frontSetPoint();
    }


}