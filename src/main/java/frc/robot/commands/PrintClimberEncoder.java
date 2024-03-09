// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LeftClimber;

/** An example command that uses an example subsystem. */
public class PrintClimberEncoder extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final LeftClimber m_climber;

  public PrintClimberEncoder(LeftClimber climber) {
    m_climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    System.out.println(m_climber.getLeftEncoderPosition());
    // System.out.println(m_climber.getRightEncoderPosition());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
