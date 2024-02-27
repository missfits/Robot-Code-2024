package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.Constants.IndexerConstants;

/**
 * Runs the indexer wheels upwards (moves note up towards shooter).
 */
public class IndexerUpCommand extends Command {
    private Indexer m_indexer;
    
    public IndexerUpCommand(Indexer indexer){
        m_indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize() {
        // System.out.println("INDEXER SUCK COMMAND STARTED");
    }  

    @Override
    public void execute() {
        m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_SPEED_UP);
    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.indexerOff();
    }

    @Override
    public boolean isFinished() {
       return false;
    }
}
