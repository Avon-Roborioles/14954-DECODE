package org.firstinspires.ftc.teamcode.commands.teleop.launch;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;

public class TurnLauncher extends CommandBase {
    private LaunchSubsystem subsystem;
    private Telemetry telemetry;
    private double pos;

    public TurnLauncher(LaunchSubsystem subsystem, Telemetry telemetry, double pos){
        this.subsystem = subsystem;
        this.telemetry = telemetry;
        this.pos = pos;
        addRequirements(subsystem);
    }
    @Override
    public void execute(){
        subsystem.setTurnServo(pos);
    }

}
