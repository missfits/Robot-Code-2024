package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Constants.OperatorConstants;

/**
 * Operator Interface class to handle communication between controllers and driver station.
 */
public class OI {
    // Instance variables
    public static CommandXboxController m_driverXbox = new CommandXboxController(
        OperatorConstants.DRIVER_XBOX_PORT);
    public static CommandXboxController m_coPilotXbox = new CommandXboxController(
        OperatorConstants.COPILOT_XBOX_PORT);
    
    /**
     * Constructs OI object.
     */
    public OI() {}

    // Driver XBox joystick values

    /**
     * Gets XBox left joystick x-axis value.
     *
     * @return  current left side x-axis value
     */
    public double getDriverXBoxLeftJoyX() {
        return m_driverXbox.getLeftX();
    }

    /**
     * Gets XBox left joystick y-axis value.
     *
     * @return  current left side y-axis value
     */
    public double getDriverXBoxLeftJoyY() {
        // Needs to be negative to make motors run straight (as of 9/26/23)
        return -m_driverXbox.getLeftY();
    }

    /**
     * Gets XBox right joystick x-axis value.
     *
     * @return  current right side x-axis value
     */
    public double getDriverXBoxRightJoyX() {
        // Needs to be negative to make the turning correct (as of 9/26/23)
        return -m_driverXbox.getRightX();
    }

    /**
     * Gets XBox right joystick y-axis value.
     *
     * @return  current right side y-axis value
     */
    public double getDriverXBoxRightJoyY() {
        return m_driverXbox.getRightY();
    }

    // Copilot XBox joystick values (not being used right now)
    
    // /**
    //  * Gets XBox left joystick x-axis value.
    //  *
    //  * @return  current left side x-axis value
    //  */
    // public double getCoXBoxLeftJoyX() {
    //     return m_coPilotXbox.getLeftX();
    // }

    // /**
    //  * Gets XBox left joystick y-axis value.
    //  *
    //  * @return  current left side y-axis value
    //  */
    // public double getCoXBoxLeftJoyY() {
    //     // Needs to be negative to make motors run straight (as of 9/26/23)
    //     return -m_driverXbox.getLeftY();
    // }

    // /**
    //  * Gets XBox right joystick x-axis value.
    //  *
    //  * @return  current right side x-axis value
    //  */
    // public double getCoXBoxRightJoyX() {
    //     // Needs to be negative to make the turning correct (as of 9/26/23)
    //     return -m_driverXbox.getRightX();
    // }

    // /**
    //  * Gets XBox right joystick y-axis value.
    //  *
    //  * @return  current right side y-axis value
    //  */
    // public double getCoXBoxRightJoyY() {
    //     return m_coPilotXbox.getRightY();
    // }

}
