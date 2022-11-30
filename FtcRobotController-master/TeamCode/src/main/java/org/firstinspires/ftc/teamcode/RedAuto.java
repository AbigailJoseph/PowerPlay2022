package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="RedAuto")
public class RedAuto extends LinearOpMode {

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public ColorSensor sensor;

    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");//need to rename
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        sensor = hardwareMap.get(ColorSensor.class, "sensor");

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        //encoderWheelDrive(DRIVE_SPEED, 35.25, 35.25, 35.25, 35.25);
        //encoderWheelDrive(DRIVE_SPEED, 23.5, -23.5, -23.5,23.5);

        //if red color sensor value is highest - ZONE 1

        if (sensor.red() >= 700)
        {
            if (sensor.red() > sensor.blue() && sensor.red() > sensor.green())
            {
                // movement straight by 2 tiles and left by 1 tile
                encoderWheelDrive(DRIVE_SPEED, 47, 47, 47, 47);
                encoderWheelDrive(DRIVE_SPEED, -23.5, 23.5, 23.5, -23.5);
            }

        }

        //if green color sensor value is highest - ZONE 2

        else if (sensor.green() >= 700)
        {
            /*if ((sensor.green() > sensor.blue() && sensor.green() > sensor.red())
            {
                // movement straight by 2 tiles
                encoderWheelDrive(DRIVE_SPEED, 47, 47, 47, 47);
            }*/
        }

        //if blue color sensor value is highest - ZONE 3

        else if (sensor.blue() >= 700)
        {
           /* if ((sensor.blue() > sensor.red() && sensor.blue() > sensor.green())
            {
                // movement straight by 2 tiles and right by 1 tile
                encoderWheelDrive(DRIVE_SPEED, 47, 47, 47, 47);
                encoderWheelDrive(DRIVE_SPEED, 23.5, -23.5, -23.5, 23.5);
            }*/
        }
    }

    public void encoderWheelDrive(double speed,
                                  double frontRightInches, double frontLeftInches,
                                  double backRightInches, double backLeftInches) {
        int frontRightTarget;
        int frontLeftTarget;
        int backRightTarget;
        int backLeftTarget;

        // Determine new target position, and pass to motor controller
        frontRightTarget = frontRight.getCurrentPosition() + (int) (frontRightInches * COUNTS_PER_INCH);
        frontLeftTarget = frontLeft.getCurrentPosition() + (int) (frontLeftInches * COUNTS_PER_INCH);
        backRightTarget = backRight.getCurrentPosition() + (int) (backRightInches * COUNTS_PER_INCH);
        backLeftTarget = backLeft.getCurrentPosition() + (int) (backLeftInches * COUNTS_PER_INCH);

        frontRight.setTargetPosition(frontRightTarget);
        frontLeft.setTargetPosition(frontLeftTarget);
        backRight.setTargetPosition(backRightTarget);
        backLeft.setTargetPosition(backLeftTarget);

        // Turn On RUN_TO_POSITION
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //start motion.
        frontRight.setPower(speed);
        frontLeft.setPower(speed);
        backRight.setPower(speed);
        backLeft.setPower(speed);


        while(frontRight.isBusy() &&
                frontLeft.isBusy() &&  backLeft.isBusy()
                && backRight.isBusy());

        // Stop all motion;
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        // Turn off RUN_TO_POSITION
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //  sleep(250);   // pause after each move
    }
}
