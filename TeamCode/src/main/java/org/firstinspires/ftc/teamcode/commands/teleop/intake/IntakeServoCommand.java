package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class IntakeServoCommand extends CommandBase {
    private IntakeServoSubsystem subsystem;
    public IntakeServoCommand(IntakeServoSubsystem subsystem ){
        this.subsystem = subsystem;
addRequirements(subsystem);
    }
    @Override
    public void execute() {
        subsystem.runServo();
    }
}
