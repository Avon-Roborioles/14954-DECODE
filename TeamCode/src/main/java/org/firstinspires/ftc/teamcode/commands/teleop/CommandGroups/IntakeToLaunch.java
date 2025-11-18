package org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.LaunchCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.launch.StopMotor;

public class IntakeToLaunch extends SequentialCommandGroup {
    private LaunchSubsystem launch;
    private DistanceSubsystem distance;
    private IntakeSubsystem intake;

    public IntakeToLaunch(LaunchSubsystem launch, DistanceSubsystem distance, IntakeSubsystem intake){
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
