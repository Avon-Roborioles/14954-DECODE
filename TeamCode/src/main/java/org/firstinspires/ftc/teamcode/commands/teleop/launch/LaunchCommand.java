package org.firstinspires.ftc.teamcode.commands.teleop.launch;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

import java.util.concurrent.TimeUnit;

public class LaunchCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private double power;
    private Timing.Timer timer = new Timing.Timer(2, TimeUnit.SECONDS);
    public LaunchCommand(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        power = launchSubsystem.getPower();
        addRequirements(launchSubsystem);
    }
    public LaunchCommand(LaunchSubsystem launchSubsystem, double power){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
        launchSubsystem.setLaunchPower(power);
    }
    @Override
    public void execute(){
        launchSubsystem.runMotor();
        timer.start();
    }

    public boolean isFinished(){
        return timer.done();
    }
}
