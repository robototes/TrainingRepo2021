package frc.team2412.robot.subsystems;

import java.beans.Encoder;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.robototes.units.Distance;
import com.robototes.units.UnitTypes;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

// Turret Subsystem
public class TurretSubsystem extends SubsystemBase implements Loggable {
	// Turret Constants
	public static class TurretConstants {
		// Turret Ints
		
		public static int MINIMUM_POSITION = 0;

		public static int MAXIMUM_POSITION = 2500;
		
		public static int SPEED = 1;

	}
	// Turret Enums
	public enum TurretState{
		MOVE_LEFT, MOVE_RIGHT, STOPPED;
	}

	// motor!
	@Log
	public final CANSparkMax motor;

	// TurretState
	private TurretState turretState;

	// Create Turret Subsystems
	public TurretSubsystem(CANSparkMax motor) {
		this.motor = motor;

		turretState = TurretState.STOPPED;
	}

	// Methods

	public void rotateRight() {
		motor.set(TurretConstants.SPEED);
		turretState = TurretState.MOVE_RIGHT;
	}

	public void rotateLeft() {
		motor.set(-TurretConstants.SPEED);
		turretState = TurretState.MOVE_LEFT;
	}

	public void stop() {
		motor.set(0);
		turretState = TurretState.STOPPED;
	}
	
	public void setWithConstraints(int currentAngle, int rotationAmount) {
		if (currentAngle >= TurretConstants.MAXIMUM_POSITION || currentAngle =< TurretConstants.MINIMUM_POSITION) {
			stop();
		}
		else {
			if (rotationAmount > 0) { 
				rotateRight();
			}
			else if (rotationAmount < 0){
				rotateLeft();
			}
			else {
				stop();
			}

		}
	}
}

