package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodConstants;

// ***NEED TO BE UPDATED FOR 2024 SEASON***

public class Hood extends SubsystemBase {

    // instance variables
    private final CANSparkMax m_hoodMotor = new CANSparkMax(HoodConstants.HOOD_MOTOR_PORT, MotorType.kBrushless);
    // pivot motor represents the stuff that will control the four bar linkage in the 2024 bot
    private final CANSparkMax m_pivotHoodMotor = new CANSparkMax(HoodConstants.PIVOT_MOTOR_PORT, MotorType.kBrushless);

    public final SparkRelativeEncoder m_pivotEncoder = (SparkRelativeEncoder) m_pivotHoodMotor
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, HoodConstants.COUNTS_PER_REV);
    public final SparkRelativeEncoder m_hoodEncoder = (SparkRelativeEncoder) m_hoodMotor
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, HoodConstants.COUNTS_PER_REV);
    
    // constructor
    public Hood() {}

    // Sets intake motor speed (forward if positive, backward if negative)
    public void runHoodMotor(double speed) {
        m_hoodMotor.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void hoodOff() {
      m_hoodMotor.set(0);
      m_hoodMotor.stopMotor();
    }

    // returns encoder position
    public double getPivotEncoderPosition() {
        return m_pivotEncoder.getPosition();
    }

    // sets encoder to desired position
    public void setPivotEncoderPosition(double position) {
        m_pivotEncoder.setPosition(position);
    }

    // returns encoder velocity
    public double getEncoderVelocity() {
        return m_hoodEncoder.getVelocity();
    }

    // initializes encoder position to 0
    public void resetPivotEncoderPosition() {
        setPivotEncoderPosition(0); // TO DO: WRITE THE SET FUNCTION
    }

    // Sets pivot intake motor speed (forward if positive, backward if negative)
    public void runPivotHoodMotor(double speed) {
        m_pivotHoodMotor.set(speed);
    }

    // Sets pivot intake motor speed to zero and stops motor
    public void pivotMotorOff() {
      m_pivotHoodMotor.set(0);
      m_pivotHoodMotor.stopMotor();
    }
}
