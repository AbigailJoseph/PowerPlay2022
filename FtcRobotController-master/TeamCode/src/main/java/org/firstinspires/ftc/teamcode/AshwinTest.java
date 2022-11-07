package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.*;

/**
 * Example OpMode. Demonstrates use of gyro, color sensor, encoders, and telemetry.
 *
 */
@TeleOp(name = "mecanum bot demo", group = "MecanumBot")
public class AshwinTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    public void runOpMode(){
//        DcMotor m1 = hardwareMap.dcMotor.get("back_left_motor");
//        DcMotor m2 = hardwareMap.dcMotor.get("front_left_motor");
//        DcMotor m3 = hardwareMap.dcMotor.get("front_right_motor");
//        DcMotor m4 = hardwareMap.dcMotor.get("back_right_motor");
//
//        m1.setDirection(DcMotor.Direction.REVERSE);
//        m2.setDirection(DcMotor.Direction.REVERSE);
//        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        m2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        m3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        m4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        DistanceSensor frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");
        DistanceSensor leftDistance = hardwareMap.get(DistanceSensor.class, "left_distance");
        DistanceSensor rightDistance = hardwareMap.get(DistanceSensor.class, "right_distance");
        DistanceSensor backDistance = hardwareMap.get(DistanceSensor.class, "back_distance");

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.accelerationIntegrationAlgorithm = null;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationData = null;
        parameters.calibrationDataFile = "";
        parameters.loggingEnabled = false;
        parameters.loggingTag = "Who cares.";
        imu.initialize(parameters);

        ColorSensor colorSensor = hardwareMap.colorSensor.get("color_sensor");
        telemetry.addData("Press Start When Ready","");
        telemetry.update();

        /*waitForStart();
        while (opModeIsActive()){
            double px = gamepad1.left_stick_x;
            double py = -gamepad1.left_stick_y;
            double pa = gamepad1.left_trigger - gamepad1.right_trigger;

            if (Math.abs(pa) < 0.05) pa = 0;
            double p1 = -px + py - pa;
            double p2 = px + py + -pa;
            double p3 = -px + py + pa;
            double p4 = px + py + pa;
            double max = Math.max(1.0, Math.abs(p1));
            max = Math.max(max, Math.abs(p2));
            max = Math.max(max, Math.abs(p3));
            max = Math.max(max, Math.abs(p4));
            p1 /= max;
            p2 /= max;
            p3 /= max;
            p4 /= max;
            m1.setPower(p1);
            m2.setPower(p2);
            m3.setPower(p3);
            m4.setPower(p4);
            telemetry.addData("Color","R %d  G %d  B %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
            Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
            telemetry.addData("Heading", " %.1f", orientation.firstAngle * 180.0 / Math.PI);

            telemetry.addData("Front Distance", " %.1f", frontDistance.getDistance(DistanceUnit.CM));
            telemetry.addData("Left Distance", " %.1f", leftDistance.getDistance(DistanceUnit.CM));
            telemetry.addData("Right Distance", " %.1f", rightDistance.getDistance(DistanceUnit.CM));
            telemetry.addData("Back Distance", " %.1f", backDistance.getDistance(DistanceUnit.CM));
            telemetry.addData("Encoders"," %d %d %d %d", m1.getCurrentPosition(), m2.getCurrentPosition(),
                    m3.getCurrentPosition(), m4.getCurrentPosition());
            telemetry.update();
        }
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
*/
        frontLeft  = hardwareMap.get(DcMotor.class, "front_left_motor");
        backLeft  = hardwareMap.get(DcMotor.class, "back_left_motor");
        frontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        backRight = hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while(opModeIsActive()){

            telemetry.addData("right_stick_x", gamepad1.right_stick_x);
            telemetry.addData("right_stick_y", gamepad1.right_stick_y);
            telemetry.update();
            //RIGHT STICK
            if(-gamepad1.right_stick_y < 0){ //move forward
                telemetry.addData("move", "forward");
                frontLeft.setPower(-1.0);
                //frontRight.setPower(-1.0);
                //backLeft.setPower(-1.0);
                backRight.setPower(-1.0);
            }
            else if(-gamepad1.right_stick_y > 0){ //move backward
                telemetry.addData("move", "backward");
                frontLeft.setPower(1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(1.0);
            }
            if(gamepad1.right_stick_x > 0){ //move right
                telemetry.addData("move", "right");
                frontLeft.setPower(-1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(-1.0);
            }
            else if(gamepad1.right_stick_x < 0){ //move left
                telemetry.addData("move", "left");
                frontLeft.setPower(1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(1.0);
            }
            //LEFT STICK
            else if(gamepad1.left_stick_x < 0){ //rotate left
                telemetry.addData("rotate", "left");
                frontLeft.setPower(1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(1.0);
                backRight.setPower(-1.0);
            }
            else if(gamepad1.left_stick_x > 0) { //rotate right
                telemetry.addData("rotate", "right");
                frontLeft.setPower(-1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(1.0);
            }
            else{ //stop
                telemetry.addData("move", "stop");
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
        }

       /* frontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
        backLeft = hardwareMap.get(DcMotor.class, "back_left_motor");
        frontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        backRight = hardwareMap.get(DcMotor.class, "back_right_motor");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Mecanum", "Demo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            // Right joystick to go forward & strafe, and left joystick to rotate.
            double axial = gamepad1.right_stick_y;
            double lateral = -gamepad1.right_stick_x;
            double yaw = -gamepad1.left_stick_x; // Note: pushing stick forward gives NEG value
            double max;

            // This ensures that the robot maintains the desired motion.
            double frontLeftPower = axial + lateral + yaw;
            double frontRightPower = axial - lateral - yaw;
            double backLeftPower = axial - lateral + yaw;
            double backRightPower = axial + lateral - yaw;


            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            // Normalize the values so no wheel power exceeds 100%
            if (max > 1.0) {
                frontLeftPower /= max;
                frontRightPower /= max;
                backLeftPower /= max;
                backRightPower /= max;
            }
            /*
            if (gamepad1.dpad_up) {
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
            }
            if (gamepad1.dpad_right) {
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(-frontRightPower);
                backLeft.setPower(-backLeftPower);
                backRight.setPower(backRightPower);
            }
            if (gamepad1.dpad_down) {
                frontLeft.setPower(-frontLeftPower);
                frontRight.setPower(-frontRightPower);
                backLeft.setPower(-backLeftPower);
                backRight.setPower(-backRightPower);
            }
            if (gamepad1.dpad_left) {
                frontLeft.setPower(-frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(-backRightPower);
            }
            if (gamepad1.left_stick_x > 0) {
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(-frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(-backRightPower);
            }
            if (gamepad1.left_stick_x < 0) {
                frontLeft.setPower(-1);
                frontRight.setPower(1);
                backLeft.setPower(-1);
                backRight.setPower(1);
            }
            if (gamepad1.right_stick_x > 0) {
                frontLeft.setPower(1);
                frontRight.setPower(-1);
                backLeft.setPower(-1);
                backRight.setPower(1);
            }
            if (gamepad1.right_stick_x < 0) {
                frontLeft.setPower(-1);
                frontRight.setPower(1);
                backLeft.setPower(-1);
                backRight.setPower(1);
            }
            if (gamepad1.right_stick_y > 0) {
                frontLeft.setPower(1);
                frontRight.setPower(1);
                backLeft.setPower(1);
                backRight.setPower(1);
            }
            if (gamepad1.right_stick_y < 0) {
                frontLeft.setPower(-1);
                frontRight.setPower(-1);
                backLeft.setPower(-1);
                backRight.setPower(-1);
            }


        } */

    }
}


