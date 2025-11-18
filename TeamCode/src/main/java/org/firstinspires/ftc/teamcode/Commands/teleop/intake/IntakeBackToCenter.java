package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class IntakeBackToCenter extends CommandBase{
    private IntakeSubsystem intakeServoSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private Timing.Timer timer = new Timing.Timer(500, TimeUnit.MILLISECONDS);

    public IntakeBackToCenter(IntakeSubsystem intakeServoSubsystem, DistanceSubsystem distanceSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        this.distanceSubsystem = distanceSubsystem;
        addRequirements(intakeServoSubsystem, distanceSubsystem);
        timer.start();
    }
    @Override
    public void execute() {
        intakeServoSubsystem.IntakeBackToCenter();
    }
    public boolean isFinished(){
        return distanceSubsystem.checkMiddle();
    }
}
