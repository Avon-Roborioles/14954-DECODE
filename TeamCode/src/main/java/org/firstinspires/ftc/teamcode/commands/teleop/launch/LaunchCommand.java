package org.firstinspires.ftc.teamcode.commands.teleop.launch;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class LaunchCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private double power;
    public LaunchCommand(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }
    public LaunchCommand(LaunchSubsystem launchSubsystem, double power){
        this.launchSubsystem = launchSubsystem;
        this.power = power;
        addRequirements(launchSubsystem);
        launchSubsystem.setLaunchPower(power);
    }
    @Override
    public void execute(){
        launchSubsystem.runMotor();
    }
}
