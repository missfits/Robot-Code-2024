// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

// ***NEED TO BE UPDATED FOR 2024 SEASON***

public class Intake extends SubsystemBase {

    // instance variables
    private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR_PORT,
        MotorType.kBrushless);
    private final SparkRelativeEncoder m_intakeEncoder = (SparkRelativeEncoder)
        m_intakeMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, IntakeConstants.COUNTS_PER_REV);

    // constructor
    public Intake() {}

    // Sets intake motor speed (forward if positive, backward if negative)
    public void runIntakeMotor(double speed) {
        m_intakeMotor.set(speed);
    }

    // Sets intake motor speed to zero and stops motor
    public void intakeOff() {
        m_intakeMotor.set(0);
        m_intakeMotor.stopMotor();
    }

    // returns encoder position
    public double getEncoderPosition() {
        return m_intakeEncoder.getPosition();
    }

    // sets encoder to desired position
    public void setEncoderPosition(double position) {
        m_intakeEncoder.setPosition(position);
    }

    // returns encoder velocity
    public double getEncoderVelocity() {
        return m_intakeEncoder.getVelocity();
    }
}