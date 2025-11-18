package org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenterAndUp;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeToLauncher;

import java.util.Arrays;

public class DistanceIntakeCommand extends SequentialCommandGroup {
    private DistanceSubsystem distanceSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private int[] layout;

    // all possible combinations
    private int[] array1 = {1,0,0};
    private int[] array2 = {0,1,0};
    private int[] array3 = {0,0,1};
    private int[] array4 = {1,1,0};
    private int[] array5 = {0,1,1};
    private int[] array6 = {1,0,1};

    public DistanceIntakeCommand(DistanceSubsystem distanceSubsystem, IntakeSubsystem intakeSubsystem){
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
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array4)){                  // x - x - o
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeFrontToCenter(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array5)){                   // o - x - o
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else if (Arrays.equals(layout, array6)){                  // x - o - x
            addCommands(
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem),
                    // transfers artifact originally in back to launcher while moving front to center
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        } else {
            addCommands(
                    new IntakeToLauncher(intakeSubsystem),          // x - x - x
                    new IntakeBackToCenter(intakeSubsystem , distanceSubsystem),
                    // transfers artifact originally in back to launcher while moving front to center
                    new IntakeFrontToCenterAndUp(intakeSubsystem),
                    new IntakeToLauncher(intakeSubsystem));
        }
    }
}
