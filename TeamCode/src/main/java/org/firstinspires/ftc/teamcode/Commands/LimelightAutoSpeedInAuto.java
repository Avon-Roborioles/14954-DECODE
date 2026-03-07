package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.OpModes.Auto.AutoBase;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

import java.util.concurrent.TimeUnit;

public class LimelightAutoSpeedInAuto extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private LaunchSubsystem launchSubsystem;
    private LightSubsystem lightSubsystem;
    private boolean redAlliance = false;
    private Timing.Timer timer;
    public LimelightAutoSpeedInAuto(LimeLightSubsystem limelightSubsystem, TurnTableSubsystem turnTableSubsystem, LaunchSubsystem launchSubsystem, LightSubsystem lightSubsystem , boolean redAlliance){
        this.limelightSubsystem = limelightSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        this.launchSubsystem = launchSubsystem;
        this.lightSubsystem = lightSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(limelightSubsystem,turnTableSubsystem);
    }

    public void initialize(){
        if (redAlliance){
            limelightSubsystem.setPipeline(1);
        } else {
            limelightSubsystem.setPipeline(2);
        }

        limelightSubsystem.start();
        double tx = limelightSubsystem.getTx();
        double distance = limelightSubsystem.getDistance();
        boolean failed = limelightSubsystem.isLimeLightCooked();
        timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);
        timer.start();


        double result = Math.toRadians(tx);


        // 1. Aim Turret
        turnTableSubsystem.limelightFollow(tx, redAlliance);

        // 2. Calculate and Set Hood Angle

//        launchSubsystem.distanceToRPM(distance);
    }




//        if (launchSubsystem.isMotorRunning()){
//            launchSubsystem.runMotor();
//        }


    public void execute() {
        double tx = limelightSubsystem.getTx();
        double distance = limelightSubsystem.getDistance();
        boolean failed = limelightSubsystem.isLimeLightCooked();


        double result = Math.toRadians(tx);


        // 1. Aim Turret
        turnTableSubsystem.limelightFollow(tx, redAlliance);

        // 2. Calculate and Set Hood Angle

    }

    public boolean isFinished(){
        return timer.done();
    }
}
