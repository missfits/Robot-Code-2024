package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.Constants.DrivetrainConstants;
import frc.robot.OI;

/**
 * Subsystem to control the drivetrain.
 */
public class Drivetrain extends SubsystemBase {
    // Instance variables
    private final OI m_humanControl;
    public static DifferentialDrive m_robotDrive;

    // Motors
    // Sets up a CAN-enabled SPARK MAX motor controller for each motor (left primary, left
    // secondary, right primary, and right secondary)
    public final CANSparkMax m_leftPrimary = new CANSparkMax(
        DrivetrainConstants.LEFT_MOTOR_1_PORT, MotorType.kBrushless);
    private final CANSparkMax m_leftSecondary = new CANSparkMax(
        DrivetrainConstants.LEFT_MOTOR_2_PORT, MotorType.kBrushless);
    private final CANSparkMax m_rightPrimary = new CANSparkMax(
        DrivetrainConstants.RIGHT_MOTOR_1_PORT, MotorType.kBrushless);
    private final CANSparkMax m_rightSecondary = new CANSparkMax(
        DrivetrainConstants.RIGHT_MOTOR_2_PORT, MotorType.kBrushless);

    // Encoders
    public final SparkMaxRelativeEncoder m_leftPrimaryEncoder = (SparkMaxRelativeEncoder)
        m_leftPrimary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor,
        DrivetrainConstants.COUNTS_PER_REV);
    public final SparkMaxRelativeEncoder m_leftSecondaryEncoder = (SparkMaxRelativeEncoder)
        m_leftSecondary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor,
        DrivetrainConstants.COUNTS_PER_REV);
    public final SparkMaxRelativeEncoder m_rightPrimaryEncoder = (SparkMaxRelativeEncoder)
        m_rightPrimary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor,
        DrivetrainConstants.COUNTS_PER_REV);
    public final SparkMaxRelativeEncoder m_rightSecondaryEncoder = (SparkMaxRelativeEncoder)
        m_rightSecondary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor,
        DrivetrainConstants.COUNTS_PER_REV);

    // Groups
    // Controls multiple motors at once (for multiple motors per side of the drivetrain)
    private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftPrimary,
            m_leftSecondary);
    private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightPrimary,
            m_rightSecondary);
    
    /**
     * Constructs a Drivetrain subsystem.
     */
    public Drivetrain(OI humanControl) {
        m_robotDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
        m_humanControl = humanControl;

        configDrivetrainMotors();
    }

    /**
     * Method called once every scheduler run (not being used right now).
     */
    @Override
    public void periodic() {
        // Typically used for telemetry and other periodic actions that do not interfere with
        // commands
    }

    /**
     * Method called once every scheduler run during simulation (not being used right now).
     */
    @Override
    public void simulationPeriodic() {
        // Can be used to update state of robot
    }

    /**
     * Sets up motors during construction.
     */
    //
    public void configDrivetrainMotors() {
        // Makes the secondary motors follow the primary ones
        m_leftSecondary.follow(m_leftPrimary);
        m_rightSecondary.follow(m_rightPrimary);

        // Inverts the right side to account for the fact that that side initially moves backwards
        // for positive velocity and forwards for negative
        m_leftGroup.setInverted(false);
        m_rightGroup.setInverted(true);
    }

    /**
     * Gets left primary encoder position.
     * 
     * @return  current encoder position (in "rotations")
     */
    public double getLeftEncoderPosition() {
        return m_leftPrimaryEncoder.getPosition();
    }
    
    /**
     * Gets right primary encoder position.
     * 
     * @return  current encoder position (in "rotations")
     */
    public double getRightEncoderPosition() {
        return m_rightPrimaryEncoder.getPosition();
    }

    /**
     * Gets left primary encoder velocity.
     * 
     * @return  current encoder velocity (in RPM)
     */
    public double getLeftEncoderVelocity() {
        return m_leftPrimaryEncoder.getVelocity();
    }

    /**
     * Gets right primary encoder velocity.
     * 
     * @return  current encoder velocity (in RPM)
     */
    public double getRightEncoderVelocity() {
        return m_rightPrimaryEncoder.getVelocity();
    }

    // Ensures given value is within a specified range
    public double clamp(double val, double min, double max) {
        if (val > max) {
            System.out.println("out of range");
            return max;
        } else if (val < min) {
            System.out.println("out of range");
            return min;
        }
        return val;
    }

    // Arcade drive
    public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
        m_robotDrive.arcadeDrive(forwardSpeed, rotationSpeed);
    }
}
