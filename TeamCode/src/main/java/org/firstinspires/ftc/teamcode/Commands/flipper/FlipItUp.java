package org.firstinspires.ftc.teamcode.commands.flipper;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.FlipperSubsystem;

public class FlipItUp extends CommandBase {
    private FlipperSubsystem subsystem;

    public FlipItUp(FlipperSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.FlipperUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
