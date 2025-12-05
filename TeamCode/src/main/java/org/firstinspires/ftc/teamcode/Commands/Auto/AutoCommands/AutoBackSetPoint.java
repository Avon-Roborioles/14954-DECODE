package org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

import java.util.concurrent.TimeUnit;

public class AutoBackSetPoint extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private Timing.Timer timer;

    public AutoBackSetPoint(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    public void initialize(){
        timer = new Timing.Timer(2000, TimeUnit.MILLISECONDS);
        timer.start();
        launchSubsystem.backSetPoint();
    }
    public boolean isFinished(){
        return timer.done();
    }
}
