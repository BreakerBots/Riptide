/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.drive;

import frc.team5104.module.Module;

public class DriveManager extends Module.Manager {
	//Drive Meta
	public boolean isModuleAttached() {
		//Detect Module Attached...
		//CAN can = new CAN(11);
		return true;
	}
	private DriveController controller = new DriveController();
	private DriveSystems systems = new DriveSystems();
	public Module.Systems getSystems() { return systems; }
	public Module.Controller getController() { return controller; }
	
	//Drive Loop Functions
	public DriveManager() { }
	public void enabled() { }
	public void update() { }
	public void disabled() { }
}
