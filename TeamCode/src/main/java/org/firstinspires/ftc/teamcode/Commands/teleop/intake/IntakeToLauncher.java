package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class IntakeToLauncher extends CommandBase{
    private IntakeServoSubsystem intakeServoSubsystem;

    public IntakeToLauncher(IntakeServoSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);
    }
    @Override
    public void execute() {
        intakeServoSubsystem.TransferToLauncher();
    }
}
