package org.firstinspires.ftc.teamcode.Commands.teleop.turntableCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class RecenterLimelightMiddle extends CommandBase {
    private TurnTableSubsystem turnTableSubsystem;
    public RecenterLimelightMiddle(TurnTableSubsystem turnTableSubsystem){
        this.turnTableSubsystem = turnTableSubsystem;
        addRequirements(turnTableSubsystem);
    }

    @Override
    public void initialize() {
        turnTableSubsystem.FrontSetPoint();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
