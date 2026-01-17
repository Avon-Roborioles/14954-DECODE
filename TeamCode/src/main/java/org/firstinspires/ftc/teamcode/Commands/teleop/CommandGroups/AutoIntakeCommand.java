package org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeFinal;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.*;

public class AutoIntakeCommand extends SequentialCommandGroup {

    public AutoIntakeCommand(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
                new IntakeStopServoCommand(intakeSubsystem),
                new IntakeOnlyCommand(intakeSubsystem, distanceSubsystem),
                new PassToOtherSide(intakeSubsystem, distanceSubsystem),
                new IntakeSide(intakeSubsystem,distanceSubsystem),
                new PassToCenter(intakeSubsystem,distanceSubsystem),
                new IntakeFinal(intakeSubsystem, distanceSubsystem)
        );

        // CRITICAL FIX: Do NOT require the subsystem here.
        // This command is a "manager" that delegates to other commands.
        // If you require it here, scheduling the child commands will interrupt
        // and kill this command immediately (or vice versa).
    }


    }

