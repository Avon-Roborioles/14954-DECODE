package org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class PassToCenter extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private boolean isFront;
    private Timing.Timer timer;

    public PassToCenter(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
    }
    public void initialize(){
        isFront = distanceSubsystem.isIntakingFromFront();
        timer = new Timing.Timer(400, TimeUnit.MILLISECONDS);

    }
@Override
    public void execute() {
        if (isFront){
            intakeSubsystem.IntakeFrontToCenter();
        } else if (!isFront){
            intakeSubsystem.IntakeBackToCenter();
        }
    }
@Override
    public boolean isFinished(){
        boolean m = distanceSubsystem.checkMiddle();

        if (m && !timer.isTimerOn()){
            timer.start();
        }

        return timer.done();
    }


}
