package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeFinal;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeOnlyCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeSide;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.PassToCenter;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.PassToOtherSide;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class TeleOpIntakeCommand extends SequentialCommandGroup {

    public TeleOpIntakeCommand(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem) {
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