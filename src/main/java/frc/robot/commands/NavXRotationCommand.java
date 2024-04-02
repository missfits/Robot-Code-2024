package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.math.MathUtil;

public class NavXRotationCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain m_drivetrain;
    // private double m_rightEncoderStart;
    // private double m_leftEncoderStart;
    private double m_targetDegrees;
    private int m_direction;
    private PIDController m_controller;

    /** 
     * A dead reckoning turn command.
     * Takes in target angle in degrees (between -180 and 180) and turns to that angle relative to the field.
     */ 
    public NavXRotationCommand(Drivetrain drivetrain, double targetDegrees) {
        // System.out.println("targetDistance: " + targetDegrees);
        m_drivetrain = drivetrain;
        m_targetDegrees = MathUtil.inputModulus(targetDegrees, -180, 180); // make sure angle is correct format

        // m_controller = new PIDController(DrivetrainConstants.ROTATION_KP, DrivetrainConstants.ROTATION_KI, DrivetrainConstants.ROTATION_KD);

        addRequirements(drivetrain);

        // find direction of rotation
        // m_direction = 1 if clockwise, -1 if counterclockwise
        m_direction = (int) Math.signum(degree_difference(m_drivetrain.getRotation(), m_targetDegrees));
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double thrust = AutoConstants.ROTATION_SPEED * m_direction; // takes the sign of targetDegrees
        
        // If less than SLOW_WINDOW_DEGREES degrees to go to target (will not run for the last STOP_WINDOW_DEGREES)
        if (Math.abs(degree_difference(m_targetDegrees, m_drivetrain.getRotation())) < AutoConstants.SLOW_WINDOW_DEGREES) {
            thrust = AutoConstants.SLOW_ROTATION_SPEED * m_direction; // takes the sign of targetDegrees
        }
        m_drivetrain.tankDrive(thrust, -thrust);
        System.out.println();
        System.out.println();
        System.out.println(degree_difference(m_drivetrain.getRotation(), m_targetDegrees));
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stopMotors();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(degree_difference(m_targetDegrees, m_drivetrain.getRotation())) < AutoConstants.STOP_WINDOW_DEGREES;
    }

    // Returns difference between starting and ending angles. If output is positive, then ideal
    // turning direction is clockwise; if output is negative, then ideal turning direction is counterclockwise
    private double degree_difference(double starting_angle, double ending_angle) {
        return MathUtil.inputModulus(starting_angle - ending_angle, -180, 180);
    }
}
