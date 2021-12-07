package frc.team2412.robot.subsystems;

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

	// Create Turret Subsystem
	public TurretSubsystem(CANSparkMax motor) {
		this.motor = motor;
		turretState = TurretState.STOPPED;
		setName("TurretSubsystem");
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
	
	public void setWithConstraints(int angle, int rotate) {
		if (angle <= TurretConstants.MAXIMUM_POSITION || angle > TurretConstants.MINIMUM_POSITION) {
			stop();
		}
		else {
			if (rotate > 0) {
				rotateLeft();
			}
			else {
				rotateRight();
			}

		}
	}
}

