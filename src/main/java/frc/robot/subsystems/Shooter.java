// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_shooterMotor = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR_PORT, MotorType.kBrushless);
  private final SparkRelativeEncoder m_shooterEncoder = (SparkRelativeEncoder) m_shooterMotor
      .getEncoder(SparkRelativeEncoder.Type.kHallSensor, ShooterConstants.COUNTS_PER_REV);

  public Shooter() {}

  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(()
                       -> {
                           /* one-time action goes here */
                       });
  }

  public boolean exampleCondition() { return false; }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {}
}
