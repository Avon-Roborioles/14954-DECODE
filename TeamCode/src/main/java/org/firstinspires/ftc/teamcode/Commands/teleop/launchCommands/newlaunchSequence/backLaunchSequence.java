package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.newlaunchSequence;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class backLaunchSequence extends CommandBase {
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;

    public backLaunchSequence(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem){
        this.distanceSubsystem = distanceSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem, distanceSubsystem);
    }


    @Override
    public void execute() {
        intakeSubsystem.backSideLaunch();
    }

    public boolean isFinished(){
        return distanceSubsystem.backCenterIsGone();
    }
}
