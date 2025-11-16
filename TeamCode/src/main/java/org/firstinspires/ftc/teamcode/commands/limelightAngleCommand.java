package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class limelightAngleCommand extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private TurnTableSubsystem turnTableSubsystem;

    public limelightAngleCommand(LimeLightSubsystem limelightSubsystem, TurnTableSubsystem turnTableSubsystem){
        this.limelightSubsystem = limelightSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        addRequirements(limelightSubsystem, turnTableSubsystem);
    }

    public void initialize(){
        limelightSubsystem.setPipeline(0);
        limelightSubsystem.start();
    }

    public void execute(){
        double tx = limelightSubsystem.getTx();
        turnTableSubsystem.limelightFollow(tx);

    }
    public boolean isFinished(){
        return false;
    }

}
