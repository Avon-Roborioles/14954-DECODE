package org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class PassToOtherSide extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private LightSubsystem lightSubsystem;
    private boolean isFront;

    public PassToOtherSide(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem, LightSubsystem lightSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
        this.lightSubsystem = lightSubsystem;
    }
    public void initialize(){
      isFront = distanceSubsystem.isIntakingFromFront();

    }

    public void execute() {
        if (isFront){
            intakeSubsystem.IntakeFrontToBack();
            lightSubsystem.lightOrange();
        } else if (!isFront){
            intakeSubsystem.IntakeBackToFront();
            lightSubsystem.lightOrange();
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
    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stopAll();
    }
}
