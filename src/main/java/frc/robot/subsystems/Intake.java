package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/* THIS CODE IS FOR THE OFFSEASON INTAKE; MUST BE UPDATED WHEN INTAKE DEISGN IS FINALIZED */

public class Intake extends SubsystemBase {
    private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR_PORT, MotorType.kBrushless);
    // pivot motor represents the stuff that will control the four bar linkage in the 2024 bot
    private final CANSparkMax m_pivotIntakeMotor = new CANSparkMax(IntakeConstants.PIVOT_MOTOR_PORT, MotorType.kBrushless);

    private final SparkMaxRelativeEncoder m_pivotEncoder = (SparkMaxRelativeEncoder) m_pivotIntakeMotor
        .getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, IntakeConstants.COUNTS_PER_REV);
    
    // Constructor for the Intake class
    public Intake(){
    }

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
    public void runIntakeMotor(double speed){
        m_intakeMotor.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void intakeOff() {
      m_intakeMotor.set(0);
      m_intakeMotor.stopMotor();
    }

    // Sets pivot intake motor speed (forward if positive, backward if negative)
    public void runPivotIntakeMotor(double speed){
        m_pivotIntakeMotor.set(speed);
    }

    // Sets pivot intake motor speed to zero and stops motor
    public void pivotMotorOff() {
      m_pivotIntakeMotor.set(0);
      m_pivotIntakeMotor.stopMotor();
    }
}
