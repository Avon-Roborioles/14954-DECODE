package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeToLauncher extends CommandBase{
    private IntakeSubsystem intakeServoSubsystem;
    private Timing.Timer timer = new Timing.Timer(5000, TimeUnit.MILLISECONDS);

    public IntakeToLauncher(IntakeSubsystem intakeServoSubsystem){
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

