package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeStopServoCommand extends CommandBase {
    private IntakeServoSubsystem subsystem;
    public IntakeStopServoCommand(IntakeServoSubsystem subsystem ){
        this.subsystem = subsystem;
        addRequirements(subsystem);
}
    @Override
        public void execute() {
            subsystem.stopAll();
        }
    public boolean isFinished(){
        return true;
    }
}
