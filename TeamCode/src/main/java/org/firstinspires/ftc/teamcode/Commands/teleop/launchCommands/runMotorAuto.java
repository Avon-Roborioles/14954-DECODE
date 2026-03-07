package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

import java.util.concurrent.TimeUnit;

public class runMotorAuto extends CommandBase {
    private LaunchSubsystem subsystem;
    private LightSubsystem lightSubsystem;
    private Timing.Timer timer;


    public runMotorAuto(LaunchSubsystem subsystem, LightSubsystem lightSubsystem){
        this.subsystem = subsystem;
        this.lightSubsystem = lightSubsystem;
        addRequirements(subsystem, lightSubsystem);
    }

    public void initialize(){
        timer = new Timing.Timer(1250, TimeUnit.MILLISECONDS);
        timer.start();

        subsystem.runMotor();
    }
    public void execute() {


    }


    public boolean isFinished() {
        return timer.done();
    }
}

