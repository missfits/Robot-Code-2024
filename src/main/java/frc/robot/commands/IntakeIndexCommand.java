package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.Constants.IndexerConstants;
import frc.robot.subsystems.Intake;
import frc.robot.Constants.IntakeConstants;

/**
 * Runs the intake and indexer wheels upwards (moves note up towards shooter).
 * Ends when the note hits the beam breaker.
 */
public class IntakeIndexCommand extends Command {
    private Indexer m_indexer;
    private Intake m_intake;
    
    public IntakeIndexCommand(Indexer indexer, Intake intake){
        m_indexer = indexer;
        addRequirements(indexer);
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
    }  

    @Override
    public void execute() {
        m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_SPEED_UP);
        m_intake.runIntakeMotor(IntakeConstants.INTAKE_MOTOR_SPEED_IN);

    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.indexerOff();
        m_intake.intakeOff();

    }

    @Override
    public boolean isFinished() {
       return !m_indexer.getBeamBreak(); // if the beam is broken (if the method returns false) end the command
    }
}
