package org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class CancelCommand extends CommandBase {
    private IntakeSubsystem intake;
    private LaunchSubsystem launch;
    public CancelCommand(IntakeSubsystem intake, LaunchSubsystem launch) {
      this.intake = intake;
      this.launch = launch;
        addRequirements(intake, launch);
    }
    @Override
    public void initialize() {
     intake.stopAll();
     launch.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
