package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.teleop.LightCommands.greenLightCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.LightCommands.lightOffCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.LightCommands.yellowLightCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeFinal;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeOnlyCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeSide;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.PassToCenter;
import org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands.PassToOtherSide;
import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class TeleOpIntakeCommand extends SequentialCommandGroup {

    public TeleOpIntakeCommand(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem, LightSubsystem lightSubsystem) {
        addCommands(
               new lightOffCommand(lightSubsystem),
                new IntakeStopServoCommand(intakeSubsystem),
                new IntakeOnlyCommand(intakeSubsystem, distanceSubsystem),

                new PassToOtherSide(intakeSubsystem, distanceSubsystem, lightSubsystem),
                new IntakeSide(intakeSubsystem,distanceSubsystem),
                new PassToCenter(intakeSubsystem,distanceSubsystem,lightSubsystem),
                new IntakeFinal(intakeSubsystem, distanceSubsystem),
                new greenLightCommand(lightSubsystem)
        );

    }


}