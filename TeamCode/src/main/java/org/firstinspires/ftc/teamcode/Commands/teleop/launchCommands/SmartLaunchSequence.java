package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.OTOsSmartLaunch;


public class SmartLaunchSequence extends SequentialCommandGroup {
    WaitCommand wait = new WaitCommand(1500);

    public SmartLaunchSequence(OTOsSmartLaunch launchSubsystem){
        addCommands(
                new SmartTurnCommand(launchSubsystem),
                new SmartCarrotCommand(launchSubsystem),
                wait,
                new SmartLaunchCommand(launchSubsystem)
        );
    }

}
