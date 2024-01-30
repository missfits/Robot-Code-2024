package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    // Instance variables
    private Command m_autonomousCommand;
    private RobotContainer m_robotContainer;
    
    /**
     * Constructs Robot object.
     */
    public Robot() {}

    /**
     * This function is run when the robot is first started up and should be used for any robot-wide
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        m_robotContainer = new RobotContainer();
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * This runs after the mode specific periodic functions, but before LiveWindow and SmartDashboard
     * integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands, and
        // running subsystem periodic() methods. This must be called from the robot's periodic block in
        // order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters disabled mode.
     */
    @Override
    public void disabledInit() {}

    /**
     * This function is called periodically while the robot is in disabled mode.
     */
    @Override
    public void disabledPeriodic() {}

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    /**
     * This function is called periodically while the robot is in autonomous mode.
     */
    @Override
    public void autonomousPeriodic() {}

    /**
     * This function is called once each time the robot enters teleop mode.
     */
    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when teleop starts running. If you want the
        // autonomous to continue until interrupted by another command, remove this line or comment it
        // out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically while the robot is in teleop mode.
     */
    @Override
    public void teleopPeriodic() {}

    /**
     * This function is called once each time the robot enters test mode.
     */
    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /**
     * This function is called periodically while the robot is in test mode.
     */
    @Override
    public void testPeriodic() {}

    /**
     * This function is called once when the robot is first started up.
     */
    @Override
    public void simulationInit() {}

    /**
     * This function is called periodically while the robot is in simulation.
     */
    @Override
    public void simulationPeriodic() {}
}
