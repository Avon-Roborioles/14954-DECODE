package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class PukeCommand extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    private Timing.Timer timer = new Timing.Timer(4, TimeUnit.SECONDS);
    public PukeCommand(IntakeSubsystem intakeSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }
    @Override
    public void execute() {

        intakeSubsystem.Puke();
        timer.start();
    }

    public boolean isFinished(){
        return timer.done();
    }
}
