package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeSubsystem extends SubsystemBase implements Loggable{
	public static class IntakeConstants {
		@Config.NumberSlider
		public static double INTAKE_SPEED = 3;
	}
	
	public enum IntakeMotorState{
		IN, OUT, STOPPED;
	}

    public enum IntakeSolenoidState{
        DOWN, UP;
    }

	@Log
	public final CANSparkMax motor;
    @Log
    public final Solenoid solenoid;

	private IntakeMotorState intakeMotorState;
    private IntakeSolenoidState intakeSolenoidState;

	public IntakeSubsystem(CANSparkMax motor, Solenoid solenoid) {
		this.motor = motor;
        this.solenoid = solenoid;
		intakeMotorState = IntakeMotorState.STOPPED;
        intakeSolenoidState = IntakeSolenoidState.UP;
		setName("IntakeSubsystem");
	}

	public boolean isMotorOn() {
        return intakeMotorState == IntakeMotorState.IN || intakeMotorState == IntakeMotorState.OUT;
    }

    public boolean isIntakeDown() {
        return intakeSolenoidState == IntakeSolenoidState.DOWN;
    }

    public void intakeDown() {
        solenoid.set(true);
        intakeSolenoidState = IntakeSolenoidState.DOWN;
    }

    public void intakeUp() {
        solenoid.set(false);
        intakeSolenoidState = IntakeSolenoidState.UP;
    }

    public void intakeIn() {
        motor.set(IntakeConstants.INTAKE_SPEED);
        intakeMotorState = IntakeMotorState.IN;
    }

    public void intakeOut() {
        motor.set(-IntakeConstants.INTAKE_SPEED);
        intakeMotorState = IntakeMotorState.OUT;
    }

    public void intakeStop() {
        motor.set(0);
        intakeMotorState = IntakeMotorState.STOPPED;
    }



}
