package de.slikey.effectlib.effect;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LineEffect extends LocationEffect {

    /**
     * Cache of the link for the two Locations
     */
    protected final Vector link;

    /**
     * Caches the length of the Line
     */
    protected final float length;

    /**
     * ParticleType of spawned particle
     */
    public ParticleEffect particle = ParticleEffect.FLAME;

    /**
     * Particles per arc
     */
    public int particles = 100;

    public LineEffect(EffectManager effectManager, Location start, Location stop) {
        super(effectManager, start);
        link = stop.toVector().subtract(start.toVector());
        length = (float) link.length();
        link.normalize();

        type = EffectType.INSTANT;
        period = 5;
        iterations = 200;
    }

    @Override
    public void onRun() {
        float ratio = length / particles;
        Vector v = link.clone().multiply(ratio);
        Location loc = location.clone().subtract(v);
        for (int i = 0; i < particles; i++) {
            loc.add(v);
            particle.display(loc, visibleRange);
        }
    }

}