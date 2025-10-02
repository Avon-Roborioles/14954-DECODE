package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ServoSubsystem;

import java.util.function.DoubleSupplier;

public class ServoCommand extends CommandBase {
    private ServoSubsystem subsystem;
    public ServoCommand (ServoSubsystem subsystem ){
        this.subsystem = subsystem;
addRequirements(subsystem);
    }
    @Override
    public void execute() {
        subsystem.runServo();
    }
}
