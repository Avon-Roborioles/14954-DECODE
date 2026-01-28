package org.firstinspires.ftc.teamcode.Commands.Auto.AutoCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;

public class AutoSpeedChange extends CommandBase {
    private AutoDriveSubsystem autoDriveSubsystem;
    private double speed;
    public AutoSpeedChange(AutoDriveSubsystem autoDriveSubsystem, double speed){
        this.autoDriveSubsystem = autoDriveSubsystem;
        this.speed = speed;
        addRequirements(autoDriveSubsystem);
    }
    @Override
    public void execute(){
        autoDriveSubsystem.setMaxPower(speed);
    }
}
