package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LauncherSubsystem extends SubsystemBase {

    private DcMotor leftFlywheel;
    private DcMotor rightFlywheel;

    public LauncherSubsystem(HardwareMap hardwareMap) {
        leftFlywheel  = hardwareMap.get(DcMotor.class, "leftLauncher");
        rightFlywheel = hardwareMap.get(DcMotor.class, "rightLauncher");

        leftFlywheel.setDirection(DcMotor.Direction.FORWARD);
        rightFlywheel.setDirection(DcMotor.Direction.REVERSE);

        stop();
    }

    public void shoot(double power) {
        leftFlywheel.setPower(power);
        rightFlywheel.setPower(power);
    }

    public void stop() {
        leftFlywheel.setPower(0);
        rightFlywheel.setPower(0);
    }
}