package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class ManFrontToCenter extends CommandBase {
    private IntakeSubsystem intake;

    public ManFrontToCenter(IntakeSubsystem intake){
        this.intake = intake;
    }

    public void execute(){
        intake.IntakeFrontToCenter();
    }
}
