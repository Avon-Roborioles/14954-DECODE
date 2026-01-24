package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.OTOsSmartLaunch;

import java.util.concurrent.TimeUnit;

public class SmartCarrotCommand extends CommandBase {
    private OTOsSmartLaunch launchSubsystem;
    private Timing.Timer timer = new Timing.Timer(100, TimeUnit.MILLISECONDS);

    public SmartCarrotCommand(OTOsSmartLaunch launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }
    public void execute(){
        timer.start();
        launchSubsystem.setCarrot();
    }
    public boolean isFinished(){
        return timer.done();
    }
}
