// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.*;
import frc.robot.Constants.AutoConstants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  public static Command taxiAuto(Drivetrain drivetrain) {
    return new DistanceDriveCommand(drivetrain, AutoConstants.TAXI_AUTO_TARGET_DISTANCE);
  }

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @param intake intake subsystem of robot
   * @param indexer indexer subsystem of robot
   * @param shooter shooter subsystem of robot
   * @return a sequential command group that shoots preloaded note into speaker (from the front edge),
   *          picks up the center note, drives back to the shooter, and shoots the second note
   */
  public static SequentialCommandGroup frontSpeaker2pc(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
    return new SequentialCommandGroup(
      new ParallelCommandGroup( // shoots preloaded into speaker for 2 seconds
        new IndexerUpCommand(indexer),
        new ShooterOutCommand(shooter) // TO DO: replace shooter out with shooter speaker command once testing is done
      ).withTimeout(2),
      new ParallelCommandGroup( // drive towards note while intaking
        new DistanceDriveCommand(drivetrain, 1.30),
        new IntakeIndexCommand(indexer, intake).withTimeout(4)),
      new DistanceDriveCommand(drivetrain, -1.30), // drive back towards speaker
      new ParallelCommandGroup( // shoots second note into speaker for 2 seconds
        new IndexerUpCommand(indexer),
        new ShooterOutCommand(shooter) // TO DO: replace shooter out with shooter speaker command once testing is done
      ).withTimeout(2)
    );
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
