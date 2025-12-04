package org.firstinspires.ftc.teamcode.commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.concurrent.TimeUnit;

public class ManIntakeToLauncher extends CommandBase {
    private IntakeSubsystem intakeServoSubsystem;


    public ManIntakeToLauncher(IntakeSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);
    }
    @Override
    public void execute() {
        intakeServoSubsystem.TransferToLauncher();
    }
}
