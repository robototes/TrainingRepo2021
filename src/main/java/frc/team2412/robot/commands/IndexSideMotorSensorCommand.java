package frc.team2412.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.team2412.robot.subsystems.IndexSubsystem;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexDirection;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexSensor;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexSide;

public class IndexSideMotorSensorCommand extends WaitUntilCommand {
    private final IndexSubsystem index;
    private final IndexSide side;
    private final IndexDirection dir;

    public IndexSideMotorSensorCommand(IndexSubsystem index, IndexSide side,
                                       IndexDirection dir,
                                       IndexSensor sensor) {
        super(() -> index.getReading(sensor));
        this.index = index;
        this.side = side;
        this.dir = dir;
    }

    @Override
    public void execute() {
        if (dir == IndexDirection.IN) {
            index.moveToCenter(side);
        } else {
            index.moveFromCenter(side);
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        index.stopSide(side);
    }
}
