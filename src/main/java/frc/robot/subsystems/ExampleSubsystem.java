// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(11, MotorType.kBrushless);

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {}

  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(()
                       -> { // restores the spark max to factory default 
                          System.out.println("restored to factory defaults :)");
                          m_motor.restoreFactoryDefaults();
                       });
  }

  public boolean exampleCondition() { return false; }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {}

  public void run() {
    m_motor.set(0.05);
  }
}
