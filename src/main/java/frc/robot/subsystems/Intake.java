package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.IntakeConstants;

// Helper class to set speed of intake and pivot intake motors
public class Intake extends Subsystem{
    public final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR_PORT, MotorType.kBrushless);
    public final CANSparkMax m_pivotIntakeMotor = new CANSparkMax(IntakeConstants.PIVOT_MOTOR_PORT, MotorType.kBrushless);
    
    // Initializes the Intake class
    public Intake(){

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
