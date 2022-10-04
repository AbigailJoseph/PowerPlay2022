package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="testTeleopAshwin")
@Disabled
public class AshwinTest extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    @Override
    public void runOpMode() {
        //mapping here
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset(); //resets the

        while(opModeIsActive()) {
            // Right joystick to go forward & strafe, and left joystick to rotate.
            double axial   = gamepad1.right_stick_y;
            double lateral =  -gamepad1.right_stick_x;
            double yaw =  -gamepad1.left_stick_x; // Note: pushing stick forward gives NEG value
            double max;

            // This ensures that the robot maintains the desired motion.
            double frontLeftPower  = axial + lateral + yaw;
            double frontRightPower = axial - lateral - yaw;
            double backLeftPower   = axial - lateral + yaw;
            double backRightPower  = axial + lateral - yaw;


            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            // Normalize the values so no wheel power exceeds 100%
            if (max > 1.0) {
                frontLeftPower  /= max;
                frontRightPower /= max;
                backLeftPower   /= max;
                backRightPower  /= max;
            }
            if(gamepad1.dpad_up)
            {
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
            }
            if(gamepad1.dpad_right)
            {
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(-frontRightPower);
                backLeft.setPower(-backLeftPower);
                backRight.setPower(backRightPower);
            }
            if(gamepad1.dpad_down)
            {
                frontLeft.setPower(-frontLeftPower);
                frontRight.setPower(-frontRightPower);
                backLeft.setPower(-backLeftPower);
                backRight.setPower(-backRightPower);
            }
            if(gamepad1.dpad_left)
            {
                frontLeft.setPower(-frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(-backRightPower);
            }
            if(gamepad1.left_stick_x > 0)
            {
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(-frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(-backRightPower);
            }
            if(gamepad1.left_stick_x < 0)
            {
                frontLeft.setPower(-1);
                frontRight.setPower(1);
                backLeft.setPower(-1);
                backRight.setPower(1);
            }
            if(gamepad1.right_stick_x > 0)
            {
                frontLeft.setPower(1);
                frontRight.setPower(-1);
                backLeft.setPower(-1);
                backRight.setPower(1);
            }
            if(gamepad1.right_stick_x < 0)
            {
                frontLeft.setPower(-1);
                frontRight.setPower(1);
                backLeft.setPower(-1);
                backRight.setPower(1);
            }
            if(gamepad1.right_stick_y > 0)
            {
                frontLeft.setPower(1);
                frontRight.setPower(1);
                backLeft.setPower(1);
                backRight.setPower(1);
            }
            if(gamepad1.right_stick_y < 0)
            {
                frontLeft.setPower(-1);
                frontRight.setPower(-1);
                backLeft.setPower(-1);
                backRight.setPower(-1);
            }


        }

    }


}


