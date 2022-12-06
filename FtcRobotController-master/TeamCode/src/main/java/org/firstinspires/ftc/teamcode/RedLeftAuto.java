package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RedLeftAuto")
public class RedLeftAuto extends LinearOpMode {

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 3.85827;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public ColorSensor sensor;
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime downtime = new ElapsedTime();
    private DcMotor rightArm = null;
    private DcMotor leftArm = null;
    private Servo claw = null;

    static final double CLAWCLOSE = 0.1999;
    static final double CLAWOPEN = 0.9;

    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");//need to rename
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        sensor = hardwareMap.get(ColorSensor.class, "sensor");

        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");

        claw = hardwareMap.get(Servo.class, "claw");

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        claw.setPosition(CLAWCLOSE);
        //right
        encoderWheelDrive(DRIVE_SPEED, -15, 15, 15, -15);

        //forward
        encoderWheelDrive(DRIVE_SPEED, 15, 15, 15, 15);

        //lift up arm
        encoderArmOpen(0.3,4.7,40);

        claw.setPosition(CLAWOPEN);

        //move a bit back
        encoderWheelDrive(DRIVE_SPEED, -10, -10, -10, -10);

        //close arm
        encoderArmClose(20); //time it takes to close arm

        //left
        encoderWheelDrive(DRIVE_SPEED, 35.25, -35.25, -35.25, 35.25);

        //forward
        encoderWheelDrive(DRIVE_SPEED, 35.25, 35.25, 35.25, 35.25);

        //in front of the cone
        //returns color values when arm is up
        telemetry.addData("Red", sensor.red());
        telemetry.addData("Blue", sensor.blue());
        telemetry.addData("Green", sensor.green());
        telemetry.addData("Alpha", sensor.alpha());
        telemetry.update();


        //sensor colors
        runtime.reset();
        sensor.enableLed(true);
        if (sensor.red() >= 700) //<-- might not need this  //ZONE 1
        {
            if (sensor.red() > sensor.blue() && sensor.red() > sensor.green())
            {
                // movement left by 1 tile
                encoderWheelDrive(DRIVE_SPEED, -23.5, 23.5, 23.5, -23.5);
            }

        }
        else if (sensor.green() >= 700) //ZONE 2
        {
            if (sensor.green() > sensor.blue() && sensor.green() > sensor.red())
            {
                // movement straight a bit
                encoderWheelDrive(DRIVE_SPEED, 10, 10, 10, 10);
            }
        }
        else if (sensor.blue() >= 700) //ZONE 3
        {
            if (sensor.blue() > sensor.red() && sensor.blue() > sensor.green())
            {
                // movement right by 1 tile
                encoderWheelDrive(DRIVE_SPEED, 23.5, -23.5, -23.5, 23.5);
            }
        }
        sensor.enableLed(false);



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

    public void encoderArmOpen(double speed, double openInches, double holdTime){
        int openTarget1;
        int openTarget2;


        // Determine new OPEN target position, and pass to motor controller
        openTarget1 = leftArm.getCurrentPosition() + (int) (openInches * COUNTS_PER_INCH);
        openTarget2 = rightArm.getCurrentPosition() + (int) (openInches * COUNTS_PER_INCH);

        //opens arm
        leftArm.setTargetPosition(openTarget1);
        rightArm.setTargetPosition(-openTarget2);

        leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftArm.setPower(speed);
        rightArm.setPower(speed);

        while(leftArm.isBusy() && rightArm.isBusy());
        leftArm.setPower(0); //MIGHT NEED TO DELETE
        rightArm.setPower(0);

        // Turn off RUN_TO_POSITION
        leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //HOLDS arm
        runtime.reset();
        while (runtime.seconds() < holdTime){
            rightArm.setPower(0.005);
            leftArm.setPower(-0.005);
        }

    }

    public void encoderArmClose(double closeTime){
        //CLOSES ARM
        runtime.reset();
        while(runtime.seconds() < closeTime){
            downtime.reset();
            while(downtime.seconds() < 0.005){ //HOLD
                rightArm.setPower(0.005);
                leftArm.setPower(-0.005);
            }
            downtime.reset();
            while(downtime.seconds() < 0.0001){ //FALL
                rightArm.setPower(0);
                leftArm.setPower(0);
            }
        }

        leftArm.setPower(0);
        rightArm.setPower(0);
    }
/*
    //return rgb values color TEST
        runtime.reset();
        sensor.enableLed(true);
        while(runtime.seconds() <= 30){
        telemetry.addData("Red", sensor.red());
        telemetry.addData("Blue", sensor.blue());
        telemetry.addData("Green", sensor.green());
        telemetry.addData("Alpha", sensor.alpha());
        telemetry.update();
    }
        sensor.enableLed(false); */


}
