package org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;

import java.util.concurrent.TimeUnit;

public class PassToCenter extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private LightSubsystem lightSubsystem;
    private boolean isFront;
    private Timing.Timer timer;

    public PassToCenter(IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem, LightSubsystem lightSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
        this.lightSubsystem = lightSubsystem;
    }
    public void initialize(){
        isFront = distanceSubsystem.isIntakingFromFront();
        timer = new Timing.Timer(200, TimeUnit.MILLISECONDS);

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
    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stopAll();
        lightSubsystem.lightYellow();
    }


}
