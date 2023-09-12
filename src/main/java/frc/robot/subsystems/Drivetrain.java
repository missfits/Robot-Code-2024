// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

    // instance variables

    private final OI m_humanControl;

    // motors
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
      // TO DO: check inversions
      m_leftSecondary.follow(m_leftPrimary);
      m_rightSecondary.follow(m_rightPrimary);
      m_leftGroup.setInverted(true);
      m_rightGroup.setInverted(false);
    }

    // TO DO: write getters

    @Override
    public void simulationPeriodic() {}

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
}
