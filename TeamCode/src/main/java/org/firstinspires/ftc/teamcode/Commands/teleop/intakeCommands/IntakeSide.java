package org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeSide extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private boolean isFront;

    public IntakeSide(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;

    }
    public void initialize() {
        isFront = distanceSubsystem.isIntakingFromFront();
    }

    public void execute() {


        if (isFront){
            intakeSubsystem.IntakeFrontToCenter();
        } else if (!isFront){
            intakeSubsystem.IntakeBackToCenter();
        }
    }

    public boolean isFinished() {
        boolean f = distanceSubsystem.checkFront();
        boolean b = distanceSubsystem.checkBack();
        if (isFront) {
            return b;
        } else if (!isFront) {
            return f;
        }
        return false;
    }
}
