package org.firstinspires.ftc.teamcode.commands.flipper;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.FlipperSubsystem;

public class FlipItDown extends CommandBase {
    private FlipperSubsystem subsystem;

    public FlipItDown(FlipperSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.FlipperDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
