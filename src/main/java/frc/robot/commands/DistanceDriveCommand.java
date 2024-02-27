package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DrivetrainConstants;

//import java.lang.Math.*;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * Command to drive a set distance. Uses Drivetrain subsystem.
 */
public class DistanceDriveCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain m_drivetrain;
    private double m_targetDistance;

    /** 
     * A dead reckoning drive command.
     * Takes in target distance in meters and drives straight that amount.
     */ 
    public DistanceDriveCommand(Drivetrain drivetrain, double targetDistance) {
        m_drivetrain = drivetrain;
        m_targetDistance = targetDistance * DrivetrainConstants.METERS_TO_ROTATIONS; // convert meters to motor rotations
        // System.out.println("Target meters: " + targetDistance + ", target rotations: " + m_targetDistance);

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // resets positions of encoders
        m_drivetrain.setRightEncoder(0);
        m_drivetrain.setleftEncoder(0);
    }

    @Override
    public void execute() {
        double thrust = AutoConstants.TAXI_AUTO_SPEED*Math.signum(m_targetDistance); // drives in the direction of targetDistance
        m_drivetrain.tankDrive(-thrust, -thrust);
        // System.out.println("Left encoder position: " + m_drivetrain.getLeftEncoderPosition() + ", Right encoder position: " + m_drivetrain.getRightEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stopMotors();
        m_drivetrain.setRightEncoder(0);
        m_drivetrain.setleftEncoder(0);
    }

    @Override
    public boolean isFinished() {
        // calculates if either encoder has moved enough to reach the target distance
        return (Math.abs(m_drivetrain.getRightEncoderPosition()) > Math.abs(m_targetDistance)
        || Math.abs(m_drivetrain.getLeftEncoderPosition()) > Math.abs(m_targetDistance)); 
    }
}
