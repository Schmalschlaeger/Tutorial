package mainp.thenetwork.effects;

import org.bukkit.entity.Player;


public class ParticleEffectsManager {
	
	public void setParticleEffect(ParticleEffects effect, Player target, float speed, float lenght, int number) {
		effect.display(target.getLocation().add(0.5D, 0.5D, 0.5D), 0.0f, lenght, 0.0f, speed, number);
	}
	
	

}
