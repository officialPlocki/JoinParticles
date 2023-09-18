package xyz.plocki.joinparticles.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.plocki.threaded.AsyncThreadScheduler;

import java.util.Random;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation().subtract(0, 1, 0);
        new AsyncThreadScheduler(() -> {
            for(int amount = 0; amount < 6; amount++) {
                loc.add(0, 0.3, 0);
                for (int degree = 0; degree < 360; degree++) {
                    double radians = Math.toRadians(degree);
                    double x = Math.cos(radians);
                    double z = Math.sin(radians);
                    loc.add(x, 0, z);
                    loc.getWorld().spawnParticle(Particle.FLAME, loc, 2);
                    loc.subtract(x, 0, z);
                }
            }
        }).runAsyncTaskLater(3);
    }

}
