package org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class PassToOtherSide extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private boolean isFront;

    public PassToOtherSide(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
    }
    public void initialize(){
      isFront = distanceSubsystem.isIntakingFromFront();

    }

    public void execute() {
        if (isFront){
            intakeSubsystem.IntakeFrontToBack();
        } else if (!isFront){
            intakeSubsystem.IntakeBackToFront();
        }
    }

    public boolean isFinished(){

        if (isFront){
            return distanceSubsystem.checkBack();
        } else if (!isFront){
           return distanceSubsystem.checkFront();
        }
        return false;
    }
}
