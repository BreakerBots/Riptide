/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import frc.team5104.module.Module;
import frc.team5104.module.tshirt.TShirtSystems.valve.ValveState;
import frc.team5104.util.console;
import frc.team5104.util.console.c;

public class TShirtManager extends Module.Manager {
	//Manager Meta
	public boolean isModuleAttached() {
		//Detect Module Attached...
		return true;
	}
	private TShirtController controller = new TShirtController();
	private TShirtSystems systems = new TShirtSystems();
	public Module.Systems getSystems() { return systems; }
	public Module.Controller getController() { return controller; }
	
	//Manager Loop Functions
	public TShirtManager() { }
	public void enabled() {
		TShirtSystems.valve.setState(ValveState.EXHAUST);
	}
	public void update() {
		TShirtSystems.valve.update();
	}
	public void disabled() {
		TShirtSystems.valve.setState(ValveState.EXHAUST);
	}
}
