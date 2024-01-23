package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodConstants;

public class Hood extends SubsystemBase {

    // instance variables
    private final CANSparkMax m_hoodMotor = new CANSparkMax(HoodConstants.HOOD_MOTOR_PORT, MotorType.kBrushless);
    // pivot motor represents the stuff that will control the four bar linkage in the 2024 bot
    private final CANSparkMax m_pivotIntakeMotor = new CANSparkMax(HoodConstants.PIVOT_MOTOR_PORT, MotorType.kBrushless);

    private final SparkRelativeEncoder m_pivotEncoder = (SparkRelativeEncoder) m_pivotIntakeMotor
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, HoodConstants.COUNTS_PER_REV);
    private final SparkRelativeEncoder m_hoodEncoder = (SparkRelativeEncoder) m_hoodMotor
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, HoodConstants.COUNTS_PER_REV);
    
    // constructor
    public Hood() {}

    // returns encoder position
    public double getPivotEncoderPosition() {
        return m_pivotEncoder.getPosition();
    }

    // sets encoder to desired position
    public void setPivotEncoderPosition(double position) {
        m_pivotEncoder.setPosition(position);
    }

    // initializes encoder position to 0 - MAKE SURE INTAKE IS ALWAYS FULLY UP WHENEVER THIS METHOD IS CALLED
    public void initializePivotEncoderPosition() {
        setPivotEncoderPosition(0); // TO DO: WRITE THE SET FUNCTION
    }

    // Sets intake motor speed (forward if positive, backward if negative)
    public void runIntakeMotor(double speed) {
        m_hoodMotor.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void intakeOff() {
      m_hoodMotor.set(0);
      m_hoodMotor.stopMotor();
    }

    // Sets pivot intake motor speed (forward if positive, backward if negative)
    public void runPivotIntakeMotor(double speed) {
        m_pivotIntakeMotor.set(speed);
    }

    // Sets pivot intake motor speed to zero and stops motor
    public void pivotMotorOff() {
      m_pivotIntakeMotor.set(0);
      m_pivotIntakeMotor.stopMotor();
    }
}
