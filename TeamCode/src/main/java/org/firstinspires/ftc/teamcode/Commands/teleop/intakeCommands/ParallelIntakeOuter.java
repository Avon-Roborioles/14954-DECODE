package org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class ParallelIntakeOuter extends ParallelCommandGroup {

    public ParallelIntakeOuter(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem){
        addCommands(
          new IntakeFrontOnly(intakeSubsystem, distanceSubsystem),
          new IntakeBackOnly(intakeSubsystem, distanceSubsystem)
        );
    }
}
