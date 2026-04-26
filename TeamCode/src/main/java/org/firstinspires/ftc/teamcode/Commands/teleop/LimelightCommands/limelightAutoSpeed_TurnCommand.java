package org.firstinspires.ftc.teamcode.Commands.teleop.LimelightCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

import java.util.concurrent.TimeUnit;

public class limelightAutoSpeed_TurnCommand extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private LaunchSubsystem launchSubsystem;
    private LightSubsystem lightSubsystem;
    private Timing.Timer timer;
    private boolean redAlliance = false;

    public limelightAutoSpeed_TurnCommand(LimeLightSubsystem limelightSubsystem, TurnTableSubsystem turnTableSubsystem, LaunchSubsystem launchSubsystem,LightSubsystem lightSubsystem ,boolean redAlliance){
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

            timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);


    }

    public void execute(){
        double tx = limelightSubsystem.getTx();
        double distance = limelightSubsystem.getDistance();
        boolean failed = limelightSubsystem.isLimeLightCooked();


        double result = Math.toRadians(tx);


        // 1. Aim Turret
        turnTableSubsystem.limelightFollow(tx, redAlliance);

        // 2. Calculate and Set Hood Angle

      launchSubsystem.distanceToRPM(distance);
      if (failed){
          lightSubsystem.lightRed();
          timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);
         limelightSubsystem.stop();
         limelightSubsystem.shutDown();
         if(!timer.isTimerOn()){
             timer.start();
         }

         if (timer.done()) {

             limelightSubsystem.start();
             if (redAlliance){
                 limelightSubsystem.setPipeline(1);
             } else {
                 limelightSubsystem.setPipeline(2);
             }

         }




      }


//        if (launchSubsystem.isMotorRunning()){
//            launchSubsystem.runMotor();
//        }

    }

    public boolean isFinished(){
        return false;
    }
}

