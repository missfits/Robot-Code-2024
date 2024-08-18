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
public class AutoIntakeCommand extends Command {
    private Indexer m_indexer;
    private Intake m_intake;
    private boolean beam_break = false;
    
    public AutoIntakeCommand(Indexer indexer, Intake intake){
        m_indexer = indexer;
        m_intake = intake;
        addRequirements(indexer, intake);
    }

    @Override
    public void initialize() {
        beam_break = false;
    }  

    @Override
    public void execute() {
        // once the beam has been broken, switch flag to true and reset encoders on indexer
        if (!m_indexer.getBeamBreak() && !beam_break) {
            //RUMBLE HERE
            beam_break = true;
            m_indexer.setEncoderPosition(0);
        }

        if (beam_break) { // if the beam has been broken run the indexer down a bit
            m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_REVERSE_SPEED);
        } else { // otherwise intake
            m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_SPEED_UP);
            m_intake.runIntakeMotor(IntakeConstants.INTAKE_MOTOR_SPEED_IN);
        }
   
    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.indexerOff();
        m_intake.intakeOff();
        m_indexer.setEncoderPosition(0);
        beam_break = false;
    }

    @Override
    public boolean isFinished() {
        // if the beam is broken and if the backwards command has run for enough encoder rotations, end command
        return (beam_break && (m_indexer.getEncoderPosition() > IndexerConstants.REVERSE_DISTANCE)); 
    }
}
