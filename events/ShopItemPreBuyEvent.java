package fr.elias.ultimateShopGui.api.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public class ShopItemPreBuyEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;

    private final ItemStack item;

    private final String currency;

    @Setter
    private double price;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
