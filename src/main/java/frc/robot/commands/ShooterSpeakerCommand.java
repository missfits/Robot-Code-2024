// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;

/** Command for shooting in the speaker. */
public class ShooterSpeakerCommand extends Command {
    private Shooter m_shooter;
    private Timer m_timer;
    
    public ShooterSpeakerCommand(Shooter shooter){
        m_shooter = shooter;
        m_timer = new Timer();
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        // System.out.println("SHOOTER SUCK COMMAND STARTED");
        m_timer.reset();
        m_timer.start();
    }  

    @Override
    public void execute() {
        if (m_timer.get() < 1) {
            m_shooter.runShooterMotor(-1);
        } else {
            m_shooter.runShooterMotor(ShooterConstants.SHOOTER_MOTOR_SPEED_SPEAKER);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.shooterOff();
        m_timer.stop();
        m_timer.reset();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
