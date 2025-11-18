package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeFrontToCenter extends CommandBase{
    private IntakeSubsystem intakeServoSubsystem;
    private Timing.Timer timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);

    public IntakeFrontToCenter(IntakeSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);
        timer.start();
    }
    @Override
    public void execute() {
        intakeServoSubsystem.IntakeFrontToCenter();
    }
    public boolean isFinished(){
        return timer.done();
    }
}
