package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.OTOsSmartLaunch;


public class SmartLaunchSequence extends SequentialCommandGroup {
    WaitCommand wait = new WaitCommand(1500);

    public SmartLaunchSequence(OTOsSmartLaunch launchSubsystem){
        addCommands(
                new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.SmartTurnCommand(launchSubsystem),
                new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.SmartCarrotCommand(launchSubsystem),
                wait,
                new org.firstinspires.ftc.teamcode.commands.teleop.launchCommands.SmartLaunchCommand(launchSubsystem)
        );
    }

}
