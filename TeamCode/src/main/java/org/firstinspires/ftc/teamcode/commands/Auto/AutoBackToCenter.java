package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class AutoBackToCenter extends CommandBase {
    private IntakeSubsystem intakeServoSubsystem;
    private Timing.Timer timer;

    public AutoBackToCenter(IntakeSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);


    }
    public void initialize(){
        timer = new Timing.Timer(1000, TimeUnit.MILLISECONDS);
        timer.start();
    }
    @Override
    public void execute() {
        intakeServoSubsystem.IntakeBackToCenter();
    }
    public boolean isFinished(){
        return timer.done();
    }
    public void end(boolean interrupted){
        intakeServoSubsystem.stopAll();
    }
}
