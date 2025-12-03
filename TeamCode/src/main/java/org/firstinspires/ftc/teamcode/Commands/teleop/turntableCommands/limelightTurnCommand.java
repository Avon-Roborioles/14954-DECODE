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
        addRequirements(limelightSubsystem, turnTableSubsystem);
    }

    public void initialize(){
        if (redAlliance){
            limelightSubsystem.setPipeline(1);
        } else {
            limelightSubsystem.setPipeline(2);
        }
        limelightSubsystem.start();
    }

    public void execute(){
        double tx = limelightSubsystem.getTx();


        // 1. Aim Turret
        turnTableSubsystem.limelightFollow(tx);

        // 2. Calculate and Set Hood Angle

        // 4. If the launcher is ALREADY toggled on, update the velocity live.
        // We check isMotorRunning() instead of getPower() because setVelocity
        // doesn't always update getPower() immediately in the way you expect.
        if (launchSubsystem.isMotorRunning()){
            launchSubsystem.runMotor();
        }

    }

    public boolean isFinished(){
        return false;
    }
}

