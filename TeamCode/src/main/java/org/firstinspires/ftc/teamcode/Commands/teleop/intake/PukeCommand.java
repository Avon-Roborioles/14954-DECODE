package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class PukeCommand extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    public PukeCommand(IntakeSubsystem intakeSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }
    @Override
    public void execute() {
        intakeSubsystem.Puke();
    }

}
