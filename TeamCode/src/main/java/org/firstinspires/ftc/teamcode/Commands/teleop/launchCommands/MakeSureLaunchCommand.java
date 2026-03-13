package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class MakeSureLaunchCommand extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private Timing.Timer timer;

    public MakeSureLaunchCommand(IntakeSubsystem intakeSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);

    }
    public void initialize(){
        timer = new Timing.Timer(1500, TimeUnit.MILLISECONDS);
        timer.start();
    }
    @Override
    public void execute() {
        intakeSubsystem.backSideLaunch();
    }
    public boolean isFinished(){
        return timer.done();
    }
//    public void end(boolean interrupted){
//        intakeSubsystem.stopAll();
//    }
}

