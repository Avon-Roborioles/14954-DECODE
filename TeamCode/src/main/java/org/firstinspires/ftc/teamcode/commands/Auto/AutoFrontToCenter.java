package org.firstinspires.ftc.teamcode.commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class AutoFrontToCenter extends CommandBase {
    private IntakeSubsystem intakeServoSubsystem;
    private Timing.Timer timer;

    public AutoFrontToCenter(IntakeSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);


    }
    public void initialize(){
        timer = new Timing.Timer(3000, TimeUnit.MILLISECONDS);
        timer.start();
    }
    @Override
    public void execute() {
        intakeServoSubsystem.IntakeFrontToCenter();
    }
    public boolean isFinished(){
        return timer.done();
    }
    public void end(boolean interrupted){
        intakeServoSubsystem.stopAll();
    }

}
