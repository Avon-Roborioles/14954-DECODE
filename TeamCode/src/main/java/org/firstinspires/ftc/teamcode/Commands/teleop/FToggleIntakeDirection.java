package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class FToggleIntakeDirection extends CommandBase {
    private IntakeServoSubsystem subsystem;
    public FToggleIntakeDirection(IntakeServoSubsystem subsystem ){
        this.subsystem = subsystem;
addRequirements(subsystem);
    }
    @Override
    public void execute() {
        subsystem.toggleFrontDirection();
    }
}
