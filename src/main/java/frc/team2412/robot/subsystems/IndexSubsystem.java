package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.robototes.units.Distance;
import com.robototes.units.UnitTypes;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

// This is an example subsystem. Make sure all subsystems extend Subsystem base and take in all dependencies through a constructor
public class IndexSubsystem extends SubsystemBase implements Loggable {
	// Constants for the example subsystem
	public static class IndexConstants {
		// @Config allows us to configure the constants
		@Config.NumberSlider
		public static double INTAKE_TO_CENTER_SPEED = 0.75;

		@Config.NumberSlider
		public static double CENTER_TO_SHOOTER_SPEED = 0.5;

		@Config.NumberSlider
		public static double TIME_INTAKE_TO_MID = 0.75;

		@Config.NumberSlider
		public static double TIME_INTAKE_TO_SIDE = 0.25;

		@Config.NumberSlider
		public static double TIME_CENTER_TO_TOP = 1;
	}

	// Enums will go below here
	public enum IndexSide {
		BACK, FRONT;
	}

	public enum IndexDirection {
		IN, OUT;
	}

	public enum IndexSensor {
		FRONT, FRONT_MID, FRONT_INNER, BACK, BACK_MID, BACK_INNER;
	}

	public enum IndexPosition {
		FRONT, FRONT_MID, TOP, BACK_MID, BACK;
	}

	private final DigitalInput m_backInnerSensor;
	private final DigitalInput m_backMidSensor;
	private final DigitalInput m_backSensor;
	private final DigitalInput m_frontInnerSensor;
	private final DigitalInput m_frontMidSensor;
	private final DigitalInput m_frontSensor;

	private final CANSparkMax m_frontMotor;
	private final CANSparkMax m_backMotor;
	private final CANSparkMax m_upMotor;

	private boolean slotsFilled[];
	private int numBalls;

	public IndexSubsystem(DigitalInput backInnerSensor, DigitalInput backMidSensor,
			DigitalInput backSensor, DigitalInput frontInnerSensor, DigitalInput frontMidSensor,
			DigitalInput frontSensor, CANSparkMax frontMotor, CANSparkMax backMotor,
			CANSparkMax upMotor) {
		m_backInnerSensor = backInnerSensor;
		m_backMidSensor = backMidSensor;
		m_backSensor = backSensor;
		m_frontInnerSensor = frontInnerSensor;
		m_frontMidSensor = frontMidSensor;
		m_frontSensor = frontSensor;
		m_frontMotor = frontMotor;
		m_backMotor = backMotor;
		m_upMotor = upMotor;
		slotsFilled = new boolean[5];
		numBalls = 0;
		setName("IndexSubsystem");
	}

	// Run every loop of the robot
	@Override
	public void periodic() {
		;
	}

	/*
	Instead of having setFrontMotor, setBackMotor, setUpMotor and making the
	callers access the speeds (e.g.,
	setFrontMotor(IndexSubsystem.IndexConstants.INTAKE_TO_CENTER_SPEED))),
	have methods like frontMotorIn, backMotorIn, upMotor
	*/

	/**
	 * Sets the front motor to move a ball to the center.
	 */
	public void frontToCenter() {
		m_frontMotor.set(IndexConstants.INTAKE_TO_CENTER_SPEED);
	}

	/**
	 * Sets the front motor to move a ball from the center to the front.
	 */
	public void centerToFront() {
		m_frontMotor.set(-IndexConstants.INTAKE_TO_CENTER_SPEED);
	}

	/**
	 * Stops the front motor.
	 */
	public void frontStop() {
		m_frontMotor.set(0);
	}

	/**
	 * Sets the back motor's speed to move a ball to the center.
	 */
	public void backToCenter() {
		m_backMotor.set(IndexConstants.INTAKE_TO_CENTER_SPEED);
	}

	/**
	 * Sets the back motor to move a ball from the center to the back.
	 */
	public void centerToBack() {
		m_backMotor.set(-IndexConstants.INTAKE_TO_CENTER_SPEED);
	}

	/**
	 * Stops the back motor.
	 */
	public void backStop() {
		m_backMotor.set(0);
	}

	/**
	 * Sets the motor on the specified side to move a ball to the center.
	 * 
	 * @param side   the side to move the ball from
	 */
	public void moveToCenter(IndexSide side) {
		if (side == IndexSide.FRONT) {
			frontToCenter();
		} else {
			backToCenter();
		}
	}

	/**
	 * Sets the motor on the specified side to move a ball from the center.
	 * 
	 * @param side   the side to move the ball to
	 */
	public void moveFromCenter(IndexSide side) {
		if (side == IndexSide.FRONT) {
			centerToFront();
		} else {
			centerToBack();
		}
	}

