package org.firstinspires.ftc.teamcode.Commands.teleop.LightCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class lightOffCommand extends CommandBase {
    private LightSubsystem lightSubsystem;
    public lightOffCommand(LightSubsystem lightSubsystem){
        this.lightSubsystem = lightSubsystem;
        addRequirements(lightSubsystem);
    }
    public void initialize(){
        lightSubsystem.lightOff();
    }
    public boolean isFinished(){
        return true;
    }
}
