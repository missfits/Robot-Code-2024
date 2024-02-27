package frc.robot.commands;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

/*
 * Runs during auto to shoot note into speaker
 */
public class AutoSpeakerShootCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Indexer m_indexer;
  private final Shooter m_shooter;
  private final Timer m_timer;

  public AutoSpeakerShootCommand(Indexer indexer, Shooter shooter) {
    m_indexer = indexer;
    m_shooter = shooter;
    m_timer = new Timer();
    addRequirements(indexer, shooter);
  }

  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void execute() {
    m_shooter.runShooterMotor(ShooterConstants.SHOOTER_MOTOR_SPEED_SPEAKER);
     // once shooter motor reaches desired speed, run indexer motor
    if(Math.abs(m_shooter.getEncoderVelocity())> ShooterConstants.SHOOTER_TARGET_SPEED_SPEAKER)
        m_indexer.runIndexerMotor(IndexerConstants.INDEXER_MOTOR_SPEED_UP);
    } 

  @Override
  public void end(boolean interrupted) {
    m_shooter.shooterOff();
    m_indexer.indexerOff();
  }

  @Override
  public boolean isFinished() {
    return m_timer.get() > AutoConstants.SPEAKER_SHOOT_TIMEOUT;
  }
}