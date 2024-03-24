package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

/* THIS CODE IS PLACEHOLDER CODE AND NEEDS TO BE REPLACED WHEN CLIMBER DESIGN IS FINALIZED */

public class RightClimber extends SubsystemBase {

    private final CANSparkMax m_climberMotorRight = new CANSparkMax(ClimberConstants.CLIMBER_MOTOR_PORT_RIGHT, MotorType.kBrushless);

    private final SparkRelativeEncoder m_climberEncoderRight = (SparkRelativeEncoder) m_climberMotorRight
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, ClimberConstants.COUNTS_PER_REV);

    
    // Constructor for the Climber class
    public RightClimber(){
    }

    // returns right encoder position
    public double getRightEncoderPosition() {
        return m_climberEncoderRight.getPosition();
    }

    public void setRightEncoderPosition(double position) {
        m_climberEncoderRight.setPosition(position);
    }

    // initializes encoder position to 0
    public void initializeEncoderPosition() {
        setRightEncoderPosition(0);
    }

    // runs right motor at given speed
    public void runRightMotor(double speed){
        m_climberMotorRight.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void rightMotorOff() {
        m_climberMotorRight.set(0);
        m_climberMotorRight.stopMotor();
    }

}
