package org.firstinspires.ftc.teamcode.Commands.teleop.LimelightCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class noLimelightCenter extends CommandBase {
    private TurnTableSubsystem turnTableSubsystem;
    public noLimelightCenter(TurnTableSubsystem turnTableSubsystem){
        this.turnTableSubsystem = turnTableSubsystem;
        addRequirements(turnTableSubsystem);
    }

    @Override
    public void initialize() {
        turnTableSubsystem.FrontSetPoint();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}


