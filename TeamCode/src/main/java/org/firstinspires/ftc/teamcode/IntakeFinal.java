package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeFinal extends CommandBase {
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private boolean isFront;

    public IntakeFinal (IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
    }

    public void initialize() {
        isFront = distanceSubsystem.isIntakingFromFront();
    }
    public void execute() {


        if (isFront){
            intakeSubsystem.IntakeFrontOnly();
        } else if (!isFront){
            intakeSubsystem.IntakeBackOnly();
        }
    }
    public boolean isFinished() {
        boolean f = distanceSubsystem.checkFront();
        boolean b = distanceSubsystem.checkBack();
        if (isFront) {
            return f;
        } else if (!isFront) {
            return b;
        }
        return false;
    }
    public void end(boolean interrupted){
        intakeSubsystem.stopAll();
    }
}
