package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor.IntakeBackOnly;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor.IntakeFrontOnly;

public class ParallelIntakeOuter extends ParallelCommandGroup {

    public ParallelIntakeOuter(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem){
        addCommands(
          new IntakeFrontOnly(intakeSubsystem, distanceSubsystem),
          new IntakeBackOnly(intakeSubsystem, distanceSubsystem)
        );
    }
}
