package org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeFrontToCenterAndUp extends CommandBase{
    private IntakeSubsystem intakeServoSubsystem;
    private Timing.Timer timer;
    private Timing.Timer timer2;

    public IntakeFrontToCenterAndUp(IntakeSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);

    }
    public void initialize(){
        timer = new Timing.Timer(1000, TimeUnit.MILLISECONDS);
        timer2 = new Timing.Timer(1000, TimeUnit.MILLISECONDS);
        timer.start();
    }
    @Override
    public void execute() {
//        if (timer.isTimerOn() && !timer.done()){
//            intakeServoSubsystem.IntakeFrontToCenter();
//        } else if (timer.done()){
//            intakeServoSubsystem.TransferToLauncher();
//            timer2.start();
//        }

        intakeServoSubsystem.IntakeFrontToCenter();
        intakeServoSubsystem.TransferToLauncher();


    }
//    public boolean isFinished(){
//        return timer2.done();
//    }
//    public void end(boolean interrupted){
//        intakeServoSubsystem.stopAll();
//    }
}
