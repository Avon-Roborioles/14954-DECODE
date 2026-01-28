package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class CorrectMotorSpeedCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    public CorrectMotorSpeedCommand(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }
public void initialize(){
        launchSubsystem.correctSpeed();

        if(launchSubsystem.getTargetRPM() == 1850){
            launchSubsystem.backSetPoint(false);
        } else if (launchSubsystem.getTargetRPM() == 1800){
            launchSubsystem.closeBackSetPoint();
        }

}

}
