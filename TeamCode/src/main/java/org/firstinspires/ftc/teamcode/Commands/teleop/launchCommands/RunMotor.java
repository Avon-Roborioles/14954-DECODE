package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class RunMotor extends CommandBase {
    private LaunchSubsystem subsystem;
    private LightSubsystem lightSubsystem;

    public RunMotor(LaunchSubsystem subsystem, LightSubsystem lightSubsystem){
        this.subsystem = subsystem;
        this.lightSubsystem = lightSubsystem;
        addRequirements(subsystem, lightSubsystem);
    }

    public void execute() {
        subsystem.runMotor();

        boolean ready = subsystem.isLauncherReady();

        // Only change color once when state flips
        if (ready){
            lightSubsystem.lightViolet();
        } else {
            lightSubsystem.lightAzure();
        }

    }


    public boolean isFinished() {
        return false;
    }
}
