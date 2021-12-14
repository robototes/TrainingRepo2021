package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.robototes.PIDControls.PIDController;
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

        public static final double P = 0.0004;
        public static final double I = 0.000001;
        public static final double D = 0.0001;
        public static final double FF = 0;
	}

	// Enums will go below here
	public enum ShooterSubsystemState{
		ON, OFF;
	}

	// Public so other subsystems can access it, but final so they cant mess with it (@Log allows us to log the object)
	// motors for shooting the balls
    @Log
	public final CANSparkMax leftMotor;
    @Log
    public final CANSparkMax rightMotor;
    // motor for rotating the flywheel
    @Log
    public final CANSparkMax motor3;
    // servos for extending/retracting
    @Log
    public final Servo servo1;
    @Log
    public final Servo servo2;

    public final CANPIDController leftPIDController;
    public final CANPIDController rightPIDController;

	// Private so other subsystems dont mess with it but this one can
	private ShooterSubsystemState shooterSubsystemState;

	// Create the example subsystem
	public ShooterSubsystem(CANSparkMax leftMotor, CANSparkMax rightMotor, CANSparkMax motor3, Servo servo1, Servo servo2) {
        this.leftMotor = leftMotor;
        this.leftPIDController = this.leftMotor.getPIDController();

        this.rightMotor = rightMotor;
        this.rightMotor.setInverted(true);
        this.rightPIDController = this.rightMotor.getPIDController();

        setPIDtoDefault();

        this.motor3 = motor3;

        this.servo1 = servo1;
        this.servo2 = servo2;
		this.shooterSubsystemState = ShooterSubsystemState.OFF;
		setName("ShooterSubsystem");
	}

    public void shooterOn(double speed) {
        // clamp speed between MIN_SHOOT_SPEED and MAX_SHOOT_SPEED
        double correctedSpeed = Math.max(Math.min(speed, ShooterConstants.MAX_SHOOT_SPEED), ShooterConstants.MIN_SHOOT_SPEED);

        this.leftMotor.set(correctedSpeed);
        this.rightMotor.set(correctedSpeed);
        this.shooterSubsystemState = ShooterSubsystemState.ON;
    }

    public void shooterOff() {
        // stop both motors
        this.leftMotor.set(0);
        this.rightMotor.set(0);
        this.shooterSubsystemState = ShooterSubsystemState.OFF;
    }

    public void setRPM(double wantedSpeed) {
        leftPIDController.setReference(wantedSpeed, ControlType.kVelocity);
        rightPIDController.setReference(wantedSpeed, ControlType.kVelocity);
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

    public void setPIDtoDefault() {
        setP(ShooterConstants.P);
        setI(ShooterConstants.I);
        setD(ShooterConstants.D);
        setFF(ShooterConstants.FF);
    }

    public void setP(double p) {
        this.leftPIDController.setP(p);
        this.rightPIDController.setP(p);
    }

    public void setI(double i) {
        this.leftPIDController.setI(i);
        this.rightPIDController.setI(i);
    }

    public void setD(double d) {
        this.leftPIDController.setD(d);
        this.rightPIDController.setD(d);
    }

    public void setFF(double ff) {
        this.leftPIDController.setFF(ff);
        this.rightPIDController.setFF(ff);
    }

}
