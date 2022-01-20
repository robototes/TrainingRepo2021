package frc.team2412.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.robototes.units.Distance;
import com.robototes.units.UnitTypes;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.ommand.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

// This is an example subsystem. Make sure all subsystems extend Subsystem base and take in all dependencies through a constructor
public class LiftSubsystem extends SubsystemBase implements Loggable{
	// Constants for the example subsystem
	public static class Constants {
		// Example int (@Config allows us to configure the constants)
		@Config.NumberSlider
		public static int DELAY = 3;

		@Condig.NumberSlider
		public static double liftDouble = 3.5;	 

{''	}
	// Enums will go below here
	public enum LiftState{
		UP, DOWN;
	}
    

	// Public so other subsystems can access it, but final so they cant mess with it (@Log allows us to log the object)
	@Log
	public final Solenoid solenoid;

	// Private so other subsystems dont mess with it but this one can
	private ExampleState State;

	// Create the example subsystem
	public LiftSubsystem(Solenoid liftDouble) {
		//Solenoid = example;
		LiftState = LiftState.DOWN;
		LiftState("ExampleSubsystem");
	}
    
	// Run every loop of the robot
	

	// Example methods to be called by commandsS
	public void liftUP() {
		LiftState = LiftState.UP;
	public void liftDOWN() {
		LiftState = LiftState.DOWN;
	}

	// Getter value for a subsystem private variable
	public boolean isUp(){
		return LiftState == LiftState.UP;
	}

	public boolean isDown() {
		return LiftState == LiftState.DOWN;
	}



}?
>