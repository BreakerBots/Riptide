/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import frc.team5104.main.Controls;
import frc.team5104.module.Module;
import frc.team5104.module.tshirt.TShirtSystems.valve.ValveState;
import frc.team5104.module.tshirt.Turret.TurretState;
import frc.team5104.util.console;
import frc.team5104.util.console.c;

class TShirtController extends Module.Controller {
	
	double targetPressure = 10; //PSI
	
	protected void update() {
		//Firing
		if (Controls.TShirt.fire.getPressed()) {
			TShirtSystems.valve.toggleState();
			console.log(c.TSHIRT, TShirtSystems.valve.state == ValveState.EXHAUST ? "Firing!" : "Filling!");
		}
		
		//Pressure
		if (Controls.TShirt.pressure_increase.getPressed()) {
			targetPressure += 2;
			console.log(c.TSHIRT, "target pressure: " + targetPressure);
		}
		if (Controls.TShirt.pressure_decrease.getPressed()) {
			targetPressure -= 2;
			console.log(c.TSHIRT, "target pressure: " + targetPressure);
		}
		
		//Turret
		if (Turret.state == TurretState.MANUAL) {
			TShirtSystems.turret.setSpeed(Controls.TShirt.rotateTurret.getAxis() * TShirtConstants.TURRET_MAX_VOLTAGE);
		}
		
		//Pitch
		TShirtSystems.pitch.setSpeed(Controls.TShirt.rotatePitch.getAxis() * 
				(Controls.TShirt.rotatePitch.getAxis() < 0 ? TShirtConstants.PITCH_UP_MAX_VOLTAGE :
					TShirtConstants.PITCH_DOWN_MAX_VOLTAGE));
	}
		
	protected void idle() {
		TShirtSystems.turret.stop();
		TShirtSystems.pitch.stop();
		TShirtSystems.valve.setState(ValveState.EXHAUST);
	}
}
