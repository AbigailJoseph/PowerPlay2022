package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.teamcode.InternalCode.CompleteBot;

public class LastYearAuto extends LinearOpMode{

    public ElapsedTime runtime = new ElapsedTime();
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor duck;
    public DcMotor intake;
    public DcMotor arm;
    public Servo cage;

    static final double DRIVE_SPEED = 0.8;
    static final double DUCK_SPEED = 1.0;
    static final double ARM_SPEED = 0.5;
    static final double SERVO_POSITION = 0.0;

    static final double  COUNTS_PER_MOTOR_REV = 1120 ; // NeveRest 40 Motor Encoder
    static final double  DRIVE_GEAR_REDUCTION = 1.0 ;
    static final double  WHEEL_DIAMETER_INCHES = 3.85827;   // for circumference
    static final double  COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    @Override
    public void runOpMode(){


        //Initialize the drive system variables.
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        arm = hardwareMap.get(DcMotor.class, "arm");
        duck = hardwareMap.get(DcMotor.class, "duck");
        intake = hardwareMap.get(DcMotor.class, "intake");
        cage = hardwareMap.get(Servo.class, "cage");

        // Send telemetry message to signify robot waiting;

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //   test:
        cage.setPosition(1.0);

        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        //  encoderWheelDrive(DRIVE_SPEED,  11,  11, 11, 11); //forward 11

        encoderWheelDrive(DRIVE_SPEED,  20,  -20, -20, 20);  // strafe left
        encoderWheelDrive(DRIVE_SPEED,  21,  21, 21, 21); //forward
        encoderArm(ARM_SPEED, 12, -6, SERVO_POSITION);
        encoderWheelDrive(DRIVE_SPEED,  -5,  -5, -5, -5); //back
        encoderWheelDrive(DRIVE_SPEED,   17, -17, 17, -17);//turn 90degrees
        encoderWheelDrive(DRIVE_SPEED,  19, -19, -19, 19);  // strafe left
        encoderWheelDrive(DRIVE_SPEED,  -45,  -45, -45, -45); //back
        encoderDuck(DUCK_SPEED, -95);
        encoderWheelDrive(DRIVE_SPEED,  -30,  30, 30, -30);  // strafe right
        encoderWheelDrive(DRIVE_SPEED,  -5,  -5, -5, -5); //back



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

    public void encoderDuck(double speed, double inches){
        int target;

        // Determine new target position, and pass to motor controller
        target = duck.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);

        duck.setTargetPosition(target);

        // Turn On RUN_TO_POSITION
        duck.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //start motion.
        duck.setPower(speed);

        while(duck.isBusy());

        //stops all motion
        duck.setPower(0);

        // Turn off RUN_TO_POSITION
        duck.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //for arm movement
    public void encoderArm(double speed, double openInches, double closeInches, double servoPosition){
        int openTarget;
        int closeTarget;

        // Determine new OPEN target position, and pass to motor controller
        openTarget = arm.getCurrentPosition() + (int) (openInches * COUNTS_PER_INCH);

        // Determine new CLOSE target position, and pass to motor controller
        closeTarget = arm.getCurrentPosition() + (int) (closeInches * COUNTS_PER_INCH);

        //opens arm
        arm.setTargetPosition(openTarget);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(speed);
        while(arm.isBusy());
        arm.setPower(0);

        // opens the cage
        cage.setPosition(servoPosition);

        sleep(2000);

        //close the cage
        cage.setPosition(1.0);

        //closes arm
        arm.setTargetPosition(closeTarget);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(speed);
        while(arm.isBusy());
        arm.setPower(0);

        // Turn off RUN_TO_POSITION
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
