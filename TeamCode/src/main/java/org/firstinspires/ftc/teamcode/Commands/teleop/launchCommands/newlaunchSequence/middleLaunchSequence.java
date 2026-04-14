package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.newlaunchSequence;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class middleLaunchSequence extends CommandBase {
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;

    public middleLaunchSequence(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem){
        this.distanceSubsystem = distanceSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem, distanceSubsystem);
    }




    @Override
    public void execute() {
        intakeSubsystem.TransferToLauncher();
    }

    public boolean isFinished(){
        return !distanceSubsystem.checkMiddle();
    }
}
