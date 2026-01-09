package org.firstinspires.ftc.teamcode.commands.teleop.turntableCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

import java.util.function.DoubleSupplier;

public class ManualTurntableCommand extends CommandBase {
    private TurnTableSubsystem turntableSubsystem;
    private DoubleSupplier doubleSupplier;
    public ManualTurntableCommand(TurnTableSubsystem turntableSubsystem, DoubleSupplier doubleSupplier){
        this.turntableSubsystem = turntableSubsystem;
        this.doubleSupplier = doubleSupplier;
        addRequirements(turntableSubsystem);

    }

    public void execute(){
        turntableSubsystem.moveManual(doubleSupplier.getAsDouble());
    }
    }
