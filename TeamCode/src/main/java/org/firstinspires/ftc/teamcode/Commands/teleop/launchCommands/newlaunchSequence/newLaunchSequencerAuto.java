package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.newlaunchSequence;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class newLaunchSequencerAuto extends SequentialCommandGroup {
        public newLaunchSequencerAuto(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem, LightSubsystem lightSubsystem){
            addRequirements(distanceSubsystem, intakeSubsystem, lightSubsystem);

            addCommands(
                    new IntakeStopServoCommand(intakeSubsystem),
                    new middleLaunchSequence(distanceSubsystem,intakeSubsystem),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new frontLaunchSequence( distanceSubsystem, intakeSubsystem),
                    new IntakeStopServoCommand(intakeSubsystem),
                    new backLaunchSequence(distanceSubsystem,intakeSubsystem),
                    new WaitCommand(10000)
//                new CheckLaunch(distanceSubsystem,lightSubsystem,intakeSubsystem),
            );
        }

    }

