package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;

import java.util.function.DoubleSupplier;

public class MotorCommand extends CommandBase {
    private MotorSubsystem subsystem;
    private DoubleSupplier power;
    public MotorCommand (MotorSubsystem subsystem, DoubleSupplier power){
        this.subsystem = subsystem;
        this.power = power;
        addRequirements(subsystem);
    }
    @Override
    public void execute() {
        subsystem.runMotor(power.getAsDouble());
    }
}
