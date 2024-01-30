package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.AutoConstants;

/**
 * Command to drive a set distance. Uses Drivetrain subsystem.
 */
public class DistanceDriveCommand extends Command {
    // Instance variables
    private final Drivetrain m_drivetrain;
    private double m_rightEncoderStart;
    private double m_leftEncoderStart;
    private double m_targetDistance;

    /**
     * Constructs a DistanceDriveCommand command.
     */
    public DistanceDriveCommand(Drivetrain drivetrain, double targetDistance) {
        m_drivetrain = drivetrain;
        // Supports both negative and positive targetDistance
        m_targetDistance = targetDistance;
        addRequirements(drivetrain);
    }

    /**
     * Method called once per time the command is scheduled.
     */
    @Override
    public void initialize() {
        // Gets start positions of encoders
        m_rightEncoderStart = m_drivetrain.getRightEncoderPosition();
        m_leftEncoderStart = m_drivetrain.getLeftEncoderPosition();
    }

    /**
     * Method called repeatedly while the command is scheduled (every time the scheduler runs).
     */
    @Override
    public void execute() {
        m_drivetrain.arcadeDrive(AutoConstants.TAXI_AUTO_SPEED*Math.signum(m_targetDistance), 0); // drives in the direction of targetDistance 
    }

    /**
     * Method called once when the command ends (whether it finishes normally or is interrupted).
     */
    @Override
    public void end(boolean interrupted) {}

    /**
     * Method called repeatedly while the command is scheduled (returns false while the command is
     * scheduled, true when the command should end).
     * 
     * @return  whether command is finished
     */
    @Override
    public boolean isFinished() {
        // Calculates if either encoder has moved enough to reach the target distance
        return (Math.abs(m_drivetrain.getRightEncoderPosition() - m_rightEncoderStart) > 
                        Math.abs(m_targetDistance) || Math.abs(m_drivetrain.getLeftEncoderPosition() -
                        m_leftEncoderStart) >  Math.abs(m_targetDistance)); 
    }
}
