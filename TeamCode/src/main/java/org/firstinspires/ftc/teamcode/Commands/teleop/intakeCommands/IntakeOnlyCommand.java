package org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeOnlyCommand extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private DistanceSubsystem distanceSubsystem;

    public IntakeOnlyCommand(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;

    }

    public void initialize() {
        distanceSubsystem.setIntakeFromFront(null);
    }

    public void execute() {
        intakeSubsystem.intakeOnly();
    }

    public boolean isFinished() {
        boolean f = distanceSubsystem.checkFront();
        boolean b = distanceSubsystem.checkBack();
        if (f || b) {
            distanceSubsystem.setIntakeFromFront(f);
            return true;
        }
        return false;
    }
    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stopAll();
    }
}


