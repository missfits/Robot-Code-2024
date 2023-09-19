// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.DrivetrainConstants;

import frc.robot.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {

    // instance variables

    private final OI m_humanControl;

    // motors - TO DO: explain what these things do!
    public final CANSparkMax m_leftPrimary = new CANSparkMax(DrivetrainConstants.LEFT_MOTOR_1_PORT,
        MotorType.kBrushless);
    private final CANSparkMax m_leftSecondary = new CANSparkMax(DrivetrainConstants.LEFT_MOTOR_2_PORT,
        MotorType.kBrushless);
    private final CANSparkMax m_rightPrimary = new CANSparkMax(DrivetrainConstants.RIGHT_MOTOR_1_PORT,
        MotorType.kBrushless);
    private final CANSparkMax m_rightSecondary = new CANSparkMax(DrivetrainConstants.RIGHT_MOTOR_2_PORT,
        MotorType.kBrushless);

    // encoders
    public final SparkMaxRelativeEncoder m_leftPrimaryEncoder = (SparkMaxRelativeEncoder) m_leftPrimary
        .getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);
    public final SparkMaxRelativeEncoder m_leftSecondaryEncoder = (SparkMaxRelativeEncoder) m_leftSecondary
        .getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);
    public final SparkMaxRelativeEncoder m_rightPrimaryEncoder = (SparkMaxRelativeEncoder) m_rightPrimary
        .getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);
    public final SparkMaxRelativeEncoder m_rightSecondaryEncoder = (SparkMaxRelativeEncoder) m_rightSecondary
        .getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);

    // groups - to control multiple motors at once (for multiple motors per side of the drivetrain)
    private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftPrimary, m_leftSecondary);
    private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightPrimary, m_rightSecondary);

    public static DifferentialDrive m_robotDrive;
    
    public Drivetrain(OI humanControl) {
      m_robotDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
      m_humanControl = humanControl;

      configDrivetrainMotors();
    }

    @Override
    public void periodic() {}

    public void configDrivetrainMotors() {

      // makes the secondary motors follow the primary ones
      m_leftSecondary.follow(m_leftPrimary);
      m_rightSecondary.follow(m_rightPrimary);

      // inverts the left side to account for the fact that that side initially moves backwards for positive velocity and forwards for negative
      m_leftGroup.setInverted(true);
      m_rightGroup.setInverted(false);
    }

    // TO DO: write getters

    @Override
    public void simulationPeriodic() {}

    // ensures given value is within a specified range
    public double clamp(double val, double min, double max) {
      if (val > max) {
          System.out.println("out of range");
          return max;
      } else if (val < min) {
          System.out.println("out of range");
          return min;
      }
      return val;
    }

    // arcade drive
    public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
        m_robotDrive.arcadeDrive(forwardSpeed, rotationSpeed);
    }
}
