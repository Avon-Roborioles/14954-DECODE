package org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeFrontToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeFrontToCenterAndUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeToLauncher;

public class AutoIntakeToLauncher extends SequentialCommandGroup {
//public class DistanceIntakeCommand extends SequentialCommandGroup {
    public AutoIntakeToLauncher(DistanceSubsystem distanceSubsystem,
                                IntakeSubsystem intakeSubsystem, LaunchSubsystem launchSubsystem, Telemetry telemetry) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();

//        SequentialCommandGroup intakeSequence = new SequentialCommandGroup();
        // to add the motors
        // FRONT ONLY
        if (f && !m && !b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Front Only"),
                    new IntakeFrontToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // MIDDLE ONLY
        } else if (!f && m && !b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Middle Only"),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // BACK ONLY
        } else if (!f && !m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Back Only"),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // FRONT + MIDDLE
        } else if (f && m && !b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Front + Middle"),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeFrontToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // MIDDLE + BACK
        } else if (!f && m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Middle and Back "),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // FRONT + BACK
        } else if (f && !m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Front and Back"),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // ALL 3
        } else if (f && m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"All 3"),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem),
//
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem)//,
//                    new IntakeToLauncher(intakeSubsystem)
            );
        } else if (!f && !m && !b) {
            addCommands(
            new IntakeStopServoCommand(intakeSubsystem)
            );
        }
    }
}
