package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;

import frc.team2412.robot.commands.DriveCommand;

public class DrivebaseSubsytem extends SubsystemBase implements Loggable{

    
	public static class DriveConstants {

		public final static int MOTOR_SPEED_MAX = 100;

        public final static int MOTOR_SPEED_MIN = 0;

        public final static double WHEEL_SIZE = 25.36;

        public final static int MOTOR_SPEED_MULTIPLIER = 1000;

	}
    
    public enum DriveGearState {
        UP, DOWN
    }

    public DriveGearState driveBaseState;

    public final Joystick joystick;
    public final Solenoid solenoid;
    public final Gyro gyro;

	public final CANSparkMax motorLeftOne, motorLeftTwo;
    public final CANSparkMax motorRightOne, motorRightTwo;

	public DrivebaseSubsytem(
        CANSparkMax motorLeftOne,
        CANSparkMax motorRightOne,
        CANSparkMax motorLeftTwo,
        CANSparkMax motorRightTwo,
        Solenoid solenoid,
        Joystick joystick,
        Gyro gyro
    ) {

		this.motorLeftOne = motorLeftOne;
        this.motorLeftTwo = motorLeftTwo;
        this.motorRightOne = motorRightOne;
        this.motorRightTwo = motorRightTwo;

        this.solenoid = solenoid;
        this.joystick = joystick;
        this.gyro = gyro;

        motorLeftTwo.follow(motorLeftOne);
        motorRightTwo.follow(motorRightOne);

        motorRightOne.setInverted(true);
        motorRightTwo.setInverted(true);

	}

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand(this));
    }

	@Override
	public void periodic() {
		System.out.println("Subsystem driveBase says hi.");
	}

    public void drive() {
        double rightValue = joystick.getY(Hand.kRight);
        double leftValue = joystick.getY(Hand.kLeft);
        motorRightOne.set(rightValue);
        motorLeftOne.set(leftValue);
    }

    public void shiftGearUp() {
        driveBaseState = DriveGearState.UP;
        solenoid.set(true);
    }

    public void shiftGearDown() {
        driveBaseState = DriveGearState.DOWN;
        solenoid.set(false);
    }

}
