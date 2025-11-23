package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class RunMotor extends CommandBase {
    private LaunchSubsystem subsystem;

    public RunMotor(LaunchSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    public void execute() {
        subsystem.runMotor();
    }

    public boolean isFinished() {
        return false;
    }
}
