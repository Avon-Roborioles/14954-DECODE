package org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.LaunchCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.StopMotor;

import java.util.concurrent.TimeUnit;

public class IntakeToLaunch extends SequentialCommandGroup {
    private LaunchSubsystem launch;
    private DistanceSubsystem distance;
    private IntakeServoSubsystem intake;

    public IntakeToLaunch(LaunchSubsystem launch, DistanceSubsystem distance, IntakeServoSubsystem intake){
        this.launch = launch;
        this.distance = distance;
        this.intake = intake;


        addCommands(
                new LaunchCommand(launch),
                new IntakeToLauncher(intake),
                new StopMotor(launch)
        );
    }




}
