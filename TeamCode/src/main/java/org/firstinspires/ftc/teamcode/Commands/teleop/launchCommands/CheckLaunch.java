package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class CheckLaunch extends CommandBase {
    private DistanceSubsystem distanceSubsystem;
    private LightSubsystem lightSubsystem;

    public CheckLaunch(DistanceSubsystem distanceSubsystem, LightSubsystem lightSubsystem){
        this.distanceSubsystem = distanceSubsystem;
        this.lightSubsystem = lightSubsystem;
        addRequirements(distanceSubsystem,lightSubsystem);
    }

    @Override
    public void execute() {
        if (distanceSubsystem.getArtifactNum() == 0){
            lightSubsystem.lightOff();
        } else if (distanceSubsystem.getArtifactNum() == 1){
            lightSubsystem.lightYellow();
        } else if (distanceSubsystem.getArtifactNum() == 2){
            lightSubsystem.lightOrange();
        } else if (distanceSubsystem.getArtifactNum() == 3){
            lightSubsystem.lightRed();
        }


//        if (!distanceSubsystem.checkFront() && !distanceSubsystem.checkBack()){
//            lightSubsystem.lightOff();
//        } else {
//            lightSubsystem.lightOrange();
//        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
