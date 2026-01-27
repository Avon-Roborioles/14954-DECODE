package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.OTOsSmartLaunch;

import java.util.concurrent.TimeUnit;

public class SmartTurnCommand extends CommandBase {
    private OTOsSmartLaunch launchSubsystem;

    public SmartTurnCommand(OTOsSmartLaunch launchSubsystem) {
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    public void initialize() {
        launchSubsystem.turnToGoal();
    }
}
