// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

/** 
 * Initializes the gyro to the current robot angle.
 * Angle is measured as follows:
 *  0 degrees is when the robot's intake side is facing the field, against OUR alliance wall
 *  Positive degrees = clockwise turning, negative degrees = counterclockwise
 */
public class InitializeNavX extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;
  private double m_degrees;

  public InitializeNavX(Drivetrain drivetrain, double degrees) {
    m_drivetrain = drivetrain;
    m_degrees = degrees;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    m_drivetrain.rotationOffset(m_degrees);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
