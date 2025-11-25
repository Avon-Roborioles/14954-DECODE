package org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class limelightTurnCommand extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private LaunchSubsystem launchSubsystem;
    private boolean redAlliance = false;


    public limelightTurnCommand(LimeLightSubsystem limelightSubsystem, TurnTableSubsystem turnTableSubsystem, LaunchSubsystem launchSubsystem, boolean redAlliance){
        this.limelightSubsystem = limelightSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        this.launchSubsystem = launchSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(limelightSubsystem, turnTableSubsystem, launchSubsystem);
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
        double distance = limelightSubsystem.getDistance();
        double v = launchSubsystem.distanceToSpeed(distance);
        double angle = launchSubsystem.distanceToHoodAngle(distance);

        turnTableSubsystem.limelightFollow(tx);
        launchSubsystem.setLaunchAngle(angle);


    }
    public boolean isFinished(){
        return false;
    }

}
