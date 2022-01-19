package frc.team2412.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.IndexSubsystem;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexDirection;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexSensor;
import frc.team2412.robot.subsystems.IndexSubsystem.IndexSide;

public class IndexIntakeCommand extends SequentialCommandGroup {
    public IndexIntakeCommand(IndexSubsystem index, IndexSide side) {
        int numBalls = index.getNumBalls();
        if (numBalls == 0) {
            addCommands(new IndexSideToCenterCommand(index, side),
                        new IndexCenterToTopCommand(index));
        } else if (numBalls == 1) {
            if (side == IndexSide.FRONT) {
                addCommands(new IndexSideMotorSensorCommand(index, side,
                                    IndexDirection.IN, IndexSensor.FRONT_INNER));
            } else {
                addCommands(new IndexSideToCenterCommand(index, side),
                            new IndexSideMotorSensorCommand(index, side,
                                    IndexDirection.OUT, IndexSensor.FRONT_INNER));
            }
        } else if (numBalls == 2) {
            if (side == IndexSide.FRONT) {
                // Move ball from position 1 to 2
                // Move new ball into position 1
            } else {
                // Move new ball into position 2
            }
        } else if (numBalls == 3) {
            if (side == IndexSide.FRONT) {
                // Move new ball into position 3
            } else {
                // Move ball from position 1 to position 3
                // Move ball from position 2 to position 1
                // Move new ball into position 2
            }
        } else if (numBalls == 4) {
            if (side == IndexSide.FRONT) {
                // Move ball from position 2 to position 4
                // Move ball from position 1 to position 2
                // Move ball from position 3 to position 1
                // Move new ball into position 3
            } else {
                // Move new ball into posibion 4
            }
        } else {
            /* Too many balls- Do nothing */
        }
    }
}