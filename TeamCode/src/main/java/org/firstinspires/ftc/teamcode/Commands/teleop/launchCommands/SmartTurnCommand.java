package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.OTOsSmartLaunch;

import java.util.concurrent.TimeUnit;

public class SmartTurnCommand extends CommandBase {
    private OTOsSmartLaunch launchSubsystem;
    private Timing.Timer timer = new Timing.Timer(200, TimeUnit.MILLISECONDS);

    public SmartTurnCommand(OTOsSmartLaunch launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }
    public void execute(){
        timer.start();
        launchSubsystem.turnToGoal();
    }
    public boolean isFinished(){
        return timer.done();
    }
}
