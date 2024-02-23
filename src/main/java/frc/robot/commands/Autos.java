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

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @return a command that drives forward a set distance
   * 
   * Start position: anywhere facing the opposing alliance, unobstructed
   */
  public static Command taxiAuto(Drivetrain drivetrain) {
    return new DistanceDriveCommand(drivetrain, AutoConstants.TAXI_AUTO_TARGET_DISTANCE);
  }

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @param indexer intake subsystem of robot
   * @param shooter shooter subsystem of robot
   * @return a sequential command group that shoots preloaded note into speaker (front edge) and taxis out
   * 
   * Start position: against the front edge of the speaker
   * 
   * UNTESTED
   */
  public static SequentialCommandGroup shootTaxiFront(Drivetrain drivetrain, Indexer indexer, Shooter shooter) {
    return new SequentialCommandGroup(
      new AutoSpeakerShootCommand(indexer, shooter),
      new DistanceDriveCommand(drivetrain, AutoConstants.TAXI_AUTO_TARGET_DISTANCE)
    );
  }

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @param indexer intake subsystem of robot
   * @param shooter shooter subsystem of robot
   * @return a sequential command group that shoots preloaded note into speaker (left edge) and taxis out
   * 
   * Start position: against the left edge of the speaker
   * 
   * UNTESTED
   */
  public static SequentialCommandGroup shootTaxiLeft(Drivetrain drivetrain, Indexer indexer, Shooter shooter) {
    return new SequentialCommandGroup(
      new AutoSpeakerShootCommand(indexer, shooter),
      new DistanceDriveCommand(drivetrain, 0.4), // drive a little 
      new RotationCommand(drivetrain, -60), // rotate
      new DistanceDriveCommand(drivetrain, 1.5) // taxi forwards
    );
  }

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @param indexer intake subsystem of robot
   * @param shooter shooter subsystem of robot
   * @return a sequential command group that shoots preloaded note into speaker (right edge) and taxis out
   * 
   * Start position: against the right edge of the speaker
   * 
   * UNTESTED
   */
  public static SequentialCommandGroup shootTaxiRight(Drivetrain drivetrain, Indexer indexer, Shooter shooter) {
    return new SequentialCommandGroup(
      new AutoSpeakerShootCommand(indexer, shooter),
      new DistanceDriveCommand(drivetrain, 0.4), // drive a little 
      new RotationCommand(drivetrain, 60), // rotate
      new DistanceDriveCommand(drivetrain, 1.5) // taxi forwards
    );
  }

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @param intake intake subsystem of robot
   * @param indexer indexer subsystem of robot
   * @param shooter shooter subsystem of robot
   * @return a sequential command group that shoots preloaded note into speaker (from the front edge),
   *          picks up the center note, drives back to the shooter, and shoots the second note
   * 
   * Start position: centered against the front edge of speaker
   * 
   * UNTESTED
   */
  public static SequentialCommandGroup frontSpeaker2pc(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
    return new SequentialCommandGroup(
      new AutoSpeakerShootCommand(indexer, shooter), // shoot preloaded
      new ParallelCommandGroup( // drive towards note while intaking, intake for 4 seconds
        new DistanceDriveCommand(drivetrain, AutoConstants.FRONT_SPEAKER_TO_CENTER_NOTE),
        new IntakeIndexCommand(indexer, intake).withTimeout(4)),
      new DistanceDriveCommand(drivetrain, -AutoConstants.FRONT_SPEAKER_TO_CENTER_NOTE), // drive back towards speaker
      new AutoSpeakerShootCommand(indexer, shooter) // shoot second note
    );
  }

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @param intake intake subsystem of robot
   * @param indexer indexer subsystem of robot
   * @param shooter shooter subsystem of robot
   * @return a sequential command group that shoots preloaded note into speaker (from the left edge when facing the speaker),
   *          picks up the note closest to amp, drives back to the shooter (left edge), and shoots the second note
   * 
   * Start position: Against left edge of speaker, as far against the field wall as possible
   * 
   * UNTESTED
   */
  public static SequentialCommandGroup leftSpeaker2pc(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
    return new SequentialCommandGroup(
      new AutoSpeakerShootCommand(indexer, shooter), // shoot preloaded
      new DistanceDriveCommand(drivetrain, 0.33), // 0.33m gets center robot in line with note
      new RotationCommand(drivetrain, -60), // turns robot to face note
      new ParallelCommandGroup( // drives towards note while intaking for 4 seconds
        new DistanceDriveCommand(drivetrain, 1.75), // 1.75 is the distance needed to get to the note
        new IntakeIndexCommand(indexer, intake).withTimeout(4)
      ),
      new DistanceDriveCommand(drivetrain, -1.75), // back up same distance as before
      new RotationCommand(drivetrain, 60), // turn the other way 60 degrees (facing speaker)
      new DistanceDriveCommand(drivetrain, -0.33), // back into speaker
      new AutoSpeakerShootCommand(indexer, shooter) // shoot again
    );
  }

  /**
   * @param drivetrain drivetrain subsystem of robot
   * @param intake intake subsystem of robot
   * @param indexer indexer subsystem of robot
   * @param shooter shooter subsystem of robot
   * @return a sequential command group that shoots preloaded note into speaker (from the right edge when facing the speaker),
   *          picks up the note closest to center, drives back to the shooter (right edge), and shoots the second note
   * 
   * Start position: Against the right edge of speaker, as far against the wall as possible
   * 
   * UNTESTED
   */
  public static SequentialCommandGroup rightSpeaker2pc(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter) {
    return new SequentialCommandGroup(
      new AutoSpeakerShootCommand(indexer, shooter), // shoot preloaded
      new DistanceDriveCommand(drivetrain, 0.33), // 0.33m gets center robot in line with note
      new RotationCommand(drivetrain, 60), // turns robot to face note
      new ParallelCommandGroup( // drives towards note while intaking for 4 seconds
        new DistanceDriveCommand(drivetrain, 1.75),
        new IntakeIndexCommand(indexer, intake).withTimeout(4)
      ),
      new DistanceDriveCommand(drivetrain, -1.75), // back up same distance as before
      new RotationCommand(drivetrain, -60), // turn the other way 60 degrees (facing speaker)
      new DistanceDriveCommand(drivetrain, -0.33), // back into speaker
      new AutoSpeakerShootCommand(indexer, shooter) // shoot again
    );
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
