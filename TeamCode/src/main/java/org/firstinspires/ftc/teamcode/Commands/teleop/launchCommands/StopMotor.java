package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class StopMotor extends CommandBase {
    private LaunchSubsystem subsystem;

    public StopMotor(LaunchSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    public void execute() {
        subsystem.stopMotor();
    }

    public boolean isFinished() {
        return true;
    }
}
