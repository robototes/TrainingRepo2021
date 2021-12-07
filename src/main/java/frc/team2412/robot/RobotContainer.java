package frc.team2412.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team2412.robot.subsystems.IndexSubsystem;

// this is the class for containing all the subsystems of the robot
public class RobotContainer {
	public static class RobotConstants {
		public static boolean INDEX_CONNECTED = true;
	}
	// Subsystems
	IndexSubsystem index;

	public RobotContainer() {
		// create and instance of example subsystem with the device from hardware map if the subsystem is connected
		if (RobotConstants.INDEX_CONNECTED) {
			index
				= new IndexSubsystem(new DigitalInput(0), new DigitalInput(1),
						  new DigitalInput(2), new DigitalInput(3),
						  new DigitalInput(4), new DigitalInput(5),
						  null, null, null);
		}
	}
}
