package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands.OTOsSmartLaunch;

import java.util.concurrent.TimeUnit;

public class SmartLaunchCommand extends CommandBase {
    private OTOsSmartLaunch launchSubsystem;

    public SmartLaunchCommand(OTOsSmartLaunch launchSubsystem){
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }
    public void initialize(){
        launchSubsystem.launch();
    }
}
