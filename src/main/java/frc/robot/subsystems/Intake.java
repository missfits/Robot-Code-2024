package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase{
    public final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR_PORT, MotorType.kBrushless);
    public final CANSparkMax m_pivotIntakeMotor = new CANSparkMax(IntakeConstants.PIVOT_MOTOR_PORT, MotorType.kBrushless);
    
    public Intake(){

    }

    public void runIntakeMotorForward(double speed){
        m_intakeMotor.set(speed);
    }

    public void runIntakeMotorBackward(double speed){
        m_intakeMotor.set(speed);
    }

    public void intakeOff() {
      m_intakeMotor.set(0);
      m_intakeMotor.stopMotor();
    }

    public void runPivotIntakeMotorForward(double speed){
        m_pivotIntakeMotor.set(speed);
    }

    public void runPivotIntakeMotorBackward(double speed){
        m_pivotIntakeMotor.set(speed);
    }

    public void pivotMotorOff() {
      m_pivotIntakeMotor.set(0);
      m_pivotIntakeMotor.stopMotor();
    }
}
