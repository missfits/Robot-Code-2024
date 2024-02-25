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
    private boolean beam_break = false;
    
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
        if (beam_break) { // if the beam has been broken run the indexer down a bit
            m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_SPEED_DOWN);
        } else { // otherwise intake
            m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_SPEED_UP);
            m_intake.runIntakeMotor(IntakeConstants.INTAKE_MOTOR_SPEED_IN);
        }

        // once the beam has been broken, switch flag to true and reset encoders on indexer
        if (!m_indexer.getBeamBreak() && !beam_break) {
            beam_break = true;
            m_indexer.setEncoderPosition(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.indexerOff();
        m_intake.intakeOff();
    }

    @Override
    public boolean isFinished() {
        // if the beam is broken and if the backwards command has run for enough encoder rotations, end command
        return (beam_break && (Math.abs(m_indexer.getEncoderPosition()) > IndexerConstants.BACKUP_DISTANCE)); 
    }
}
