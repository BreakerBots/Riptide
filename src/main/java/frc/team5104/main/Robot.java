/*BreakerBots Robotics Team 2019*/
package frc.team5104.main;

import frc.team5104.module.ModuleManager;
import frc.team5104.module.drive.DriveManager;
import frc.team5104.module.tshirt.TShirtManager;
import frc.team5104.util.Controller;
import frc.team5104.webapp.Tuner;
import frc.team5104.webapp.Webapp;

public class Robot extends RobotController.BreakerRobot {
	public Robot() {
		ModuleManager.useModules(ModuleManager.identifyModules(
			new DriveManager(), 
			new TShirtManager()
		));
		Webapp.init();
		Tuner.init();
	}
	
	//Main
	public void mainEnabled() {
		ModuleManager.enabled();
	}
	public void mainDisabled() {
		ModuleManager.disabled();
	}
	public void mainLoop() {
		if (RobotState.isEnabled()) {
			ModuleManager.handle();
			
			if (Controls.robotDisable.getPressed()) {
				//TODO disable robot here
			}
			
			Controller.handle();
		}
	}
}
