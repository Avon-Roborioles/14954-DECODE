package org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeFinal extends CommandBase {
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private boolean isFront;
    private Timing.Timer timer;

    public IntakeFinal (IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
    }

    public void initialize() {
        isFront = distanceSubsystem.isIntakingFromFront();
        timer = new Timing.Timer(35, TimeUnit.MILLISECONDS);
    }
    public void execute() {


        if (isFront){
            intakeSubsystem.IntakeFrontOnly();
        } else if (!isFront){
            intakeSubsystem.IntakeBackOnly();
        }
    }
    public boolean isFinished() {
        boolean f = distanceSubsystem.checkFront();
        boolean b = distanceSubsystem.checkBack();
        if (isFront) {
            if (f && !timer.isTimerOn()){
                timer.start();
            }
        } else if (!isFront) {
            if(b && !timer.isTimerOn()){
                timer.start();
            }
        }
        return timer.done();
    }
    public void end(boolean interrupted){
        intakeSubsystem.stopAll();
    }
}
