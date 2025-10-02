package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;

import java.util.function.DoubleSupplier;

public class MotorCommand extends CommandBase {
    private MotorSubsystem driveSubsystem;
    private DoubleSupplier strafe, forward, turn, heading;
    private MecanumDrive drive;
    private boolean fieldCentric;

    public MotorCommand(MotorSubsystem driveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn){
        this.driveSubsystem = driveSubsystem;
        this.strafe = strafe;
        this.forward = forward;
        this.turn = turn;

        addRequirements(driveSubsystem);

    }
    @Override
    public void execute(){
        driveSubsystem.drive(strafe.getAsDouble(),forward.getAsDouble(),turn.getAsDouble())  ;
    }
}
