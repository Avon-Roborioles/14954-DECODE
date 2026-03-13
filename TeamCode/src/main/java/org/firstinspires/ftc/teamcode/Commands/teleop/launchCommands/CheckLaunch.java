package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class CheckLaunch extends CommandBase {
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private LightSubsystem lightSubsystem;

    public CheckLaunch(DistanceSubsystem distanceSubsystem, LightSubsystem lightSubsystem, IntakeSubsystem intakeSubsystem){
        this.distanceSubsystem = distanceSubsystem;
        this.lightSubsystem = lightSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(distanceSubsystem,lightSubsystem, intakeSubsystem);
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


        if (distanceSubsystem.getArtifactNum() != 0){
            intakeSubsystem.manLaunch();
        } else {
            intakeSubsystem.stopAll();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
