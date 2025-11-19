package org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenterAndUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;

public class DistanceIntakeCommand extends SequentialCommandGroup {

    public DistanceIntakeCommand(DistanceSubsystem distanceSubsystem,
                                 IntakeSubsystem intakeSubsystem) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();

        // FRONT ONLY
        if (f && !m && !b) {
            addCommands(
                    new IntakeFrontToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // MIDDLE ONLY
        } else if (!f && m && !b) {
            addCommands(
                    new IntakeToLauncher(intakeSubsystem)
            );

            // BACK ONLY
        } else if (!f && !m && b) {
            addCommands(
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // FRONT + MIDDLE
        } else if (f && m && !b) {
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeFrontToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // MIDDLE + BACK
        } else if (!f && m && b) {
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // FRONT + BACK
        } else if (f && !m && b) {
            addCommands(
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );

            // ALL 3
        } else if (f && m && b) {
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem)
            );
        }
    }
}
