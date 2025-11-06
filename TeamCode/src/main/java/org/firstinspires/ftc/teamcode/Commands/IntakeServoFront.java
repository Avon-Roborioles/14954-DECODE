package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class IntakeServoFront extends CommandBase {
    private IntakeServoSubsystem subsystem;
    public IntakeServoFront(IntakeServoSubsystem subsystem ){
        this.subsystem = subsystem;
addRequirements(subsystem);
    }
    @Override
    public void execute() {
        subsystem.runServoF();
    }
}
