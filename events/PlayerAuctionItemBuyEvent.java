package fr.elias.ultimateShopGui.api.events;

import fr.elias.ultimateShopGui.AuctionItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public class PlayerAuctionItemBuyEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;

    private final AuctionItem auctionItem;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
