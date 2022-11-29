package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "color sensor")
public class ColorSensorTest extends LinearOpMode {

    ColorSensor sensor;

    @Override
    public void runOpMode(){
        sensor = hardwareMap.get(ColorSensor.class, "sensor");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Red", sensor.red());
            //telemetry.update();
            telemetry.addData("Blue", sensor.blue());
            //telemetry.update();
            telemetry.addData("Green", sensor.green());
            telemetry.update();
        }
    }
}
