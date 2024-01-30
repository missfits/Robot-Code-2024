package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.AutoConstants;

/**
 * Class to hold all auto commands.
 */
public final class Autos {
    /**
     * Example static factory method to create autonomous command.
     * 
     * @return  sequence of example commands to run in series, one after another
     */
    public static Command exampleAuto(ExampleSubsystem subsystem) {
        return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
    }

    /**
     * Command to drive straight.
     * 
     * @return  DistanceDriveCommand to drive straight
     */
    public static Command taxiAuto(Drivetrain drivetrain) {
        return new DistanceDriveCommand(drivetrain, AutoConstants.TAXI_AUTO_TARGET_DISTANCE);
    }

    /**
     * Throws an error if Autos object is constructed.
     */
    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
