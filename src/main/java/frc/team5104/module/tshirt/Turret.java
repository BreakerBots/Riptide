package frc.team5104.module.tshirt;

public class Turret {
	protected static enum TurretState { MANUAL, AUTOMATIC };
	protected static TurretState state = TurretState.MANUAL;
	
	protected static enum TurretAutomaticState { CALIBRATING, RUNNING };
	protected static TurretAutomaticState automaticState = TurretAutomaticState.CALIBRATING;
	
	protected static void update() {
		if (state == TurretState.AUTOMATIC) {
			if (automaticState == TurretAutomaticState.RUNNING) {
				//PID stuff....
			}
			else if (automaticState == TurretAutomaticState.CALIBRATING) {
				if (TShirtSystems.turret.rightLimitHit()) {
					TShirtSystems.turret.stop();
					automaticState = TurretAutomaticState.RUNNING;
				}
				else TShirtSystems.turret.setSpeed(0.1);
			}
		}
	}
	
	protected static void enabled() {
		automaticState = TurretAutomaticState.CALIBRATING;
	}
	
	protected static void disabled() {
		automaticState = TurretAutomaticState.CALIBRATING;
	}
}
