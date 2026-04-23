package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.newlaunchSequence;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class backLaunchSequence extends CommandBase {
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private Timing.Timer timer;

    public backLaunchSequence(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem){
        this.distanceSubsystem = distanceSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem, distanceSubsystem);
    }

    public void initialize(){
        timer = new Timing.Timer(1000, TimeUnit.MILLISECONDS);

    }

    @Override
    public void execute() {
        intakeSubsystem.backSideLaunch();


    }

    public boolean isFinished(){
        if (!distanceSubsystem.checkBack() && !distanceSubsystem.checkMiddle() && !timer.isTimerOn()){
            timer.start();
        }

        return timer.done();
    }
    public void end(boolean interrupted){
        intakeSubsystem.stopAll();
    }
}
