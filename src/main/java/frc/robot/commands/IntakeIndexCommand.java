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
        m_intake = intake;
        addRequirements(indexer, intake);
    }

    @Override
    public void initialize() {
        beam_break = false;
        System.out.println("indexer intake  COMMAND STARTED");
    }  

    @Override
    public void execute() {
        System.out.println("intake indexer command running :D");
        // once the beam has been broken, switch flag to true and reset encoders on indexer
        if (!m_indexer.getBeamBreak() && !beam_break) {
            // System.out.println("beam break bool switched");
            beam_break = true;
            m_indexer.setEncoderPosition(0);
        }

        // System.out.println("Encoder value: " + m_indexer.getEncoderPosition());
        if (beam_break) { // if the beam has been broken run the indexer down a bit
            m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_REVERSE_SPEED);
            // System.out.println("Backing up");
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
        // System.out.println("Encoder: " + m_indexer.getEncoderPosition());
    }

    @Override
    public boolean isFinished() {
        // if the beam is broken and if the backwards command has run for enough encoder rotations, end command
        return (beam_break && (Math.abs(m_indexer.getEncoderPosition()) > IndexerConstants.REVERSE_DISTANCE)); 
    }
}
