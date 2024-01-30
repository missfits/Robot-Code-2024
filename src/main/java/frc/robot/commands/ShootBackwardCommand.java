// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/** An example command that uses an example subsystem. */
public class ShootBackwardCommand extends Command {
    private Shooter m_shooter;
    
    public ShootBackwardCommand(Shooter shooter){
        m_shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        // System.out.println("SHOOTER SUCK COMMAND STARTED");
    }  

    @Override
    public void execute() {
        m_shooter.runShooterMotor(ShooterConstants.SHOOTER_MOTOR_SPEED_BACKWARD);
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
