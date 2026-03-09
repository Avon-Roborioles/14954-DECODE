package org.firstinspires.ftc.teamcode.Commands.teleop.DriveCommands;

import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;

public class autoSetHeading extends CommandBase {
    private AutoDriveSubsystem autoDriveSubsystem;
    private boolean redAlliance;
    public autoSetHeading(AutoDriveSubsystem autoDriveSubsystem,boolean redAlliance){
        this.autoDriveSubsystem = autoDriveSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(autoDriveSubsystem);
    }

    @Override
    public void initialize() {
        if (redAlliance){
            autoDriveSubsystem.setStartingPose(new Pose(0, 0, 0));
            autoDriveSubsystem.setPose(new Pose(0, 0, 0));
        } else {
            autoDriveSubsystem.setStartingPose(new Pose(0, 0, PI));
            autoDriveSubsystem.setPose(new Pose(0, 0, PI));
        }
    }
}
