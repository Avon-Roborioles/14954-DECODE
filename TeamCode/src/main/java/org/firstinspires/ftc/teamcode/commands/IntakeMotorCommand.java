package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;

import java.util.function.DoubleSupplier;

public class IntakeMotorCommand extends CommandBase {
    private IntakeMotorSubsystem driveSubsystem;
    private DoubleSupplier strafe, forward, turn, heading;
    private MecanumDrive drive;
    private boolean fieldCentric;

    public IntakeMotorCommand(IntakeMotorSubsystem driveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn){
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
