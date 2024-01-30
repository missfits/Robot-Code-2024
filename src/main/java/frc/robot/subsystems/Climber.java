package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ClimberConstants;

/**
 * Subsystem to control the climber.
 */
public class Climber extends SubsystemBase {
    // Instance variables
    // Motor
    private final CANSparkMax m_climberMotor = new CANSparkMax(ClimberConstants.CLIMBER_MOTOR_PORT,
            MotorType.kBrushless);

    // Encoder
    private final SparkMaxRelativeEncoder m_climberEncoder = (SparkMaxRelativeEncoder)
            m_climberMotor.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor,
            ClimberConstants.COUNTS_PER_REV);
    
    /**
     * Constructs a Climber subsystem.
     */
    public Climber() {}

    /**
     * Gets climber encoder position.
     * 
     * @return  current encoder position (in "rotations")
     */
    public double getclimberEncoderPosition() {
        return m_climberEncoder.getPosition();
    }

    /**
     * Sets climber encoder position.
     * 
     * @param  position  desired encoder position (in "rotations")
     */
    public void setClimberEncoderPosition(double position) {
        m_climberEncoder.setPosition(position);
    }

    /**
     * Initializes climber encoder position to 0.
     */
    public void initializeClimberEncoderPosition() {
        setClimberEncoderPosition(0);
    }

    /**
     * Sets climber motor speed (forward if positive / backward if negative).
     * 
     * @param  speed  desired motor speed (between -1.0 and 1.0)
     */
    public void runClimberMotor(double speed){
        m_climberMotor.set(speed);
    }

    /**
     * Sets climber motor speed to 0 and stops motor.
     */
    public void climberOff() {
        m_climberMotor.set(0);
        m_climberMotor.stopMotor();
    }

}
