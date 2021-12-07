package frc.team2412.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.subsystems.IndexSubsystem;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexConstants;

public class IndexCenterToTopCommand extends WaitCommand {
    private final IndexSubsystem index;

    public IndexCenterToTopCommand(IndexSubsystem index) {
        super(IndexConstants.TIME_CENTER_TO_TOP);
        this.index = index;
    }

    @Override
    public void execute() {
        index.centerToShooter();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        index.topStop();
    }
}
