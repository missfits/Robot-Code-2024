package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants.IntakeConstants;

/**
 * Subsystem to control the intake.
 */
public class Intake extends SubsystemBase {
    // Motors
    private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR_PORT, MotorType.kBrushless);
    // pivot motor represents the stuff that will control the four bar linkage in the 2024 bot
    private final CANSparkMax m_pivotIntakeMotor = new CANSparkMax(IntakeConstants.PIVOT_MOTOR_PORT, MotorType.kBrushless);

    // Encoder
    private final SparkMaxRelativeEncoder m_pivotEncoder = (SparkMaxRelativeEncoder) m_pivotIntakeMotor
        .getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, IntakeConstants.COUNTS_PER_REV);
    
    /**
     * Constructs an Intake subsystem.
     */
    public Intake() {}

    /**
     * Sets intake motor speed (forward if positive / backward if negative).
     * 
     * @param  speed  desired motor speed (between -1.0 and 1.0)
     */
    public void runIntakeMotor(double speed){
        m_intakeMotor.set(speed);
    }

    /**
     * Sets intake motor speed to 0 and stops motor.
     */
    public void intakeOff() {
      m_intakeMotor.set(0);
      m_intakeMotor.stopMotor();
    }

    /**
     * Gets pivot intake encoder position.
     * 
     * @return  current encoder position (in "rotations")
     */
    public double getPivotEncoderPosition() {
        return m_pivotEncoder.getPosition();
    }

    /**
     * Sets pivot intake encoder position.
     * 
     * @param  position  desired encoder position (in "rotations")
     */
    public void setPivotEncoderPosition(double position) {
        m_pivotEncoder.setPosition(position);
    }

    /**
     * Initializes pivot intake encoder position to 0. MAKE SURE INTAKE IS ALWAYS FULLY UP WHENEVER THIS METHOD IS CALLED.
     */
    public void initializePivotEncoderPosition() {
        setPivotEncoderPosition(0);
    }

    /**
     * Sets pivot intake motor speed (forward if positive / backward if negative).
     * 
     * @param  speed  desired motor speed (between -1.0 and 1.0)
     */
    public void runPivotIntakeMotor(double speed){
        m_pivotIntakeMotor.set(speed);
    }

    /**
     * Sets pivot intake motor speed to 0 and stops motor.
     */
    public void pivotMotorOff() {
      m_pivotIntakeMotor.set(0);
      m_pivotIntakeMotor.stopMotor();
    }
}
