// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.AutoConstants;


//import java.lang.Math.*;

import edu.wpi.first.wpilibj2.command.Command;


/** An taxi command that uses the drivetrain subsystem. */
public class DistanceDriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;
  private double m_rightEncoderStart;
  private double m_leftEncoderStart;
  private double m_targetDistance;


  public DistanceDriveCommand(Drivetrain drivetrain, double targetDistance) {
    m_drivetrain = drivetrain;
    m_targetDistance = targetDistance; // supports both neg + pos targetDistance
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    m_rightEncoderStart = m_drivetrain.getRightEncoderPosition();
    m_leftEncoderStart = m_drivetrain.getLeftEncoderPosition();
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(AutoConstants.TAXI_AUTO_SPEED*Math.signum(m_targetDistance), 0); // drives in the direction of targetDistance 
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    // calculates if either encoder has moved enough to reach the target distance
    return (Math.abs(m_drivetrain.getRightEncoderPosition() - m_rightEncoderStart) >  Math.abs(m_targetDistance)
    || Math.abs(m_drivetrain.getLeftEncoderPosition() - m_leftEncoderStart) >  Math.abs(m_targetDistance)); 
  }
}
