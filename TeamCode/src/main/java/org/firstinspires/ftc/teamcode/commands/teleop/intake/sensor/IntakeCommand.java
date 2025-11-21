package org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToFront;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToBack;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenterAndUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeOnlyCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;

public class IntakeCommand extends SequentialCommandGroup{

    //public class DistanceIntakeCommand extends SequentialCommandGroup {
    public IntakeCommand(DistanceSubsystem distanceSubsystem,
                                 IntakeSubsystem intakeSubsystem) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();
//        SequentialCommandGroup intakeSequence = new SequentialCommandGroup();
        // to add the motors
        // FRONT ONLY
        if (f && !m && !b) {
            addCommands(
                    new IntakeFrontToBack(intakeSubsystem, distanceSubsystem)
            );

            // MIDDLE ONLY
        } else if (!f && m && !b) {
            addCommands(
                    new IntakeStopServoCommand(intakeSubsystem)
            );

            // BACK ONLY
        } else if (!f && !m && b) {
            addCommands(
                    new IntakeBackToFront(intakeSubsystem, distanceSubsystem)
            );

            // FRONT + MIDDLE
        } else if (f && m && !b) {
            addCommands(
                    new IntakeFrontToBack(intakeSubsystem,distanceSubsystem)
            );

            // MIDDLE + BACK
        } else if (!f && m && b) {
            addCommands(
                    new IntakeBackToFront(intakeSubsystem, distanceSubsystem)
            );

            // FRONT + BACK
        } else if (f && !m && b) {
            addCommands(
                    new IntakeFrontToBack(intakeSubsystem, distanceSubsystem)
            );

            // ALL 3
        } else if (f && m && b) {
            addCommands(
                    new IntakeStopServoCommand(intakeSubsystem)
            );
        } else if (!f && !m && !b) {

            addCommands(
            new IntakeOnlyCommand(intakeSubsystem, distanceSubsystem)
            );
        }
    }
}
