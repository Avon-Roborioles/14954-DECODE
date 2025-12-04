package org.firstinspires.ftc.teamcode.commands.teleop.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;

public class AutoDriveCommand extends CommandBase {
    private AutoDriveSubsystem autoDriveSubsystem;
    private Telemetry telemetry;
    public AutoDriveCommand(AutoDriveSubsystem autoDriveSubsystem, Telemetry telemetry){
        this.autoDriveSubsystem = autoDriveSubsystem;
        this.telemetry = telemetry;
        addRequirements(autoDriveSubsystem);
    }

    @Override
    public void execute(){
        autoDriveSubsystem.update();
    }
    @Override
    public boolean isFinished(){
        return !autoDriveSubsystem.isBusy();
    }
    public void setPath(Path path, boolean holdEnd){
        autoDriveSubsystem.followPath(path, holdEnd);
    }
    public void setPathChain(PathChain pathChain, boolean holdEnd){
        autoDriveSubsystem.followPath(pathChain, holdEnd);
    }
}
