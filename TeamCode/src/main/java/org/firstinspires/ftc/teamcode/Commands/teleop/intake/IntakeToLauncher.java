package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeToLauncher extends CommandBase{
    private IntakeServoSubsystem intakeServoSubsystem;
    private Timing.Timer timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);

    public IntakeToLauncher(IntakeServoSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);
        timer.start();
    }
    @Override
    public void execute() {
        intakeServoSubsystem.TransferToLauncher();
    }
    public boolean isFinished(){
        return timer.done();
    }
}

