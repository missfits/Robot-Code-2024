package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.Drivetrain;

public class NavXRotationCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain m_drivetrain;
    private double m_targetDegrees;
    private double m_startDegrees;
    private double m_thrust;

     /** 
     * A NavX based turn command.
     * Takes in target angle RELATIVE TO FIELD.
     *      0 = facing the field from the alliance wall
     *      Negative values turning left, positive values turning right
     *      All values between -180 and 180.
     */ 
    public NavXRotationCommand(Drivetrain drivetrain, double targetDegrees) {
        m_drivetrain = drivetrain;
        m_targetDegrees = targetDegrees;
        addRequirements(drivetrain);
    }

   @Override
    public void initialize() {
        // sets initial position to 0
        // m_drivetrain.setRightEncoder(0);
        // m_drivetrain.setleftEncoder(0);
        m_startDegrees = m_drivetrain.getDegrees(); // value in degrees between -180 and 180
        // m_rightEncoderStart = m_drivetrain.getRightEncoderPosition();
        // m_leftEncoderStart = m_drivetrain.getLeftEncoderPosition();

        // get appropriate thrust sign
        if (m_startDegrees < m_targetDegrees) {
            if (m_targetDegrees - m_startDegrees < 180) {
                m_thrust = AutoConstants.ROTATION_SPEED;
            } else {
                m_thrust = -AutoConstants.ROTATION_SPEED;
            }
        } else {
            if (m_startDegrees - m_targetDegrees < 180) {
                m_thrust = -AutoConstants.ROTATION_SPEED;
            } else {
                m_thrust = AutoConstants.ROTATION_SPEED;
            }
        }
    }

    @Override
    public void execute() {
        System.out.println("Target degrees: " + m_targetDegrees + ", Current degrees: " + m_drivetrain.getDegrees());
        m_drivetrain.tankDrive(m_thrust, -m_thrust); // drives in two opposite directions so the robot spins
        // System.out.println("Left encoder position: " + m_drivetrain.getLeftEncoderPosition() + ", Right encoder position: " + m_drivetrain.getRightEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stopMotors();
        // m_drivetrain.setRightEncoder(0);
        // m_drivetrain.setleftEncoder(0);
    }

    @Override
    public boolean isFinished() {
        // calculates if either encoder has moved past the target number of rotations
        return Math.abs(m_drivetrain.getDegrees() - m_targetDegrees) < 0.1;
    }
}
