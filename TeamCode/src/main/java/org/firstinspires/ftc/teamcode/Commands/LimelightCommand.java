package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;

public class LimelightCommand extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private LLResult lastResult;

    public LimelightCommand(LimeLightSubsystem limelightSubsystem){
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(limelightSubsystem);
    }
    @Override
    public void execute(){
        lastResult = limelightSubsystem.readAprilTag();
        limelightSubsystem.getLimelightTelemetry();
        limelightSubsystem.setPipeline(4);
    }

    public void end(boolean interrupted){
        limelightSubsystem.setPipeline(1);
    }
    public boolean isFinished(){
        return limelightSubsystem.seeTag();
    }

}