	/**
	 * Stops the motor on the specified side.
	 * 
	 * @param side   the side of the motor to stop
	 */
	public void stopSide(IndexSide side) {
		if (side == IndexSide.FRONT) {
			frontStop();
		} else {
			backStop();
		}
	}

	/**
	 * Sets the top motor's speed to move a ball to the shooter.
	 */
	public void centerToShooter() {
		m_upMotor.set(IndexConstants.CENTER_TO_SHOOTER_SPEED);
	}

	/**
	 * Stops the top motor.
	 */
	public void topStop() {
		m_upMotor.set(0);
	}

	/**
	 * Returns the reading of the front sensor.
	 * 
	 * @return   the reading of the front sensor
	 */
	public boolean getFrontReading() {
		return getReading(IndexSensor.FRONT);
	}

	/**
	 * Returns the reading of the front middle sensor.
	 * 
	 * @return   the reading of the front middle sensor
	 */
	public boolean getFrontMidReading() {
		return getReading(IndexSensor.FRONT_MID);
	}

	/**
	 * Returns the reading of the front inner sensor.
	 * 
	 * @return   the reading of the front inner sensor
	 */
	public boolean getFrontInnerReading() {
		return getReading(IndexSensor.FRONT_INNER);
	}

	/**
	 * Returns the reading of the back sensor.
	 * 
	 * @return   the reading of the back sensor
	 */
	public boolean getBackReading() {
		return getReading(IndexSensor.BACK);
	}

	/**
	 * Returns the reading of the back middle sensor.
	 * 
	 * @return   the reading of the back middle sensor
	 */
	public boolean getBackMidReading() {
		return getReading(IndexSensor.BACK_MID);
	}

	/**
	 * Returns the reading of the back inner sensor.
	 * 
	 * @return   the reading of the back inner sensor
	 */
	public boolean getBackInnerReading() {
		return getReading(IndexSensor.BACK_INNER);
	}

	/**
	 * Returns the reading of the given sensor.
	 * 
	 * @param sensor   the sensor to read
	 * @return         the reading of the sensor
	 */
	public boolean getReading(IndexSensor sensor) {
		switch (sensor) {
			case FRONT:
				return m_frontSensor.get();
			case FRONT_MID:
				return m_frontMidSensor.get();
			case FRONT_INNER:
				return m_frontInnerSensor.get();
			case BACK:
				return m_backSensor.get();
			case BACK_MID:
				return m_backMidSensor.get();
			case BACK_INNER:
				return m_backInnerSensor.get();
			default:
				throw new IllegalArgumentException("Unknown IntakeSensor");
		}
	}

	/**
	 * Returns the index of the slot corresponding to the given position.
	 * 
	 * @param pos   the position to convert
	 * @return      the index of the matching slot
	 */
	private int positionToSlot(IndexPosition pos) {
		switch (pos) {
			case TOP:
				return 0;
			case FRONT_MID:
				return 1;
			case BACK_MID:
				return 2;
			case FRONT:
				return 3;
			case BACK:
				return 4;
			default:
				throw new IllegalArgumentException("Unknown position");
		}
	}

	/**
	 * Returns the ball count.
	 * 
	 * @return   the number of balls in index
	 */
	public int getNumBalls() {
		return numBalls;
	}

	/**
	 * Adds a ball at the given position.
	 * 
	 * @param pos   the position to add the ball at
	 */
	public void addBall(IndexPosition pos) {
		slotsFilled[positionToSlot(pos)] = true;
		numBalls++;
	}

	/**
	 * Removes a ball at the given position.
	 * 
	 * @param pos   the position to remove the ball at
	 */
	public void removeBall(IndexPosition pos) {
		slotsFilled[positionToSlot(pos)] = false;
		numBalls++;
	}

	public boolean getTopSlotFilled() {
		return getSlotFilled(IndexPosition.TOP);
	}

	public boolean getFrontMidSlotFilled() {
		return getSlotFilled(IndexPosition.FRONT_MID);
	}

	public boolean getFrontSlotFilled() {
		return getSlotFilled(IndexPosition.FRONT);
	}

	public boolean getBackMidSlotFilled() {
		return getSlotFilled(IndexPosition.BACK_MID);
	}

	public boolean getBackSlotFilled() {
		return getSlotFilled(IndexPosition.BACK);
	}

	public boolean getSlotFilled(IndexPosition pos) {
		return slotsFilled[positionToSlot(pos)];
	}
}
