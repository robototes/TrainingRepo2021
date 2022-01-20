package frc.team2412.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.DrivebaseSubsystem;

public class DriveCommand extends CommandBase {
    
    private DrivebaseSubsystem subsystem;
    
    public DriveCommand(DrivebaseSubsystem subsystem) {
        addRequirements(subsystem);
        this.subsystem = subsystem;
    }

    @Override
    public void execute() {
        subsystem.drive();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
