package org.firstinspires.ftc.teamcode.commands.teleop.launch;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class ToggleLaunchCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private double power;
    public ToggleLaunchCommand(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        power = launchSubsystem.getPower();
        addRequirements(launchSubsystem);
    }
    public ToggleLaunchCommand(LaunchSubsystem launchSubsystem, double power){
        this.launchSubsystem = launchSubsystem;
        this.power = power;
        addRequirements(launchSubsystem);
        launchSubsystem.setLaunchPower(power);
    }
    @Override
    public void execute(){
        if(power==0) {
            launchSubsystem.runMotor();
        } else {
            launchSubsystem.stopMotor();
        }
    }
}
