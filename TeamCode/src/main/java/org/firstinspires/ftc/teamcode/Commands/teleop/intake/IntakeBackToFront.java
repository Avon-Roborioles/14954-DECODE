package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeBackToFront extends CommandBase{
    private IntakeServoSubsystem intakeServoSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private Timing.Timer timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);

    public IntakeBackToFront(IntakeServoSubsystem intakeServoSubsystem, DistanceSubsystem distanceSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        this.distanceSubsystem = distanceSubsystem;
        addRequirements(intakeServoSubsystem, distanceSubsystem);
        timer.start();
    }
    @Override
    public void execute() {
        intakeServoSubsystem.IntakeBackToFront();
    }
    public boolean isFinished(){
        return distanceSubsystem.checkFront();
    }
}
