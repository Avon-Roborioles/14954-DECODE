package org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeFinal;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.*;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class AutoIntakeCommand extends SequentialCommandGroup {

    public AutoIntakeCommand(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem, LightSubsystem lightSubsystem) {
        addCommands(
                new IntakeStopServoCommand(intakeSubsystem),
                new IntakeOnlyCommand(intakeSubsystem, distanceSubsystem),
                new PassToOtherSide(intakeSubsystem, distanceSubsystem, lightSubsystem),
                new IntakeSide(intakeSubsystem,distanceSubsystem),
                new PassToCenter(intakeSubsystem,distanceSubsystem, lightSubsystem),
                new IntakeFinal(intakeSubsystem, distanceSubsystem),
                new WaitCommand(5000)
        );

        // CRITICAL FIX: Do NOT require the subsystem here.
        // This command is a "manager" that delegates to other commands.
        // If you require it here, scheduling the child commands will interrupt
        // and kill this command immediately (or vice versa).
    }


    }

