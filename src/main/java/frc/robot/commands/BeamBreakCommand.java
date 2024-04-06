package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj.Timer;

/**
 * Testing command that prints beam break values.
 */
public class BeamBreakCommand extends Command {
    private Indexer m_indexer;
    private Timer m_timer;
    
    public BeamBreakCommand(Indexer indexer){
        m_indexer = indexer;
        m_timer = new Timer();
        addRequirements(indexer);
    }

    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();
    }  

    @Override
    public void execute() {
        if (m_timer.get() > 1) {
            m_indexer.printBeamBreak();
            System.out.println(m_indexer.getEncoderPosition());
            m_timer.reset();
            m_timer.start();
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
       return false; // returns value from beam break sensor
    }
}
