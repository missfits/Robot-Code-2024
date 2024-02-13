// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class Indexer extends SubsystemBase {

  // instance variables
  private final CANSparkMax m_IndexerMotor = new CANSparkMax(IndexerConstants.INDEXER_MOTOR_PORT, MotorType.kBrushless);
  private final SparkRelativeEncoder m_IndexerEncoder = (SparkRelativeEncoder) m_IndexerMotor
      .getEncoder(SparkRelativeEncoder.Type.kHallSensor, IndexerConstants.COUNTS_PER_REV);

  // constructor
  public Indexer() {}

  // Sets Indexer motor speed (forward if positive, backward if negative)
  public void runIndexerMotor(double speed) {
    m_IndexerMotor.set(speed);
  }

  // Sets Indexer motor speed to zero and stops motor
  public void IndexerOff() {
    m_IndexerMotor.set(0);
    m_IndexerMotor.stopMotor();
  }
}
