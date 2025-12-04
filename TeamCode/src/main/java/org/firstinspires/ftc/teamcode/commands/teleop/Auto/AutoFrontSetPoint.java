package org.firstinspires.ftc.teamcode.commands.teleop.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

import java.util.concurrent.TimeUnit;

public class AutoFrontSetPoint extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private Timing.Timer timer;

    public AutoFrontSetPoint(LaunchSubsystem launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    public void initialize(){
        timer = new Timing.Timer(2000, TimeUnit.MILLISECONDS);
        timer.start();
        launchSubsystem.frontSetPoint();
    }
    public boolean isFinished(){
        return timer.done();
    }
}
