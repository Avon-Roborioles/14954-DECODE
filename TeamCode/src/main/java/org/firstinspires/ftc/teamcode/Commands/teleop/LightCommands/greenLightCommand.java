package org.firstinspires.ftc.teamcode.Commands.teleop.LightCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

public class greenLightCommand extends CommandBase {
    private LightSubsystem lightSubsystem;
    public greenLightCommand(LightSubsystem lightSubsystem){
        this.lightSubsystem = lightSubsystem;
        addRequirements(lightSubsystem);
    }
    public void initialize(){
        lightSubsystem.lightGreen();
    }
}
