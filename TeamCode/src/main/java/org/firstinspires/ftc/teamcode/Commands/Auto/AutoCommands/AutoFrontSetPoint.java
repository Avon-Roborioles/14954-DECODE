package org.firstinspires.ftc.teamcode.commands.Auto.AutoCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

import java.util.concurrent.TimeUnit;

public class AutoFrontSetPoint extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private boolean redAlliance;
    private Timing.Timer timer;

    public AutoFrontSetPoint(LaunchSubsystem launchSubsystem, TurnTableSubsystem turnTableSubsystem, boolean redAlliance){
        this.launchSubsystem = launchSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(launchSubsystem);
    }

    public void initialize(){
        timer = new Timing.Timer(2000, TimeUnit.MILLISECONDS);
        timer.start();
        launchSubsystem.frontSetPoint();
//        turnTableSubsystem.FrontSetPoints(redAlliance);
    }
    public boolean isFinished(){
        return timer.done();
    }
}
