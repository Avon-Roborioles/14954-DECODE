package org.firstinspires.ftc.teamcode.pedroPathing;


import static org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity.TAG;

import static java.lang.Math.PI;

import android.util.Log;

import com.pedropathing.VectorCalculator;
import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.OTOSConstants;
import com.pedropathing.ftc.localization.localizers.OTOSLocalizer;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants extends FollowerConstants {
    private OTOSLocalizer sparkfun;
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(20.0)
            .forwardZeroPowerAcceleration(-38.1868184966)
            .lateralZeroPowerAcceleration(-65.99318707)
            .useSecondaryDrivePIDF(false)
            .translationalPIDFCoefficients(new PIDFCoefficients(
                    0.0,
                    0,
                    0.0,
                    0.0
            ))
            .translationalPIDFSwitch(4)
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(
                    0.0,
                    0,
                    0.0,
                    0.0
            ))
            .headingPIDFCoefficients(new PIDFCoefficients(
                    0.0,
                    0,
                    0.0,
                    0.0
            ))
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(
                    0.0,
                    0,
                    0.0,
                    0.0
            ))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(
                    0,
                    0,
                    0,
                    0,
                    0
            ))
            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(
                    0,
                    0,
                    0,
                    0,
                    0
            ))
            .drivePIDFSwitch(15)
            .centripetalScaling(0.0005);
    public static MecanumConstants driveConstants = new MecanumConstants()
            .leftFrontMotorName("frontLeft")
            .leftRearMotorName("backLeft")
            .rightFrontMotorName("frontRight")
            .rightRearMotorName("backRight")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .useBrakeModeInTeleOp(true)
            .xVelocity(82.3219679785)
            .yVelocity(65.799423357);

    /**
     These are the PathConstraints in order:
     tValueConstraint, velocityConstraint, translationalConstraint, headingConstraint, timeoutConstraint,
     brakingStrength, BEZIER_CURVE_SEARCH_LIMIT, brakingStart
     The BEZIER_CURVE_SEARCH_LIMIT should typically be left at 10 and shouldn't be changed.
     */
    public static PathConstraints pathConstraints = new PathConstraints(
            0.995,
            0.1,
            0.1,
            0.009,
            50,
            1.25,
            10,
            1
    );
    public static OTOSConstants sparkConstants = new OTOSConstants()
            .hardwareMapName("otos")
            .offset(new SparkFunOTOS.Pose2D(0,0,-PI/2))
            .linearUnit(DistanceUnit.INCH)
            .angleUnit(AngleUnit.RADIANS)
            .linearScalar(1.0577373997)
            .angularScalar(0.97758);


    //Add custom localizers or drivetrains here
    public static Follower createFollower(HardwareMap hardwareMap) {
        Log.e(TAG, "createFollower started");
        FollowerBuilder fb = new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .pathConstraints(pathConstraints)
                .setLocalizer(new OTOSLocalizer(hardwareMap, sparkConstants));
        Log.e(TAG, "createFollower made and ready to return");
        return fb.build();

    }
}