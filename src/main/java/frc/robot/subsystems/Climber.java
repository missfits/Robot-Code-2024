package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

/* THIS CODE IS PLACEHOLDER CODE AND NEEDS TO BE REPLACED WHEN CLIMBER DESIGN IS FINALIZED */

public class Climber extends SubsystemBase {
    private final CANSparkMax m_climberMotor = new CANSparkMax(ClimberConstants.CLIMBER_MOTOR_PORT, MotorType.kBrushless);

    private final SparkRelativeEncoder m_climberEncoder = (SparkRelativeEncoder) m_climberMotor
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, ClimberConstants.COUNTS_PER_REV);
    
    // Constructor for the Intake class
    public Climber(){
    }

    // returns encoder position
    public double getclimberEncoderPosition() {
        return m_climberEncoder.getPosition();
    }

    // sets encoder to desired position
    public void setClimberEncoderPosition(double position) {
        m_climberEncoder.setPosition(position);
    }

    // initializes encoder position to 0 - MAKE SURE INTAKE IS ALWAYS FULLY UP WHENEVER THIS METHOD IS CALLED
    public void initializeClimberEncoderPosition() {
        setClimberEncoderPosition(0); // TO DO: WRITE THE SET FUNCTION
    }

    // Sets intake motor speed (forward if positive, backward if negative)
    public void runClimberMotor(double speed){
        m_climberMotor.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void climberOff() {
      m_climberMotor.set(0);
      m_climberMotor.stopMotor();
    }

}
