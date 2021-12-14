package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.robototes.units.Distance;
import com.robototes.units.UnitTypes;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;
import edu.wpi.first.wpilibj.Servo;

import java.lang.Math;

// This is an example subsystem. Make sure all subsystems extend Subsystem base and take in all dependencies through a constructor
public class ShooterSubsystem extends SubsystemBase implements Loggable{
	// Constants for the example subsystem
	public static class ShooterConstants {
		// Example double
		@Config.NumberSlider
		public static double MAX_SHOOT_SPEED = 5.0;
        @Config.NumberSlider
        public static double MIN_SHOOT_SPEED = 0.5;

        @Config.NumberSlider
        public static double MAX_ROTATION_SPEED = 5.0;
        @Config.NumberSlider
        public static double MIN_ROTATION_SPEED = 0.5;
	}

	// Enums will go below here
	public enum ShooterSubsystemState{
		ON, OFF;
	}

	// Public so other subsystems can access it, but final so they cant mess with it (@Log allows us to log the object)
	// motors for shooting the balls
    @Log
	public final CANSparkMax motor1;
    @Log
    public final CANSparkMax motor2;
    // motor for rotating the flywheel
    @Log
    public final CANSparkMax motor3;
    // servos for extending/retracting
    @Log
    public final Servo servo1;
    @Log
    public final Servo servo2;

	// Private so other subsystems dont mess with it but this one can
	private ShooterSubsystemState shooterSubsystemState;

	// Create the example subsystem
	public ShooterSubsystem(CANSparkMax motor1, CANSparkMax motor2, CANSparkMax motor3, Servo servo1, Servo servo2) {
		this.motor1 = motor1;
        this.motor2 = motor2;
        this.motor2.setInverted(true);

        this.motor3 = motor3;

        this.servo1 = servo1;
        this.servo2 = servo2;
		this.shooterSubsystemState = ShooterSubsystemState.OFF;
		setName("ShooterSubsystem");
	}

    public void shooterOn(double speed) {
        // clamp speed between MIN_SHOOT_SPEED and MAX_SHOOT_SPEED
        double correctedSpeed = Math.max(Math.min(speed, ShooterConstants.MAX_SHOOT_SPEED), ShooterConstants.MIN_SHOOT_SPEED);

        this.motor1.set(correctedSpeed);
        this.motor2.set(correctedSpeed);
        this.shooterSubsystemState = ShooterSubsystemState.ON;
    }

    public void shooterOff() {
        // stop both motors
        this.motor1.set(0);
        this.motor2.set(0);
        this.shooterSubsystemState = ShooterSubsystemState.OFF;
    }

    public void setHood(double degrees) {
        // maybe this will work? ask bob
        this.servo1.setAngle(degrees);
        this.servo2.setAngle(-degrees);
    }

    public void rotate(double speed) {
        // clamp speed between MIN_ROTATION_SPEED and MAX_ROTATION_SPEED
        double correctedSpeed = Math.max(Math.min(speed, ShooterConstants.MAX_ROTATION_SPEED), ShooterConstants.MIN_ROTATION_SPEED);

        this.motor3.set(correctedSpeed);
    }

	// Run every loop of the robot
	@Override
	public void periodic() {
		if(isOn()) System.out.println("Subsystem "+getName()+" says hi.");
	}

	// Example methods to be called by commands
	public void enable(double value) {
		shooterSubsystemState = ShooterSubsystemState.ON;
	}
	public void disable() {
		shooterSubsystemState = ShooterSubsystemState.OFF;
	}

	// Getter value for a subsystem private variable
	public boolean isOn(){
		return shooterSubsystemState == ShooterSubsystemState.ON;
	}



}
