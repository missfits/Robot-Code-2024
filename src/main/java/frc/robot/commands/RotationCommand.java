package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;

public class RotationCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain m_drivetrain;
    // private double m_rightEncoderStart;
    // private double m_leftEncoderStart;
    private double m_targetDegrees;

    /** 
     * A dead reckoning turn command.
     * Takes in target angle in degrees (between -180 and 180) and turns that amount clockwise from current position.
     */ 
    public RotationCommand(Drivetrain drivetrain, double targetDegrees) {
        // System.out.println("targetDistance: " + targetDegrees);
        m_drivetrain = drivetrain;
        m_targetDegrees = targetDegrees * DrivetrainConstants.DEGREES_TO_ROTATIONS; // convert degrees to motor rotations

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // sets initial position to 0
        m_drivetrain.setRightEncoder(0);
        m_drivetrain.setleftEncoder(0);
        // m_rightEncoderStart = m_drivetrain.getRightEncoderPosition();
        // m_leftEncoderStart = m_drivetrain.getLeftEncoderPosition();
    }

    @Override
    public void execute() {
        double thrust = AutoConstants.ROTATION_SPEED*Math.signum(m_targetDegrees); // takes the sign of targetDegrees
        m_drivetrain.tankDrive(thrust, -thrust); // drives in two opposite directions so the robot spins
        System.out.println("Left encoder position: " + m_drivetrain.getLeftEncoderPosition() + ", Right encoder position: " + m_drivetrain.getRightEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stopMotors();
    }

    @Override
    public boolean isFinished() {
        // calculates if either encoder has moved past the target number of rotations
        return (Math.abs(m_drivetrain.getRightEncoderPosition()) > Math.abs(m_targetDegrees)
        || Math.abs(m_drivetrain.getLeftEncoderPosition()) > Math.abs(m_targetDegrees));
    }
}
