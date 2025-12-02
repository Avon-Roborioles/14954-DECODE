package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import java.util.function.DoubleSupplier;

public class ManJoystickPassCommand extends CommandBase {
    private IntakeSubsystem intake;
    private DoubleSupplier doubleSupplier;

    public ManJoystickPassCommand(IntakeSubsystem intake, DoubleSupplier doubleSupplier){
        this.intake = intake;
        this.doubleSupplier = doubleSupplier;
        addRequirements(intake);
    }

    public void execute(){
        if (doubleSupplier.getAsDouble() > 0.8){
            intake.IntakeBackToCenter();
        } else if (doubleSupplier.getAsDouble() < -0.8){
            intake.IntakeFrontToCenter();
        } else {
            intake.stopAll();
        }
    }

}
