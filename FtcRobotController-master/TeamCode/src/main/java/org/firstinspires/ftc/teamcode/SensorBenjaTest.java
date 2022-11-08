package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.RobotHardware;

@Autonomous(name = "Auto", group = "Test")

public class SensorBenjaTest extends LinearOpMode {

    RobotHardware robot = new RobotHardware();
    DcMotor frontLeft = new DcMotor();
    DcMotor frontRight = new DcMotor();
    DcMotor backLeft = new DcMotor();
    DcMotor backRight = new DcMotor();


    @Override
    public void runOpMode() {

        robot.init(HardwareMap);

        waitForStart();

        robot.motor1.setPower(1);
    }
}
