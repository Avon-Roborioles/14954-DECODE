package org.firstinspires.ftc.teamcode.Commands.teleop.LimelightCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class limelightAutoSpeed_TurnCommand extends CommandBase {
    private LimeLightSubsystem limelightSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private LaunchSubsystem launchSubsystem;
    private LightSubsystem lightSubsystem;
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
    }

    public void execute(){
        double tx = limelightSubsystem.getTx();
        double distance = limelightSubsystem.getDistance();
        boolean failed = limelightSubsystem.isLimeLightCooked();


        double result = Math.toRadians(tx);


        // 1. Aim Turret
        turnTableSubsystem.limelightFollow(tx);

        // 2. Calculate and Set Hood Angle

      launchSubsystem.distanceToRPM(distance);
      if (failed){
          lightSubsystem.lightRed();
          limelightSubsystem.stop();
          limelightSubsystem.start();
          if (redAlliance){
              limelightSubsystem.setPipeline(1);
          } else {
              limelightSubsystem.setPipeline(2);
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

