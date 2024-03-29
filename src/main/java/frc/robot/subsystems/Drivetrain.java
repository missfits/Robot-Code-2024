// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

import frc.robot.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.MathUtil;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C.Port;

public class Drivetrain extends SubsystemBase {

    // instance variables

    private final OI m_humanControl;

    // motors
    // sets up a CAN-enabled SPARK MAX motor controller for each motor (left primary, left secondary, right primary, and right secondary)
    public final CANSparkMax m_leftPrimary = new CANSparkMax(DrivetrainConstants.LEFT_MOTOR_1_PORT,
        MotorType.kBrushless);
    private final CANSparkMax m_leftSecondary = new CANSparkMax(DrivetrainConstants.LEFT_MOTOR_2_PORT,
        MotorType.kBrushless);
    public final CANSparkMax m_rightPrimary = new CANSparkMax(DrivetrainConstants.RIGHT_MOTOR_1_PORT,
        MotorType.kBrushless);
    private final CANSparkMax m_rightSecondary = new CANSparkMax(DrivetrainConstants.RIGHT_MOTOR_2_PORT,
        MotorType.kBrushless);

    // encoders
    public final SparkRelativeEncoder m_leftPrimaryEncoder = (SparkRelativeEncoder) m_leftPrimary
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);
    public final SparkRelativeEncoder m_leftSecondaryEncoder = (SparkRelativeEncoder) m_leftSecondary
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);
    public final SparkRelativeEncoder m_rightPrimaryEncoder = (SparkRelativeEncoder) m_rightPrimary
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);
    public final SparkRelativeEncoder m_rightSecondaryEncoder = (SparkRelativeEncoder) m_rightSecondary
        .getEncoder(SparkRelativeEncoder.Type.kHallSensor, DrivetrainConstants.COUNTS_PER_REV);

    // odometry
    public static DifferentialDrive m_robotDrive;
    // private final DifferentialDriveKinematics m_kinematics = new DifferentialDriveKinematics(DrivetrainConstants.TRACK_WIDTH);
    public static AHRS m_gyro = new AHRS(Port.kMXP);

    public SparkPIDController m_pidController;

    public DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(
        m_gyro.getRotation2d(),
        getLeftEncoderPosition(), getRightEncoderPosition(),
        new Pose2d(0, 0, new Rotation2d()));

    public Drivetrain(OI humanControl) {
      m_robotDrive = new DifferentialDrive(m_rightPrimary, m_leftPrimary);
      m_humanControl = humanControl;

      configDrivetrainMotors();
    }

    @Override
    public void periodic() {
        // Update the robot pose accordingly
        m_odometry.update(m_gyro.getRotation2d(),
            DrivetrainConstants.ENCODER_TICKS_TO_METERS * getLeftEncoderPosition(),
            DrivetrainConstants.ENCODER_TICKS_TO_METERS * getRightEncoderPosition());
    }

    public void configDrivetrainMotors() {

      // makes the secondary motors follow the primary ones
      m_leftSecondary.follow(m_leftPrimary);
      m_rightSecondary.follow(m_rightPrimary);

      // inverts the right side to account for the fact that that side initially moves backwards for positive velocity and forwards for negative
      // inversion is correct as of 2/25/24
      m_leftPrimary.setInverted(true);
      m_leftSecondary.setInverted(true);

      m_rightPrimary.setInverted(false);
      m_rightSecondary.setInverted(false);

    }

    // returns position in "rotations"
    public double getLeftEncoderPosition() {
        return m_leftPrimaryEncoder.getPosition();
    }
    
    // returns position in "rotations"
    public double getRightEncoderPosition() {
        return m_rightPrimaryEncoder.getPosition();
    }

    // returns velocity in RPM 
    public double getLeftEncoderVelocity() {
        return m_leftPrimaryEncoder.getVelocity();
    }

    // returns velocity in RPM 
    public double getRightEncoderVelocity() {
        return m_rightPrimaryEncoder.getVelocity();
    }

    // sets right encoder position to given double
    public void setRightEncoder(double position) {
        m_rightPrimaryEncoder.setPosition(position);
    }

    // sets left encoder position to given double
    public void setleftEncoder(double position) {
        m_leftPrimaryEncoder.setPosition(position);
    }

    // odometry methods

    // returns current robot pose in meters
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    // returns current robot rotation in degrees relative to the field, from -180 to 180
    public double getRotation() {
        return MathUtil.inputModulus(m_gyro.getRotation2d().getDegrees(), -180, 180);
    }

    // resets robot pose to given pose
    public void resetPose(Pose2d pose) {
        m_odometry.resetPosition(m_gyro.getRotation2d(), new DifferentialDriveWheelPositions(getLeftEncoderPosition(), getRightEncoderPosition()), pose);
    }

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

    /* 
     * tank drive 
     * takes in two thrust values for left and right motor, from -1.0 to 1.0
     * only to be used for auto driving commands
     */ 
    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_robotDrive.tankDrive(leftSpeed, rightSpeed);
    }

    // stops all motors
    public void stopMotors() {
        m_robotDrive.stopMotor();
    }
}
