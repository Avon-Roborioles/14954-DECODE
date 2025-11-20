package org.firstinspires.ftc.teamcode.commands.teleop.CommandGroups;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.DistanceSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeBackToFront;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToBack;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeFrontToCenter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.ParallelIntakeOuter;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor.IntakeBackOnly;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.sensor.IntakeFrontOnly;

public class AllIntakeCommand extends SequentialCommandGroup {
    public AllIntakeCommand(DistanceSubsystem distanceSubsystem,
                            IntakeSubsystem intakeSubsystem) {

        addRequirements(distanceSubsystem, intakeSubsystem);

        boolean f = distanceSubsystem.checkFront();
        boolean m = distanceSubsystem.checkMiddle();
        boolean b = distanceSubsystem.checkBack();


        while(!f || !m || !b){ //while not full
            new ParallelIntakeOuter(intakeSubsystem, distanceSubsystem); //runs both outer until artifact is detected
            if(f){
                new IntakeFrontToBack(intakeSubsystem, distanceSubsystem); //if front send artifact to back
                new IntakeFrontOnly(intakeSubsystem, distanceSubsystem); //run until front detects another
                if(f){
                    new IntakeFrontToCenter(intakeSubsystem); //transfer second to center
                    new IntakeFrontOnly(intakeSubsystem, distanceSubsystem); //intake until third is collected in front
                }
            } else if (b){
                new IntakeBackToFront(intakeSubsystem, distanceSubsystem); //if back send artifact to front
                new IntakeBackOnly(intakeSubsystem, distanceSubsystem); //run until back detects another
                if(b){
                    new IntakeBackToCenter(intakeSubsystem, distanceSubsystem); //if another in back, send to center
                    new IntakeBackOnly(intakeSubsystem, distanceSubsystem); //run until third artifact detected in back
                }
            }
        }
    }
    }

