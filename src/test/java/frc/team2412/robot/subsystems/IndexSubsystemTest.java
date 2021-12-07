package frc.team2412.robot.subsystems;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.commands.IndexIntakeCommand;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexSide;

// This is an example test of the robot. This is to make sure that everything is working as intended before code goes on a robot.
public class IndexSubsystemTest {

	// Mock instance of Example Subsystem
	IndexSubsystem mockedExampleSubsystem;

	// This method is run before the tests begin. initialize all mocks you wish to
	// use in multiple functions here. Copy and paste this function in your own test
	@Before
	public void before() {
		TestWithScheduler.schedulerStart();
		TestWithScheduler.schedulerClear();
		MockHardwareExtension.beforeAll();

		mockedExampleSubsystem = mock(IndexSubsystem.class);
	}

	// This test makes sure that the example command calls the .subsystemMethod of
	// example subsystem
	@Test
	public void ExampleCommandOnExampleSubsystem() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedExampleSubsystem);

		// Create command
		IndexIntakeCommand exampleCommand
            = new IndexIntakeCommand(mockedExampleSubsystem, IndexSide.BACK);

		// Create a fake button that will be "pressed"
		MockButton fakeButton = new MockButton();

		// Tell the button to run example command when pressed
		fakeButton.whenPressed(exampleCommand);

		// Push the button and run the scheduler once
		fakeButton.push();
		CommandScheduler.getInstance().run();
		fakeButton.release();

		// Verify that subsystemMethod was called once
		verify(mockedExampleSubsystem, times(1)).moveToCenter(IndexSide.BACK);;

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	// This test makes sure that periodic is called properly (odd case as this
	// should already work, but you may want to test methods inside of periodic)
	@Test
	public void ExampleSubsystemCallsPeriodic() {
		// Reset the subsystem to make sure all mock values are reset
		reset(mockedExampleSubsystem);

		// Make sure that the scheduler has the subsystem registered
		CommandScheduler.getInstance().registerSubsystem(mockedExampleSubsystem);

		// run the scheduler once
		CommandScheduler.getInstance().run();

		// Verify that periodic was called once
		verify(mockedExampleSubsystem, times(1)).periodic();

		// Clear the scheduler
		TestWithScheduler.schedulerClear();
	}

	// This is called after tests, and makes sure that nothing is left open and
	// everything is ready for the next test class
	@After
	public void after() {
		TestWithScheduler.schedulerDestroy();
		MockHardwareExtension.afterAll();
	}

}
