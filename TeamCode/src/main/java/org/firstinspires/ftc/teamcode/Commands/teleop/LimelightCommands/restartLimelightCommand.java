package org.firstinspires.ftc.teamcode.Commands.teleop.LimelightCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;

public class restartLimelightCommand extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private boolean redAlliance;

    public restartLimelightCommand(LimeLightSubsystem limelightSubsystem,  boolean redAlliance){
        this.limelightSubsystem = limelightSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(limelightSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.stop();
        if (redAlliance){
            limelightSubsystem.setPipeline(1);
        } else {
            limelightSubsystem.setPipeline(2);
        }

        limelightSubsystem.start();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
