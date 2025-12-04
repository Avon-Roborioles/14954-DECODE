package org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

import java.util.concurrent.TimeUnit;

public class AutoShootingAuto extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private Timing.Timer timer;
    private boolean f;
    private boolean m;
    private boolean b;

    public AutoShootingAuto(LaunchSubsystem launchSubsystem, DistanceSubsystem distanceSubsystem, IntakeSubsystem subsystem){
        this.launchSubsystem = launchSubsystem;
        this.distanceSubsystem = distanceSubsystem;


        addRequirements(launchSubsystem, distanceSubsystem);
    }

    @Override
    public void initialize() {

        timer = new Timing.Timer(2000, TimeUnit.MILLISECONDS);
        timer.start();

    }

    @Override
    public void execute() {
        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();
        launchSubsystem.frontSetPoint();
        if (timer.done()){
         if(f && !m && !b) {

         }
        }
    }
}
