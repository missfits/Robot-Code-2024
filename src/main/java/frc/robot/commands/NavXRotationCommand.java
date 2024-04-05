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
        double currentDegrees = m_drivetrain.getRotation();
        if (currentDegrees >= 0) {
            if (currentDegrees > targetDegrees && targetDegrees >= currentDegrees - 180) {
                m_direction = -1;
            } else {
                m_direction = 1;
            }
        } else {
            if (targetDegrees > currentDegrees && targetDegrees <= currentDegrees + 180) {
                m_direction = 1;
            } else {
                m_direction = -1;
            }
        }
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double thrust = AutoConstants.ROTATION_SPEED * m_direction; // takes the sign of targetDegrees
        m_drivetrain.tankDrive(thrust, -thrust); // drives in two opposite directions so the robot spins
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stopMotors();
    }

    @Override
    public boolean isFinished() {
        // returns if the current degrees is within a certain range of target degrees
        return (Math.abs(m_drivetrain.getRotation() - m_targetDegrees) < 10);
    }


}
