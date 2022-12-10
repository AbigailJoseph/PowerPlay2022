package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;


import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Teleop")
public class Teleop extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;
    private DcMotor rightArm = null;
    private DcMotor leftArm = null;
    private Servo claw = null;
    private double clawPosition;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    private final double CLAWCLOSE = 0.1;
    private final double CLAWOPEN = 1.0;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");

        claw = hardwareMap.get(Servo.class, "claw");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //rightArm.setDirection(DcMotor.Direction.REVERSE);

        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightArm.setDirection(DcMotor.Direction.REVERSE);
        leftArm.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset(); //resets the

        while (opModeIsActive()) {

            //                                   [GAME PAD 1]
            double speed;
            double strafe;
            double turn;

            speed = -gamepad1.right_stick_y; //needs to be negative if right motors are flipped
            strafe = gamepad1.right_stick_x;
            turn = gamepad1.left_stick_x;

            // WHEEL MOVEMENT(left stick) & ROTATION(right stick)
            frontLeft.setPower(speed + turn + strafe);
            frontRight.setPower(speed - turn - strafe);
            backLeft.setPower(speed + turn - strafe);
            backRight.setPower(speed - turn + strafe);

            //                                  [GAME PAD 2]


            if (gamepad2.left_bumper) { //OPEN
                claw.setPosition(0);

            } else if (gamepad2.right_bumper) { //CLOSE
                claw.setPosition(1);
                telemetry.addData("Servo Position", clawPosition);
                telemetry.update();
            }


            if (gamepad2.y) { //preset high height
                encoderArmUp(8);
            } else if (gamepad2.b) { //preset medium height
                encoderArmUp(6.0);
            } else if (gamepad2.a) {//preset low height
                encoderArmUp(5.0);
            } else if (gamepad2.x) { //down
                runtime.reset();
                while (runtime.seconds() < 0.1) {
                    rightArm.setPower(-0.0005);
                    leftArm.setPower(0.0005);
                }
                runtime.reset();
                while (runtime.seconds() < 0.1) {
                    rightArm.setPower(0.0001);
                    leftArm.setPower(-0.0001);
                }
                runtime.reset();
                while (runtime.seconds() < 0.15) {
                    rightArm.setPower(0.0003);
                    leftArm.setPower(-0.0003);
                }
                runtime.reset();
                while (runtime.seconds() < 0.2) {
                    rightArm.setPower(0.0004);
                    leftArm.setPower(-0.0004);
                }
                runtime.reset();
                while (runtime.seconds() < 0.2) {
                    rightArm.setPower(0.0005);
                    leftArm.setPower(-0.0005);
                }
            } else if (gamepad2.dpad_down) {//moving down
                runtime.reset();
                while (runtime.seconds() < 0.00005) {
                    rightArm.setPower(0.005);
                    leftArm.setPower(-0.005);
                }
                runtime.reset();
                while (runtime.seconds() < 0.00005) {
                    rightArm.setPower(0);
                    leftArm.setPower(0);
                }
            } else if (gamepad2.dpad_up) {//moving up
                rightArm.setPower(0.15);
                leftArm.setPower(-0.1);
            } else { //KEEP AT POSITION WHEN NO BUTTON PRESSED
                rightArm.setPower(0.002); //SMALLER THIS VALUE IS THE LONGER IT WILL TAKE FOR THE ARM TO SHOOT UP
                leftArm.setPower(-0.002);
            }
            /*else{ //NO POWER WHEN NO BUTTON PRESSED
                rightArm.setPower(0);
                leftArm.setPower(0);
            }*/

            // ARM(right stick)
            /*if(gamepad2.right_stick_y > 0.2){ //moves up
                rightArm.setPower(0.85);
                leftArm.setPower(-0.85);
            }
            else if(gamepad2.right_stick_y < -0.2){ //moves down
                rightArm.setPower(-0.85);
                leftArm.setPower(0.85);
            }
            else{
                rightArm.setPower(0);
                leftArm.setPower(0);
            }*/

            /*

            // CLAW TEST(button a & b)
            if(gamepad2.right_bumper){ //open or close
                clawPosition += 0.01;
            }
            else if(gamepad2.left_bumper){ //open or close
                clawPosition -= 0.01;
            }
            clawPosition = Range.clip(clawPosition, -1.0, 1.0);
            claw.setPosition(clawPosition);
            telemetry.addData("Servo Position: ", clawPosition);
            telemetry.update(); */

            //CLAW(bumpers)
            //claw.setDirection(Servo.Direction.FORWARD);
            if(gamepad2.left_bumper){ //close
                //clawPosition = CLAWCLOSE;
               // claw.setDirection(CRServo.Direction.FORWARD);
                //claw.setPower(0.5);
                claw.setDirection(Servo.Direction.FORWARD);

            }
            else if(gamepad2.right_bumper) { //open
                claw.setDirection(Servo.Direction.REVERSE);
                //clawPosition = CLAWOPEN;
                // claw.setDirection(CRServo.Direction.REVERSE);
                //claw.setPower(-0.5);
            }
            //clawPosition = Range.clip(clawPosition, -1.0, 1.0);
            claw.setPosition(clawPosition);
            //claw.setDirection();
            //telemetry.addData("claw power: ", claw.getPower());
            //telemetry.addData("claw power: ", claw.getDirection());
            clawPosition = Range.clip(clawPosition, 0.1, 1.0);
            telemetry.addData("Servo Position", clawPosition);
            telemetry.update();

        }
    }

        public void encoderArmUp ( double openInches){
            int openTarget1;
            int openTarget2;


            // Determine new OPEN target position, and pass to motor controller
            openTarget1 = leftArm.getCurrentPosition() + (int) (openInches * COUNTS_PER_INCH);
            openTarget2 = rightArm.getCurrentPosition() + (int) (openInches * COUNTS_PER_INCH);

            //opens arm
            leftArm.setTargetPosition(-openTarget1);
            rightArm.setTargetPosition(openTarget2);

            // Turn On RUN_TO_POSITION
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            //start motion.
            rightArm.setPower(0.15);
            leftArm.setPower(0.1);

            while (leftArm.isBusy() && rightArm.isBusy()) ;

            leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //hold
            rightArm.setPower(0.005);
            leftArm.setPower(-0.005);


            //  sleep(250);   // pause after each move
        }

    }




