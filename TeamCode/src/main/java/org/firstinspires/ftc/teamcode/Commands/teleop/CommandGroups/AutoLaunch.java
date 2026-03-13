package org.firstinspires.ftc.teamcode.Commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Auto.AutoBackToCenter;
import org.firstinspires.ftc.teamcode.Commands.Auto.AutoFrontToCenter;
import org.firstinspires.ftc.teamcode.Commands.Auto.AutoIntakeToLauncher;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.CheckLaunch;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeStopServoCommand;

public class AutoLaunch extends SequentialCommandGroup {
    public AutoLaunch(DistanceSubsystem distanceSubsystem,
                      IntakeSubsystem intakeSubsystem,LaunchSubsystem launchSubsystem ,LightSubsystem lightSubsystem, Telemetry telemetry) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();

        addCommands(

                new IntakeStopServoCommand(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
//                new IntakeStopServoCommand(intakeSubsystem),
                new AutoBackToCenter(intakeSubsystem),
//                new IntakeStopServoCommand(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
//                new IntakeStopServoCommand(intakeSubsystem),
                new AutoFrontToCenter(intakeSubsystem),
//                new IntakeStopServoCommand(intakeSubsystem),
                new AutoIntakeToLauncher(intakeSubsystem),
//                new CheckLaunch(distanceSubsystem, lightSubsystem),
                new IntakeStopServoCommand(intakeSubsystem)
//                new WaitCommand(1000)
        );


    }
}