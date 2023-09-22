// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
  }

  public static class DrivetrainConstants {
    public static final int LEFT_MOTOR_1_PORT = 1; // pulled from 2023 robot code
    public static final int LEFT_MOTOR_2_PORT = 2; // same as above
    public static final int RIGHT_MOTOR_1_PORT = 3; // ^^
    public static final int RIGHT_MOTOR_2_PORT = 4; // ^^

    public static final int COUNTS_PER_REV = 42;
  }

  public static class IntakeConstants {
    public static final int INTAKE_MOTOR_PORT = 6; // pulled from 2023 robot code
    public static final int PIVOT_MOTOR_PORT = 8; // same as above

    public static final double INTAKE_MOTOR_SPEED_FORWARD = 0.4; // default value 0.6, lower to prevent cubes from breaking
    public static final double INTAKE_MOTOR_SPEED_BACKWARD = -0.6;
    public static final double INTAKE_MOTOR_SPEED_DEFAULT = 0.5; // default value 0.6, lower to prevent cubes from breaking


  }
}
