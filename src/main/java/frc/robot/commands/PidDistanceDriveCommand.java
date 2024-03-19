// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Drivetrain;
// import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DrivetrainConstants;

//import java.lang.Math.*;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/** A PID controlled taxi command that uses the drivetrain subsystem, UNTESTED DO NOT USE. */
public class PidDistanceDriveCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain m_drivetrain;
    private double m_targetDistance;
    private PIDController m_driveController;

    /* 
     * Drives a given distance straight.
     * Takes in targetDistance in meters (can be positive or negative)
     */
    public PidDistanceDriveCommand(Drivetrain drivetrain, double targetDistance) {
        
        m_drivetrain = drivetrain;
        // the encoders measure distance in number of rotations, so we need to convert to that from meters
        m_targetDistance = targetDistance * DrivetrainConstants.METERS_TO_ROTATIONS;

        m_driveController = new PIDController(DrivetrainConstants.drive_P, DrivetrainConstants.drive_I, DrivetrainConstants.drive_D);
        m_driveController.disableContinuousInput();
        m_driveController.setTolerance(1.0); // TO DO: tune position tolerance

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

        // reset positions of encoders
        m_drivetrain.setRightEncoder(0);
        m_drivetrain.setleftEncoder(0);

        m_driveController.reset();
        m_driveController.setSetpoint(m_targetDistance);
    }

    @Override
    public void execute() {
        // takes average of two encoders ??? to ensure straight-ness?
        double thrust = m_driveController.calculate((m_drivetrain.getLeftEncoderPosition() + m_drivetrain.getRightEncoderPosition())/2);
        m_drivetrain.tankDrive(thrust, thrust);
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stopMotors();
        m_drivetrain.setRightEncoder(0);
        m_drivetrain.setleftEncoder(0);
    }

    @Override
    public boolean isFinished() {
        return m_driveController.atSetpoint();
    }
}
