package org.firstinspires.ftc.teamcode.opmodes;

import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.geometry.*;
import com.pedropathing.paths.*;
import com.pedropathing.follower.Follower;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Storage;
import org.firstinspires.ftc.teamcode.commands.teleop.TeleDriveCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.TelePathDriveCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.TeleSlowDriveCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystems.AutoDriveSubsystem;
@TeleOp(name = "TeleOpBase")

public class TeleOpBase extends Storage {
    private GamepadEx driverOp;
    private Follower follower;
    private AutoDriveSubsystem autoDriveSubsystem;
    private Path scorePath;

    @Override
    public void initialize() {
        driverOp = new GamepadEx(gamepad1);
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(memory.lastPose);
        follower.setPose(memory.lastPose);
        follower.startTeleopDrive();

        telemetry.addData("Starting teleop", "");
        follower.startTeleopDrive();
        telemetry.addData("Started teleop", "");
        double heading = memory.lastPose.getHeading();
        updateTelemetry(telemetry);

        autoDriveSubsystem = new AutoDriveSubsystem(follower, telemetry, memory.lastPose);
        initPoseSelect(driverOp);

        autoDriveSubsystem.setDefaultCommand(new TeleDriveCommand(autoDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, true));
        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenHeld(new TeleSlowDriveCommand(autoDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, true))
                .whenInactive(new TeleDriveCommand(autoDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, true));

        /*
        Command Bindings
         */

        driverOp.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new InstantCommand(() -> {
                    follower.setPose(new Pose(0, 0, PI));
                }));
        driverOp.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new TelePathDriveCommand(autoDriveSubsystem, follower.getPose()));

        CommandScheduler.getInstance().schedule(new WaitCommand(10));

    }

        @Override
        public void runOpMode () throws InterruptedException {
            initialize();
            while (opModeInInit() && !isStopRequested()) {
                runPoseSelect(telemetry);
                follower.setPose(memory.lastPose);
                // follower.drawOnDashBoard();
            }
            waitForStart();

            // run the scheduler
            while (!isStopRequested() && opModeIsActive()) {
                run();
            }
            reset();
       }

}
