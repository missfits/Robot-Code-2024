// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.HoodConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;

/** 
 * Command for shooting in the amp 
 * Runs both the shooter and hood rollers
 */
public class ShooterAmpCommand extends Command {
    private Shooter m_shooter;
    private Hood m_hood;
    
    public ShooterAmpCommand(Shooter shooter, Hood hood){
        m_shooter = shooter;
        m_hood = hood;
        addRequirements(shooter, hood);
    }

    @Override
    public void initialize() {
        // System.out.println("SHOOTER SUCK COMMAND STARTED");
    }  

    @Override
    public void execute() {
        m_shooter.runShooterMotor(ShooterConstants.SHOOTER_MOTOR_SPEED_AMP);
        m_hood.runHoodMotor(HoodConstants.HOOD_MOTOR_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.shooterOff();
        m_hood.hoodOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
