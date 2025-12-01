package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TelemetrySubsystem;

public class CompTelemetryCommand extends CommandBase {
    private TelemetrySubsystem telemetrySubsystem;

    public CompTelemetryCommand(TelemetrySubsystem telemetrySubsystem){
        this.telemetrySubsystem = telemetrySubsystem;
    }

    public void execute(){
        telemetrySubsystem.compTelemetry();
    }

}
