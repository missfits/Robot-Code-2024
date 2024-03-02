package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

/* THIS CODE IS PLACEHOLDER CODE AND NEEDS TO BE REPLACED WHEN CLIMBER DESIGN IS FINALIZED */

public class Climber extends SubsystemBase {
    private final CANSparkMax m_climberMotorLeft = new CANSparkMax(ClimberConstants.CLIMBER_MOTOR_PORT_LEFT, MotorType.kBrushless);
    private final CANSparkMax m_climberMotorRight = new CANSparkMax(ClimberConstants.CLIMBER_MOTOR_PORT_RIGHT, MotorType.kBrushless);

    private final SparkRelativeEncoder m_climberEncoderLeft = (SparkRelativeEncoder) m_climberMotorLeft
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, ClimberConstants.COUNTS_PER_REV);
    private final SparkRelativeEncoder m_climberEncoderRight = (SparkRelativeEncoder) m_climberMotorRight
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, ClimberConstants.COUNTS_PER_REV);

    
    // Constructor for the Climber class
    public Climber(){
        m_climberMotorRight.follow(m_climberMotorLeft); // both motors should move exactly the same way
    }

    // returns encoder position
    public double getEncoderPosition() {
        return m_climberEncoderLeft.getPosition();
    }

    // sets encoder to desired position
    public void setEncoderPosition(double position) {
        m_climberEncoderLeft.setPosition(position);
    }

    // initializes encoder position to 0
    public void initializeEncoderPosition() {
        setEncoderPosition(0);
    }

    // Sets intake motor speed (forward if positive, backward if negative)
    public void runMotor(double speed){
        m_climberMotorLeft.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void motorOff() {
      m_climberMotorLeft.set(0);
      m_climberMotorLeft.stopMotor();
    }

}
