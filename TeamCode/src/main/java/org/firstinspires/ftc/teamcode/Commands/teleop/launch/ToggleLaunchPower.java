package org.firstinspires.ftc.teamcode.commands.teleop.launch;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class ToggleLaunchPower extends CommandBase {
    private LaunchSubsystem subsystem;
    public ToggleLaunchPower(LaunchSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }
    @Override
    public void execute(){
        if(subsystem.getPower() == 0.8){
            subsystem.setLaunchPower(0.4);
        } else {
            subsystem.setLaunchPower(0.8);
        }
    }
}
