package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Intake;
import frc.robot.Constants.IntakeConstants;

/**
 * Command to run pivot backward. Uses Intake subsystem.
 */
public class PivotBackwardCommand extends Command {
    // Instance variables
    private Intake m_intake;
    private double m_encoderStart;

    /**
     * Constructs a PivotBackwardCommand command.
     */
    public PivotBackwardCommand(Intake intake){
        m_intake = intake;
    }

    /**
     * Method called once per time the command is scheduled.
     */
    @Override
    public void initialize() {
        m_encoderStart = m_intake.getPivotEncoderPosition();
    }

    /**
     * Method called repeatedly while the command is scheduled (every time the scheduler runs).
     */
    @Override
    public void execute() {
        m_intake.runPivotIntakeMotor(-IntakeConstants.PIVOT_MOTOR_SPEED);
    }

    /**
     * Method called once when the command ends (whether it finishes normally or is interrupted).
     */
    @Override
    public void end(boolean interrupted) {
        m_intake.pivotMotorOff();
    }

    /**
     * Method called repeatedly while the command is scheduled (returns false while the command is
     * scheduled, true when the command should end).
     * 
     * @return  whether command is finished
     */
    @Override
    public boolean isFinished() {
        return m_intake.getPivotEncoderPosition() <= m_encoderStart - IntakeConstants.PIVOT_DELTA;
    }
}

