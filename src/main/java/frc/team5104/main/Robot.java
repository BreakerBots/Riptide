/*BreakerBots Robotics Team 2019*/
package frc.team5104.main;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team5104.main.RobotState.RobotMode;
import frc.team5104.util.console;

/**
 * Fallthrough from <strong>Breaker Robot Controller</strong>
 */
public class Robot extends RobotController.BreakerRobot {
	public Robot() {

	}
	
	public void mainEnabled() {
		console.logFile.start();
		console.log("Robot Enabled");
	}
	public void mainDisabled() {
		console.log("Robot Disabled");
		console.logFile.end();
	}
	
	Joystick controller = new Joystick(0);
	Solenoid sol = new Solenoid(4);
	boolean mode = false;
	public void mainLoop() {
		console.log(mode);
		if (RobotState.getMode() == RobotMode.Teleop) {
			if (controller.getRawButtonPressed(1)) {
				console.log("changing mode");
				mode = !mode;
			}
		} 
		else {
			mode = false;
		}
		sol.set(mode);
	}
}
