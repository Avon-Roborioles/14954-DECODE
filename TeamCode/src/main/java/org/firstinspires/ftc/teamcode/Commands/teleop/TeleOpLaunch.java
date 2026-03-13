package org.firstinspires.ftc.teamcode.Commands.teleop;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Auto.AutoBackToCenter;
import org.firstinspires.ftc.teamcode.Commands.Auto.AutoFrontToCenter;
import org.firstinspires.ftc.teamcode.Commands.Auto.AutoIntakeToLauncher;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.CheckLaunch;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class TeleOpLaunch extends SequentialCommandGroup {
    public TeleOpLaunch(DistanceSubsystem distanceSubsystem,
                      IntakeSubsystem intakeSubsystem , LightSubsystem lightSubsystem, Telemetry telemetry) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();

        addCommands(

                new IntakeStopServoCommand(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
                new IntakeStopServoCommand(intakeSubsystem),
                new AutoBackToCenter(intakeSubsystem),
//                new IntakeStopServoCommand(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
//                new IntakeStopServoCommand(intakeSubsystem),
                new AutoFrontToCenter(intakeSubsystem),
                new IntakeStopServoCommand(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
                new IntakeStopServoCommand(intakeSubsystem),
                new CheckLaunch(distanceSubsystem, lightSubsystem, intakeSubsystem)

        );


    }
}
