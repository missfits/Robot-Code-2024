// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// ***NEED TO BE UPDATED FOR 2024 SEASON***

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class OperatorConstants {
    public static final int DRIVER_XBOX_PORT = 0;
    public static final int COPILOT_XBOX_PORT = 1;

    public static final double DRIVER_JOYSTICK_DEADBAND = 0.1;
    public static final double DRIVE_SPEED_ADJUSTMENT = 0.8;
  }

  public static class DrivetrainConstants {
    public static final int LEFT_MOTOR_1_PORT = 1;
    public static final int LEFT_MOTOR_2_PORT = 2;
    public static final int RIGHT_MOTOR_1_PORT = 3;
    public static final int RIGHT_MOTOR_2_PORT = 4;

    public static final int COUNTS_PER_REV = 42;

    // 1 meter = 39.37 inches = 2.088 wheel rotations = 17.664 motor rotations (assuming gear ratio = 8.46)
    public static final double METERS_TO_ROTATIONS = 17.664;
    public static final double DEGREES_TO_ROTATIONS = 0.1; // value accurate as of 2/13/24
  }

  public static class IntakeConstants {
    public static final int INTAKE_MOTOR_PORT = 5;

    public static final double INTAKE_MOTOR_SPEED_IN = 0.4; // finalized as of 02/23/24
    public static final double INTAKE_MOTOR_SPEED_OUT = -0.4;

    public static final int COUNTS_PER_REV = 42;
  }

  public static class IndexerConstants {
    public static final int INDEXER_MOTOR_PORT = 6;

    public static final double INDEXER_MOTOR_SPEED_DOWN = 0.4; // TO DO: replace with values from beam break branch
    public static final double INDEXER_MOTOR_SPEED_UP = -0.4; // TO DO: replace with values from beam break branch

    public static final double INDEXER_MOTOR_SPEED_DOWN_BACKUP = 0.2; // for testing/backup, finalized 02/23/2024
    public static final double INDEXER_MOTOR_SPEED_UP_BACKUP = -0.2; // for testing/backup, finalized 02/23/2024

    // FOR AUTO 
    public static final double INDEXER_MOTOR_REVERSE_SPEED = 0.2;
    public static final double REVERSE_DISTANCE = 10.0; // TO DO: test

    public static final int COUNTS_PER_REV = 42;
  }

  public static class ShooterConstants {
    public static final int SHOOTER_MOTOR_PORT = 7;

    public static final double SHOOTER_MOTOR_SPEED_AMP = HoodConstants.HOOD_MOTOR_SPEED / -2.0; // should be half of HOOD_MOTOR_SPEED, finalized 02/23/2024
    public static final double SHOOTER_MOTOR_SPEED_SPEAKER = -0.5; // correct as of 2/25/24
    public static final double SHOOTER_MOTOR_SPEED_OUT = -0.5; // constant for testing

    // FOR AUTO
    public static final double SHOOTER_TARGET_SPEED_SPEAKER = 2400; // TO DO: TESTING

    public static final int COUNTS_PER_REV = 42;
  }

  public static class HoodConstants {
    public static final int HOOD_MOTOR_PORT = 9; // correct as of 2/20/24
    public static final int PIVOT_MOTOR_PORT = 8; // correct as of 2/20/24

    public static final double HOOD_MOTOR_SPEED = 0.4; // should be double of SHOOTER_MOTOR_SPEED_AMP, finalized 02/23/2024

    public static final double PIVOT_MOTOR_SPEED = 0.5; // finalized 02/23/2024
    public static final double SLOW_PIVOT_MOTOR_SPEED = 0.2; // finalized 02/23/2024

    // assuming hood back is at encoder position 0, PIVOT_DISTANCE is the required encoder position for the hood to be forward
    public static final double PIVOT_DISTANCE = -48.20; // correct as of 2/20/24


    public static final int COUNTS_PER_REV = 42;
  }

  public static class ClimberConstants {
    public static final int CLIMBER_MOTOR_PORT = 100; // TO DO: set ID val

    public static final int COUNTS_PER_REV = 42;
  }

  public static class AutoConstants {
    public static final double SPEAKER_SHOOT_TIMEOUT  = 5; // unit: seconds
    public static final double CLOSE_INTAKE_TIMEOUT = 5; // in seconds
    public static final double FAR_INTAKE_TIMEOUT = 10; // in seconds (untested for 2024) FIX!!!!!!

    public static final double TAXI_DISTANCE = 2; // distance in meters to cross taxi line (untested for 2024)
    public static final double FRONT_SPEAKER_TO_CENTER_NOTE = 1.30; // TO DO: test
    public static final double CLOSE_DIAGONAL_DISTANCE = 0.33; // distance in meters from speaker
        // side to align with close note (untested for 2024)
    public static final double CLOSE_HORIZONTAL_DISTANCE = 1.75; // distance in meters to close
        //  note after alignment (untested for 2024)
    public static final double FAR_DIAGONAL_DISTANCE = 0.86; // distance in meters from speaker
        // side to align with far note (untested for 2024)
    public static final double FAR_HORIZONTAL_DISTANCE = 7.23; // distance in meters to far note
        //  after alignment (untested for 2024)

    public static final double TAXI_AUTO_SPEED = 0.5; // (temp) speed of robot during taxi auto
    public static final double ROTATION_SPEED = 0.5;
  }
}
