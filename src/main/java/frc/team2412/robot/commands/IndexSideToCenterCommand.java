package frc.team2412.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.IndexSubsystem;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexSensor;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexSide;

public class IndexSideToCenterCommand extends CommandBase {
    private final IndexSubsystem index;
    private final IndexSide side;
    private final IndexSensor targetSensor;

    public IndexSideToCenterCommand(IndexSubsystem index, IndexSide side) {
        this.index = index;
        this.side = side;
        if (side == IndexSide.FRONT) {
            targetSensor = IndexSensor.BACK_INNER;
        } else {
            targetSensor = IndexSensor.FRONT_INNER;
        }
    }

    @Override
    public void execute() {
        index.moveToCenter(side);
    }

    @Override
    public void end(boolean interrupted) {
        index.stopSide(side);
    }

    @Override
    public boolean isFinished() {
        return index.getReading(targetSensor);
    }
}
