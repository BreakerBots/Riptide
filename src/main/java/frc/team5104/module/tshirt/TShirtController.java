/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import frc.team5104.main.Controls;
import frc.team5104.module.Module;
import frc.team5104.module.tshirt.TShirtSystems.valve.ValveState;
import frc.team5104.module.tshirt.Turret.TurretState;
import frc.team5104.util.console;
import frc.team5104.util.console.c;

class TShirtController extends Module.Controller {
	protected void update() {
		if (Controls.TShirt.fire.getPressed()) {
			TShirtSystems.valve.toggleState();
			console.log(c.TSHIRT, TShirtSystems.valve.state == ValveState.EXHAUST ? "Firing!" : "Filling!");
		}
		
		if (Turret.state == TurretState.MANUAL) {
			TShirtSystems.turret.setSpeed(Controls.TShirt.rotateTurret.getAxis() * TShirtConstants.TURRET_MAX_VOLTAGE);
		}
		
		TShirtSystems.pitch.setSpeed(Controls.TShirt.rotatePitch.getAxis() * TShirtConstants.PITCH_MAX_VOLTAGE);
	}
		
	protected void idle() {
		TShirtSystems.turret.stop();
		TShirtSystems.pitch.stop();
		TShirtSystems.valve.setState(ValveState.EXHAUST);
	}
}
