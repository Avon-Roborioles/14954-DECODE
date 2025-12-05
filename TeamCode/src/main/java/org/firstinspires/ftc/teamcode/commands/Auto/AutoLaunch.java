package org.firstinspires.ftc.teamcode.commands.Auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeFrontToCenterAndUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands.IntakeToLauncher;

public class AutoLaunch extends SequentialCommandGroup {
    public AutoLaunch(DistanceSubsystem distanceSubsystem,
                      IntakeSubsystem intakeSubsystem, LaunchSubsystem launchSubsystem, Telemetry telemetry) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();

        addCommands(

                new IntakeStopServoCommand(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
                new AutoFrontToCenter(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
                new AutoBackToCenter(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem)

        );


    }
}