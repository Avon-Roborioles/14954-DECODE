package org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;

public class CancelCommand extends CommandBase {
    private IntakeSubsystem intake;
    private LaunchSubsystem launch;
    private LightSubsystem light;
    public CancelCommand(IntakeSubsystem intake, LaunchSubsystem launch, LightSubsystem light) {
      this.intake = intake;
      this.launch = launch;
      this.light = light;
        addRequirements(intake, launch, light);
    }
    @Override
    public void initialize() {
     intake.stopAll();
     launch.stopMotor();
     light.lightOff();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
