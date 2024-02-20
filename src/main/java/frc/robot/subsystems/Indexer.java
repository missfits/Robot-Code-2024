// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class Indexer extends SubsystemBase {

  // instance variables
  private final CANSparkMax m_indexerMotor = new CANSparkMax(IndexerConstants.INDEXER_MOTOR_PORT, MotorType.kBrushless);
  private final SparkRelativeEncoder m_indexerEncoder = (SparkRelativeEncoder) m_indexerMotor
      .getEncoder(SparkRelativeEncoder.Type.kHallSensor, IndexerConstants.COUNTS_PER_REV);

  public DigitalInput m_input = new DigitalInput(0); // takes in values from the beam breaker

  // constructor
  public Indexer() {}

  // Sets Indexer motor speed (forward if positive, backward if negative)
  public void runIndexerMotor(double speed) {
    m_indexerMotor.set(speed);
  }

  // Sets Indexer motor speed to zero and stops motor
  public void indexerOff() {
    m_indexerMotor.set(0);
    m_indexerMotor.stopMotor();
  }

  // returns encoder position
  public double getEncoderPosition() {
    return m_indexerEncoder.getPosition();
  }

  // sets encoder to desired position
  public void setEncoderPosition(double position) {
      m_indexerEncoder.setPosition(position);
  }

  // returns encoder velocity
  public double getEncoderVelocity() {
    return m_indexerEncoder.getVelocity();
  }

  // returns true if beam is *not* broken, false if no light detected
  public boolean getBeamBreak() {
    return m_input.get();
  }

  // testing method for beam break
  public void printBeamBreak() {
    System.out.println(m_input.get());
  }
}
