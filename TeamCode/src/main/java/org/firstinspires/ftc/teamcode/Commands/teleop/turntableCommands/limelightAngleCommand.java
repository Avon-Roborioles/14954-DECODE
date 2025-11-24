package org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class limelightAngleCommand extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private boolean redAlliance = false;


    public limelightAngleCommand(LimeLightSubsystem limelightSubsystem, TurnTableSubsystem turnTableSubsystem, boolean redAlliance){
        this.limelightSubsystem = limelightSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(limelightSubsystem, turnTableSubsystem);
    }

    public void initialize(){
        if (redAlliance){
            limelightSubsystem.setPipeline(1);
        }else{
            limelightSubsystem.setPipeline(2);
        }

//        limelightSubsystem.setPipeline(7);
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
