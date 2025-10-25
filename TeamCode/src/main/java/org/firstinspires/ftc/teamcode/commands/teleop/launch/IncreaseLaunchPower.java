package org.firstinspires.ftc.teamcode.commands.teleop.launch;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class IncreaseLaunchPower extends CommandBase {
    private double launchPower;
    private LaunchSubsystem subsystem;
    public IncreaseLaunchPower(LaunchSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
        launchPower = subsystem.getPower();
    }
    @Override
    public void execute(){
        launchPower = launchPower+.2;
        subsystem.setLaunchPower(launchPower);
    }
}
