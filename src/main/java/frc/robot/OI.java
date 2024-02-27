package frc.robot;

import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class OI {
    
    // Replace with CommandPS4Controller or CommandJoystick if needed
    public static CommandXboxController m_driverXbox = new CommandXboxController(
        OperatorConstants.DRIVER_XBOX_PORT);
    public static CommandXboxController m_coPilotXbox = new CommandXboxController(
        OperatorConstants.COPILOT_XBOX_PORT);

    // driver xbox inputs

    // needs to be negative to make motors run straight as of 2/25/24
    public double getDriverXBoxLeftJoyY() {
        return m_driverXbox.getLeftY();
    }

    public double getDriverXBoxLeftJoyX() {
        return m_driverXbox.getLeftX();
    }

    public double getDriverXBoxRightJoyY() {
        return m_driverXbox.getRightY();
    }

    // needs to be negative to make the turning correct as of 2/25/24
    public double getDriverXBoxRightJoyX() {
        return -m_driverXbox.getRightX();
    }

    // copilot xbox inputs
    public double getCoXBoxLeftJoyY() {
        return m_coPilotXbox.getLeftY();
    }

    public double getCoXBoxLeftJoyX() {
        return m_coPilotXbox.getLeftX();
    }

    public double getCoXBoxRightJoyY() {
        return m_coPilotXbox.getRightY();
    }

    public double getCoXBoxRightJoyX() {
        return m_coPilotXbox.getRightX();
    }

}