// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_shooterMotor = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR_PORT, MotorType.kBrushless);
  private final SparkRelativeEncoder m_shooterEncoder = (SparkRelativeEncoder) m_shooterMotor
      .getEncoder(SparkRelativeEncoder.Type.kHallSensor, ShooterConstants.COUNTS_PER_REV);

  public Shooter() {}

  // Sets intake motor speed (forward if positive, backward if negative)
  public void runShooterMotor(double speed) {
    m_shooterMotor.set(speed);
  }

  // Sets intake motor speed to zero and stops motor
  public void shooterOff() {
    m_shooterMotor.set(0);
    m_shooterMotor.stopMotor();
  }
}
