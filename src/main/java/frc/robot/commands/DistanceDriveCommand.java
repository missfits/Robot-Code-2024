// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DrivetrainConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import java.lang.Math.*;

import edu.wpi.first.wpilibj2.command.Command;


/** A dead reckoning taxi command that uses the drivetrain subsystem. */
public class DistanceDriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;
  private double m_targetDistance;
  private double m_targetVelocity;
  private SparkPIDController m_leftPIDController;
  private SparkPIDController m_rightPIDController;

  /** 
   * A dead reckoning drive command.
   * Takes in target distance in meters and drives straight that amount.
   * Uses default target velocity of ??? m/s. 
   */ 
  public DistanceDriveCommand(Drivetrain drivetrain, double targetDistance) {
    m_drivetrain = drivetrain;
    m_targetDistance = targetDistance * DrivetrainConstants.METERS_TO_ROTATIONS; // convert meters to motor rotations
    // System.out.println("Target meters: " + targetDistance + ", target rotations: " + m_targetDistance);
    m_targetVelocity = AutoConstants.DEFAULT_TARGET_VELOCITY * DrivetrainConstants.METERS_TO_ROTATIONS * 60;

    m_leftPIDController = m_drivetrain.m_leftPrimary.getPIDController();
    m_rightPIDController = m_drivetrain.m_rightPrimary.getPIDController();

    addRequirements(drivetrain);
  }

  /** 
   * A dead reckoning drive command.
   * Takes in target distance in meters and drives straight that amount.
   * Takes in target velocity in meters per second.
   */ 
  public DistanceDriveCommand(Drivetrain drivetrain, double targetDistance, double targetVelocity) {
    m_drivetrain = drivetrain;
    m_targetDistance = targetDistance * DrivetrainConstants.METERS_TO_ROTATIONS; // convert meters to motor rotations
    // System.out.println("Target meters: " + targetDistance + ", target rotations: " + m_targetDistance);
    m_targetVelocity = targetVelocity * DrivetrainConstants.METERS_TO_ROTATIONS * 60; // convert meters/sec to RPM

    m_leftPIDController = m_drivetrain.m_leftPrimary.getPIDController();
    m_rightPIDController = m_drivetrain.m_rightPrimary.getPIDController();

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    // resets positions of encoders
    m_drivetrain.setRightEncoder(0);
    m_drivetrain.setleftEncoder(0);

    // // configure PID controllers (with constants)
    // m_leftPIDController.setP(DrivetrainConstants.K_P);
    // m_leftPIDController.setI(DrivetrainConstants.K_I);
    // m_leftPIDController.setD(DrivetrainConstants.K_D);
    // m_leftPIDController.setIZone(DrivetrainConstants.K_IZ);
    // m_leftPIDController.setFF(DrivetrainConstants.K_FF);
    // m_leftPIDController.setOutputRange(DrivetrainConstants.K_MIN_OUTPUT, DrivetrainConstants.K_MAX_OUTPUT);

    // m_rightPIDController.setP(DrivetrainConstants.K_P);
    // m_rightPIDController.setI(DrivetrainConstants.K_I);
    // m_rightPIDController.setD(DrivetrainConstants.K_D);
    // m_rightPIDController.setIZone(DrivetrainConstants.K_IZ);
    // m_rightPIDController.setFF(DrivetrainConstants.K_FF);
    // m_rightPIDController.setOutputRange(DrivetrainConstants.K_MIN_OUTPUT, DrivetrainConstants.K_MAX_OUTPUT);

    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    
    m_leftPIDController.setP(p);
    m_leftPIDController.setI(i);
    m_leftPIDController.setD(d);
    m_leftPIDController.setIZone(iz);
    m_leftPIDController.setFF(ff);
    m_leftPIDController.setOutputRange(DrivetrainConstants.K_MIN_OUTPUT, DrivetrainConstants.K_MAX_OUTPUT);

    m_rightPIDController.setP(p);
    m_rightPIDController.setI(i);
    m_rightPIDController.setD(d);
    m_rightPIDController.setIZone(iz);
    m_rightPIDController.setFF(ff);
   m_rightPIDController.setOutputRange(DrivetrainConstants.K_MIN_OUTPUT, DrivetrainConstants.K_MAX_OUTPUT);

  }

  @Override
  public void execute() {
    // OLD CODE
    // double thrust = AutoConstants.TAXI_AUTO_SPEED*Math.signum(m_targetDistance); // drives in the direction of targetDistance
    // m_drivetrain.tankDrive(-thrust, -thrust);

    // we are unsure if this actually sends power to the motors...?
    m_leftPIDController.setReference(m_targetVelocity, CANSparkMax.ControlType.kVelocity);
    m_rightPIDController.setReference(m_targetVelocity, CANSparkMax.ControlType.kVelocity);

    System.out.println("Encoder velocities in RPM: " + m_drivetrain.getLeftEncoderVelocity() + ", " + m_drivetrain.getRightEncoderVelocity());

  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stopMotors();
    m_drivetrain.setRightEncoder(0);
    m_drivetrain.setleftEncoder(0);
  }

  @Override
  public boolean isFinished() {
    // calculates if either encoder has moved enough to reach the target distance
    return (Math.abs(m_drivetrain.getRightEncoderPosition()) > Math.abs(m_targetDistance)
    || Math.abs(m_drivetrain.getLeftEncoderPosition()) > Math.abs(m_targetDistance)); 
  }
}
