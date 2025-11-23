package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TelemetrySubsystem;

public class TelemetryCommand extends CommandBase {
    private TelemetrySubsystem subsystem;

    public TelemetryCommand(TelemetrySubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }
    @Override
    public void execute(){subsystem.getTelemetry();}
}
