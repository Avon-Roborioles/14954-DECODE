package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class IntakeFrontToBack extends CommandBase{
    private IntakeServoSubsystem intakeServoSubsystem;

    public IntakeFrontToBack(IntakeServoSubsystem intakeServoSubsystem){
        this.intakeServoSubsystem = intakeServoSubsystem;
        addRequirements(intakeServoSubsystem);
    }
    @Override
    public void execute() {
        intakeServoSubsystem.IntakeFrontToBack();
    }
}
