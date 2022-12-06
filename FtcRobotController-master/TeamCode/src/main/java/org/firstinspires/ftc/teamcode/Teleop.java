package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

    //FILL THESE IN
    static final double CLAWCLOSE = 0.1999;
    static final double CLAWOPEN = 0.9;

    @Override
    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
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

        while(opModeIsActive()){

            //                                   [GAME PAD 1]
            double speed;
            double strafe;
            double turn;

            speed = -gamepad1.right_stick_y; //needs to be negative if right motors are flipped
            strafe = gamepad1.right_stick_x;
            turn = gamepad1.left_stick_x;

            // WHEEL MOVEMENT(left stick) & ROTATION(right stick)
            frontLeft.setPower(speed+turn+strafe);
            frontRight.setPower(speed-turn-strafe);
            backLeft.setPower(speed+turn-strafe);
            backRight.setPower(speed-turn+strafe);

            //ALTERNATE WHEEL CODE
            /*
            if (gamepad1.right_stick_y > -0.3 && gamepad1.right_stick_y < 0.3) { //buffer zone
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
            if (gamepad1.right_stick_x > -0.3 && gamepad1.right_stick_x < 0.3) { //buffer zone
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
            if (gamepad1.right_stick_y > 0.3) { //forward
                frontLeft.setPower(0.8);
                frontRight.setPower(0.8);
                backLeft.setPower(0.8);
                backRight.setPower(0.8);

            }
            if (gamepad1.right_stick_y < -0.3) { //backward
                frontLeft.setPower(-0.8);
                frontRight.setPower(-0.8);
                backLeft.setPower(-0.8);
                backRight.setPower(-0.8);
            }
            if (gamepad1.right_stick_x > 0.3) { //right
                frontLeft.setPower(0.8);
                frontRight.setPower(-0.8);
                backLeft.setPower(-0.8);
                backRight.setPower(0.8);
            }
            if (gamepad1.right_stick_x < -0.3) { //left
                frontLeft.setPower(-0.8);
                frontRight.setPower(0.8);
                backLeft.setPower(0.8);
                backRight.setPower(-0.8);
            }
            if (gamepad1.left_stick_x < -0.3) { //rotate left
                frontLeft.setPower(-0.8);
                frontRight.setPower(0.8);
                backLeft.setPower(-0.8);
                backRight.setPower(0.8);
            }
            if (gamepad1.left_stick_x > -0.3) { //rotate right
                frontLeft.setPower(0.8);
                frontRight.setPower(-0.8);
                backLeft.setPower(0.8);
                backRight.setPower(-0.8);;
            }

             */

            //                                  [GAME PAD 2]

            // ARM TEST(button x & y)
            if(gamepad2.y){ //preset high height
                while(runtime.seconds() < 0.5){
                    rightArm.setPower(0.15); // CHECK SPEED
                    leftArm.setPower(-0.15);
                }


            }
            /*else if(gamepad2.x){  // stops at position
                rightArm.setPower(0.0005);
                leftArm.setPower(-0.0005);

                rightArm.setPower(0.005); //SMALLER THIS VALUE IS THE LONGER IT WILL TAKE FOR THE ARM TO SHOOT UP
                leftArm.setPower(-0.005);

            }*/
            else if (gamepad2.x){ //down
                runtime.reset();
                while(runtime.seconds() < 0.2) {
                    rightArm.setPower(-0.0005);
                    leftArm.setPower(0.0005);
                }
                while(runtime.seconds() < 0.2) {
                    rightArm.setPower(0.0001);
                    leftArm.setPower(-0.0001);
                }
                while(runtime.seconds() < 0.3) {
                    rightArm.setPower(0.0003);
                    leftArm.setPower(-0.0003);
                }
                while(runtime.seconds() < 0.4) {
                    rightArm.setPower(0.0004);
                    leftArm.setPower(-0.0004);
                }
                while(runtime.seconds() < 0.4) {
                    rightArm.setPower(0.0005);
                    leftArm.setPower(-0.0005);
                }
                /*while(runtime.seconds() < 0.00005) { //FALL
                    rightArm.setPower(-0.005);
                    leftArm.setPower(0.005);
                }
                while(runtime.seconds() < 0.00005){ //HOLD
                    rightArm.setPower(0.005);
                    leftArm.setPower(-0.005);
                }
                runtime.reset();
                while(runtime.seconds() < 0.00005){ //FALL
                    rightArm.setPower(0);
                    leftArm.setPower(0);
                }*/

            }
            else if (gamepad2.dpad_down){ //going fully down
                rightArm.setPower(-0.001);
                leftArm.setPower(0.001);
            }
            else if(gamepad2.b){ //preset medium height
                while(runtime.seconds() < 0.3){
                    rightArm.setPower(0.15); // CHECK SPEED
                    leftArm.setPower(-0.15);
                }
            }
            else if(gamepad2.a){//preset low height
                while(runtime.seconds() < 0.1){
                    rightArm.setPower(0.15); // CHECK SPEED
                    leftArm.setPower(-0.15);
                }
            }
            else{ //KEEP AT POSITION WHEN NO BUTTON PRESSED
                rightArm.setPower(0.002); //SMALLER THIS VALUE IS THE LONGER IT WILL TAKE FOR THE ARM TO SHOOT UP
                leftArm.setPower(-0.002);

                //OR
                /*
                runtime.reset();
                while(runtime.seconds() < 5 <- CHANGE VAL // NEED TO COUNT THE # OF SECONDS IT TAKES FOR ARM TO SHOOT UP ){
                    rightArm.setPower(0.005);
                    leftArm.setPower(-0.005);
                }
                runtime.reset();
                while(runtime.seconds() < 0.001){
                    rightArm.setPower(0.0005);
                    leftArm.setPower(-0.0005);
                }
                */
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

            //CLAW(bumbers)
            if(gamepad2.left_bumper){ //close
                clawPosition = CLAWCLOSE;
            }
            else if(gamepad2.right_bumper){ //open
                clawPosition = CLAWOPEN;
            }
            //clawPosition = Range.clip(clawPosition, -1.0, 1.0);
            claw.setPosition(clawPosition);
            telemetry.addData("Servo Position: ", clawPosition);
            telemetry.update();
        }
    }

    public void encoderArm(double speed,
                                  double rightArmInch, double leftArmInch) {
        int rightArmTarget;
        int leftArmTarget;


        // Determine new target position, and pass to motor controller
        rightArmTarget = frontRight.getCurrentPosition() + (int) (rightArmInch * COUNTS_PER_INCH);
        leftArmTarget = frontLeft.getCurrentPosition() + (int) (leftArmInch * COUNTS_PER_INCH);


        rightArm.setTargetPosition(rightArmTarget);
        leftArm.setTargetPosition(leftArmTarget);


        // Turn On RUN_TO_POSITION
        rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //start motion.
        rightArm.setPower(speed);
        leftArm.setPower(speed);



        while(frontRight.isBusy() &&
                frontLeft.isBusy() );

        // Stop all motion;
        frontRight.setPower(0);
        frontLeft.setPower(0);


        // Turn off RUN_TO_POSITION
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //  sleep(250);   // pause after each move
    }

}
