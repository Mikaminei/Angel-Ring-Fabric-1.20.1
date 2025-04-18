package de.mikaminei.simpleangelring.util;

import de.mikaminei.simpleangelring.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class AngelRingHelper {
    public static boolean hasAngelRing(ServerPlayerEntity player) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == ModItems.ANGEL_RING) return true;
        }

        for (ItemStack stack : player.getInventory().armor) {
            if (stack.getItem() == ModItems.ANGEL_RING) return true;
        }

        for (ItemStack stack : player.getInventory().offHand) {
            if (stack.getItem() == ModItems.ANGEL_RING) return true;
        }

        return false;
    }
}
