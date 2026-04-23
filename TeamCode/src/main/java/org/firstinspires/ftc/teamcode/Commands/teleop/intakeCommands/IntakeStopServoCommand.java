package org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class IntakeStopServoCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    private Timing.Timer timer;
    public IntakeStopServoCommand(IntakeSubsystem subsystem ){
        this.subsystem = subsystem;
        addRequirements(subsystem);
}

    public void initialize(){


    }
@Override
        public void execute() {
            subsystem.stopAll();
        }
    public boolean isFinished(){
        return true;
    }
}
