// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/** An example command that uses an example subsystem. */
public class ShooterForwardCommand extends Command {
    private Shooter m_shooter;
    
    public ShooterForwardCommand(Shooter shooter){
        m_shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        // System.out.println("SHOOTER SUCK COMMAND STARTED");
    }  

    @Override
    public void execute() {
        m_shooter.runShooterMotor(ShooterConstants.INTAKE_MOTOR_SPEED_FORWARD);
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
