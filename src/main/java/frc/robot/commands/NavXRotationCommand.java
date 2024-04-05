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
    private double m_direction;
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
    }

    @Override
    public void initialize() {
        m_direction = Math.signum(getAngleDiff());
    }

    @Override
    public void execute() {
        double thrust = AutoConstants.ROTATION_SPEED * m_direction; // takes the sign of targetDegrees
        
        if (Math.abs(getAngleDiff()) < AutoConstants.SLOW_ANGLE_THRESHOLD) {
            thrust = AutoConstants.SLOW_ROTATION_SPEED * m_direction;
        }
        
        m_drivetrain.tankDrive(thrust, -thrust); // drives in two opposite directions so the robot spins
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stopMotors();
    }

    @Override
    public boolean isFinished() {
        // returns if the current degrees is within a certain range of target degrees
        return (Math.abs(getAngleDiff()) <  AutoConstants.END_ANGLE_THRESHOLD);
    }

    public double getAngleDiff() {
        double difference = m_targetDegrees - m_drivetrain.getRotation();
        boolean cross;
        
        if (Math.abs(difference) < 180) {
            cross = false;
        } else {
            cross = true;
        }

        if (!cross) {
            return difference;
        } else {
            return (360 - Math.abs(difference)) * (-Math.signum(difference));
        }
    }
}
