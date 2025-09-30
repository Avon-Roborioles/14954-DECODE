package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {

    private DriveSubsystem driveSubsystem;
    private DoubleSupplier strafe, forward, turn, heading;
    private boolean fieldCentric;

    public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, DoubleSupplier heading, boolean fieldCentric){
        this.driveSubsystem = driveSubsystem;
        this.strafe = strafe;
        this.forward = forward;
        this.turn = turn;
        this.heading = heading;
        this.fieldCentric = fieldCentric;
        addRequirements(driveSubsystem);

    }
    @Override
    public void execute(){
        driveSubsystem.drive(strafe.getAsDouble(),forward.getAsDouble(),turn.getAsDouble(),heading.getAsDouble(),fieldCentric);
    }
}
