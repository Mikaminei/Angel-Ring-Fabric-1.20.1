package de.mikaminei.simpleangelring.event;

import de.mikaminei.simpleangelring.util.AngelRingHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerTickHandler {
    private static final Map<UUID, Boolean> playerRingStatus = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tick(server);
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            playerRingStatus.remove(handler.player.getUuid());
        });
    }

    private static void tick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            UUID playerUuid = player.getUuid();
            boolean currentHasRing = AngelRingHelper.hasAngelRing(player);
            boolean previousHasRing = playerRingStatus.getOrDefault(playerUuid, false);

            if (currentHasRing != previousHasRing) {
                playerRingStatus.put(playerUuid, currentHasRing);

                PlayerAbilities abilities = player.getAbilities();
                boolean abilitiesChanged = false;

                if (currentHasRing) {
                    if (!abilities.allowFlying) {
                        abilities.allowFlying = true;
                        abilitiesChanged = true;
                    }
                } else {
                    if (abilities.allowFlying && !player.isCreative() && !player.isCreative()) {
                        abilities.allowFlying = false;
                        abilities.flying = false;
                        abilitiesChanged = true;
                    }
                }

                if (abilitiesChanged) {
                    player.sendAbilitiesUpdate();
                }
            } else if (!currentHasRing && !player.isCreative() && !player.isSpectator() && player.getAbilities().flying) {
                player.getAbilities().flying = false;
                player.sendAbilitiesUpdate();
            }
        }
    }
}
