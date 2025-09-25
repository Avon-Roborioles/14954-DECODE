package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ServoSubsystem;

import java.util.function.DoubleSupplier;

public class ServoCommand extends CommandBase {
    private ServoSubsystem subsystem;
    private DoubleSupplier power;
    public ServoCommand (ServoSubsystem subsystem, DoubleSupplier power){
        this.subsystem = subsystem;
        this.power = power;
addRequirements(subsystem);
    }
    @Override
    public void execute() {
        subsystem.runServo(power.getAsDouble());
    }
}
