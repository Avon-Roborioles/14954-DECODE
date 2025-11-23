package org.firstinspires.ftc.teamcode.commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class LaunchAngleCommand extends CommandBase {
    private LaunchSubsystem subsystem;
    public LaunchAngleCommand(LaunchSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    public void execute(double pos){
     subsystem.setLaunchAngle(pos);
    }


    public boolean isFinished(){
        return true;
    }
}
