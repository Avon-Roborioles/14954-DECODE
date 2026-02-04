package org.firstinspires.ftc.teamcode.Commands.teleop.LightCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class yellowLightCommand extends CommandBase {
    private LightSubsystem lightSubsystem;
    public yellowLightCommand(LightSubsystem lightSubsystem){
        this.lightSubsystem = lightSubsystem;
        addRequirements(lightSubsystem);
    }
    public void initialize(){
        lightSubsystem.lightYellow();
    }
}
