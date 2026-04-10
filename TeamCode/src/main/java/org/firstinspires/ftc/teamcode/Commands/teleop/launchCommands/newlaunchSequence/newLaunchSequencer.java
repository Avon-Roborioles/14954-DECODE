package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.newlaunchSequence;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.CheckLaunch;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class newLaunchSequencer extends SequentialCommandGroup {
    public newLaunchSequencer(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem, LightSubsystem lightSubsystem){
        addRequirements(distanceSubsystem, intakeSubsystem, lightSubsystem);

        addCommands(
                new frontLaunchSequence( distanceSubsystem, intakeSubsystem),
                new backLaunchSequence(distanceSubsystem,intakeSubsystem),
//                new CheckLaunch(distanceSubsystem,lightSubsystem,intakeSubsystem),
                new IntakeStopServoCommand(intakeSubsystem)
        );
    }

}
