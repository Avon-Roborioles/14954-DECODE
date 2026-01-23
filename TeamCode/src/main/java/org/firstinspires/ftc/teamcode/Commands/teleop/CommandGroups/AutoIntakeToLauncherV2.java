package org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class AutoIntakeToLauncherV2 extends SequentialCommandGroup {
    public AutoIntakeToLauncherV2(DistanceSubsystem distanceSubsystem,
                                IntakeSubsystem intakeSubsystem, LaunchSubsystem launchSubsystem, Telemetry telemetry) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();

//        SequentialCommandGroup intakeSequence = new SequentialCommandGroup();
        // to add the motors
        // FRONT ONLY

        if (f && m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"All 3"),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand(intakeSubsystem),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeToLauncher(intakeSubsystem)
//

//                    new IntakeToLauncher(intakeSubsystem)
            );
        }
        else if (f && !m && !b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Front Only"),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeFrontToCenter(intakeSubsystem)
            );

            // MIDDLE ONLY
        } else if (!f && m && !b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Middle Only"),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand(intakeSubsystem),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeToLauncher(intakeSubsystem)
            );

            // BACK ONLY
        } else if (!f && !m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Back Only"),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand(intakeSubsystem),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeBackToCenter(intakeSubsystem, distanceSubsystem)
            );

            // FRONT + MIDDLE
        } else if (f && m && !b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Front + Middle"),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand(intakeSubsystem),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeToLauncher(intakeSubsystem)
            );

            // MIDDLE + BACK
        } else if (!f && m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Middle and Back "),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand(intakeSubsystem),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeToLauncher(intakeSubsystem)
            );

            // FRONT + BACK
        } else if (f && !m && b) {
            addCommands(
//                    new autoStateMachineTelmetry(telemetry,"Front and Back"),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand(intakeSubsystem),
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeBackToCenter(intakeSubsystem, distanceSubsystem)

            );

            // ALL 3
        } else if (!f && !m && !b) {
            addCommands(
                    new org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand(intakeSubsystem)
            );
        }
    }
}
