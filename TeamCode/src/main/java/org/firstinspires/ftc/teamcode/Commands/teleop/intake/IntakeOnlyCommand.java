package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeOnlyCommand extends CommandBase {
    private IntakeSubsystem intakeSubsystem;
    public IntakeOnlyCommand(IntakeSubsystem intakeSubsystem){
        this.intakeSubsystem =  intakeSubsystem;
    }

    public void execute(){
        intakeSubsystem.intakeOnly();
    }
}
