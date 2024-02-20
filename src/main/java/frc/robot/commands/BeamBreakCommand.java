package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;

/**
 * Testing command that prints beam break values.
 */
public class BeamBreakCommand extends Command {
    private Indexer m_indexer;
    
    public BeamBreakCommand(Indexer indexer){
        m_indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize() {
    }  

    @Override
    public void execute() {
        m_indexer.printBeamBreak();
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
       return false; // returns value from beam break sensor
    }
}
