// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/** Command for shooting in the speaker. */
public class PidShooterSpeakerCommand extends Command {
    private Shooter m_shooter;
    private PIDController m_controller;
    
    public PidShooterSpeakerCommand(Shooter shooter){
        m_shooter = shooter;
        m_controller = new PIDController(ShooterConstants.KP, ShooterConstants.KI, ShooterConstants.KD);
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        // System.out.println("SHOOTER SUCK COMMAND STARTED");
    }  

    @Override
    public void execute() {
        m_shooter.runShooterMotor(m_controller.calculate(m_shooter.getEncoderVelocity(), ShooterConstants.SHOOTER_TARGET_SPEED_SPEAKER));
        System.out.println(m_shooter.getEncoderVelocity());
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.shooterOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
