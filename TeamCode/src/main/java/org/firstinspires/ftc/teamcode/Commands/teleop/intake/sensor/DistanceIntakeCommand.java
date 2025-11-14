package org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.REV2mDistanceSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenterAndUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DistanceIntakeCommand extends SequentialCommandGroup {
    private REV2mDistanceSubsystem distanceSubsystem;
    private IntakeServoSubsystem intakeSubsystem;
    private int[] layout;

    // all possible combinations
    private int[] array1 = {1,0,0};
    private int[] array2 = {0,1,0};
    private int[] array3 = {0,0,1};
    private int[] array4 = {1,1,0};
    private int[] array5 = {0,1,1};
    private int[] array6 = {1,0,1};

    public DistanceIntakeCommand(REV2mDistanceSubsystem distanceSubsystem, IntakeServoSubsystem intakeSubsystem){
        this.distanceSubsystem = distanceSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(distanceSubsystem, intakeSubsystem);
        layout = distanceSubsystem.getArtifactPos();
    }
    @Override
    public void execute() {
        if(Arrays.equals(layout, array1)){                            // x - o - o
            addCommands(
                    new IntakeFrontToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array2)){                     // o - x - o
            addCommands(
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array3)){                      // o - o - x
            addCommands(
                    new IntakeBackToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array4)){                  // x - x - o
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeFrontToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array5)){                   // o - x - o
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array6)){                  // x - o - x
            addCommands(
                    new IntakeBackToCenter(intakeSubsystem),
                    // transfers artifact originally in back to launcher while moving front to center
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else {
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),          // x - x - x
                    new IntakeBackToCenter(intakeSubsystem),
                    // transfers artifact originally in back to launcher while moving front to center
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        }
    }
}
