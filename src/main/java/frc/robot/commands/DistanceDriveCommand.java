// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DrivetrainConstants;

//import java.lang.Math.*;

import edu.wpi.first.wpilibj2.command.Command;


/** A dead reckoning taxi command that uses the drivetrain subsystem. */
public class DistanceDriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;
  // private double m_rightEncoderStart;
  // private double m_leftEncoderStart;
  private double m_targetDistance;

  /** 
   * A dead reckoning drive command.
   * Takes in target distance in meters and drives straight that amount.
   */ 
  public DistanceDriveCommand(Drivetrain drivetrain, double targetDistance) {
    System.out.println("targetDistance: " + targetDistance);
    m_drivetrain = drivetrain;
    m_targetDistance = targetDistance * DrivetrainConstants.METERS_TO_ROTATIONS; // convert meters to motor rotations
    System.out.println("Target meters: " + targetDistance + ", target rotations: " + m_targetDistance);

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    // gets start positions of encoders
    m_drivetrain.setRightEncoder(0);
    m_drivetrain.setleftEncoder(0);
    // m_rightEncoderStart = m_drivetrain.getRightEncoderPosition();
    // m_leftEncoderStart = m_drivetrain.getLeftEncoderPosition();
  }

  @Override
  public void execute() {
    double thrust = AutoConstants.TAXI_AUTO_SPEED*Math.signum(m_targetDistance); // drives in the direction of targetDistance
    m_drivetrain.tankDrive(thrust, thrust);
    System.out.println("Left encoder position: " + m_drivetrain.getLeftEncoderPosition() + ", Right encoder position: " + m_drivetrain.getRightEncoderPosition());
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stopMotors();
  }

  @Override
  public boolean isFinished() {
    // calculates if either encoder has moved enough to reach the target distance
    return (Math.abs(m_drivetrain.getRightEncoderPosition()) > Math.abs(m_targetDistance)
    || Math.abs(m_drivetrain.getLeftEncoderPosition()) > Math.abs(m_targetDistance)); 
  }
}
