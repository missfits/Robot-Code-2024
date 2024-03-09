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
    }

    // returns encoder position
    public double getLeftEncoderPosition() {
        return m_climberEncoderLeft.getPosition();
    }

    public double getRightEncoderPosition() {
        return m_climberEncoderRight.getPosition();
    }

    // sets encoder to desired position
    public void setLeftEncoderPosition(double position) {
        m_climberEncoderLeft.setPosition(position);
    }

    public void setRightEncoderPosition(double position) {
        m_climberEncoderRight.setPosition(position);
    }

    // initializes encoder position to 0
    public void initializeEncoderPosition() {
        setLeftEncoderPosition(0);
        setRightEncoderPosition(0);
    }

    // Sets intake motor speed (forward if positive, backward if negative)
    public void runLeftMotor(double speed){
        m_climberMotorLeft.set(speed);
    }

    public void runRightMotor(double speed){
        m_climberMotorRight.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void leftMotorOff() {
        m_climberMotorLeft.set(0);
        m_climberMotorLeft.stopMotor();
    }

    public void rightMotorOff() {
        m_climberMotorRight.set(0);
        m_climberMotorRight.stopMotor();
    }

}
