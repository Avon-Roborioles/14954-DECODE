package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Subsystems.TelemetrySubsystem;

public class AutoTelemetryCommand extends CommandBase {
    private TelemetrySubsystem subsystem;
    private Pose pose;


    public AutoTelemetryCommand(TelemetrySubsystem subsystem, Pose pose){
        this.subsystem = subsystem;
        this.pose = pose;
        addRequirements(subsystem);
    }
    @Override
    public void execute(){subsystem.autoTelemetry(pose);}

}
