package fr.elias.ultimateShopGui.api;

import fr.elias.ultimateShopGui.*;
import fr.elias.ultimateShopGui.data.category.Category;
import fr.elias.ultimateShopGui.data.item.ShopItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@RequiredArgsConstructor
public class UltimateShopAPI {
    private final UltimateShopGui plugin;

    public @Nullable Category getShopCategory(@NotNull String categoryName) {
        return plugin.getShopDataManager().getCategories().stream()
          .filter($ -> $.getName().equalsIgnoreCase(categoryName))
          .findFirst()
          .orElse(null);
    }

    public @NotNull List<Category> getShopCategories() {
        return plugin.getShopDataManager().getCategories();
    }

    public void addShopCategory(@NotNull Category category) {
        plugin.getShopDataManager().createCategory(null, category.getName(), category.getMenuTitle(), category.getSlot(), category.getPage(), category.getIcon().getType().name(), category.getIcon().getItemMeta().getCustomModelData());
    }

    public void deleteShopCategory(@NotNull String categoryName) {
        plugin.getShopDataManager().deleteCategory(categoryName);
    }

    public double getSellPrice(@NotNull ItemStack item) {
        return plugin.getShopDataManager().getSellPrice(item);
    }

    public void addItemToCategory(@NotNull Category category, @NotNull ShopItem item) {
        plugin.getShopDataManager().addItemToCategory(null, category.getName(), item.getItem(), item.getSlot(), item.getPage(), item.getBuyPrice(), item.getSellPrice(), item.getCurrency());
    }

    public void addItemToCategory(@NotNull String categoryName, @NotNull ShopItem item) {
        plugin.getShopDataManager().addItemToCategory(null, categoryName, item.getItem(), item.getSlot(), item.getPage(), item.getBuyPrice(), item.getSellPrice(), item.getCurrency());
    }

    public @Nullable ShopItem getShopItem(@NotNull Category category, int slot, int page) {
        return category.getItems().stream()
          .filter($ -> $.getSlot() == slot && $.getPage() == page)
          .findFirst()
          .orElse(null);
    }

    public @Nullable ShopItem getShopItem(@NotNull String categoryName, int slot, int page) {
        final Category category = this.getShopCategory(categoryName);
        if (category == null) return null;

        return category.getItems().stream()
          .filter($ -> $.getSlot() == slot && $.getPage() == page)
          .findFirst()
          .orElse(null);
    }

    public @NotNull List<AuctionItem> getAuctionItems() {
        return this.getPlayerAuction().getAuctionItems();
    }

    public @NotNull PlayerAuction getPlayerAuction() {
        return plugin.getPlayerAuction();
    }

    public double getBalance(@NotNull Player player, @NotNull String currency) {
        return this.getDataManager().getCurrency(currency, player.getName());
    }

    public boolean hasBalance(@NotNull Player player, @NotNull String currency, double amount) {
        return this.getEconomyManager().hasFunds(player, amount, currency);
    }

    public void depositBalance(@NotNull Player player, @NotNull String currency, double amount) {
        this.getEconomyManager().addFunds(player.getName(), amount, currency);
    }

    public void withdrawBalance(@NotNull Player player, @NotNull String currency, double amount) {
        this.getEconomyManager().deductFunds(player, amount, currency);
    }

    public boolean isCurrencySupported(@NotNull String currency) {
        return this.getDataManager().isCurrencySupported(currency);
    }

    public @NotNull List<String> getAllCurrencies() {
        return plugin.getDataManager().currenciesList();
    }

    public @NotNull EconomyManager getEconomyManager() {
        return plugin.getEconomyManager();
    }

    public @NotNull DataManager getDataManager() {
        return plugin.getDataManager();
    }
}
