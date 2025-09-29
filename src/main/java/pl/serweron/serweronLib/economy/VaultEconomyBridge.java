package pl.serweron.serweronLib.economy;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import pl.serweron.serweronLib.api.economy.entity.IEconomyAccount;
import pl.serweron.serweronLib.api.economy.managers.IEconomyManager;
import pl.serweron.serweronLib.utils.Response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VaultEconomyBridge implements Economy {

    private final String pluginName;
    private final IEconomyManager economyManager;

    public VaultEconomyBridge(String pluginName, IEconomyManager economyManager) {
        this.pluginName = pluginName;
        this.economyManager = economyManager;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return pluginName;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double amount) {
        return economyManager.getCurrency().format(amount);
    }

    @Override
    public String currencyNamePlural() {
        return economyManager.getCurrency().getName();
    }

    @Override
    public String currencyNameSingular() {
        return economyManager.getCurrency().getName();
    }

    // -------- PLAYER ACCOUNTS --------
    @Override
    public boolean hasAccount(String player) {
        if (player == null) return false;
        try {
            UUID uuid = UUID.fromString(player);
            Response<Boolean> res = economyManager.accountExists(uuid);
            return res.isSuccess() && res.getData();
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        if (player == null) return false;
        Response<Boolean> res = economyManager.accountExists(player.getUniqueId());
        return res.isSuccess() && res.getData();
    }

    @Override
    public double getBalance(String player) {
        if (player == null) return 0.0;
        try {
            UUID uuid = UUID.fromString(player);
            Optional<IEconomyAccount> account = economyManager.getAccount(uuid);
            return account.map(IEconomyAccount::getBalance).orElse(0.0);
        } catch (IllegalArgumentException e) {
            return 0.0;
        }
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        if (player == null) return 0.0;
        Optional<IEconomyAccount> account = economyManager.getAccount(player.getUniqueId());
        return account.map(IEconomyAccount::getBalance).orElse(0.0);
    }

    @Override
    public boolean has(String player, double amount) {
        if (player == null) return false;
        try {
            UUID uuid = UUID.fromString(player);
            Optional<IEconomyAccount> account = economyManager.getAccount(uuid);
            return account.map(a -> a.has(amount)).orElse(false);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        if (player == null) return false;
        Optional<IEconomyAccount> account = economyManager.getAccount(player.getUniqueId());
        return account.map(a -> a.has(amount)).orElse(false);
    }

    // -------- TRANSACTIONS --------
    @Override
    public EconomyResponse withdrawPlayer(String player, double amount) {
        if (player == null) return failure("Player not found");
        try {
            UUID uuid = UUID.fromString(player);
            return withdraw(uuid, amount);
        } catch (IllegalArgumentException e) {
            return failure("Invalid player UUID");
        }
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        if (player == null) return failure("Player not found");
        return withdraw(player.getUniqueId(), amount);
    }

    @Override
    public EconomyResponse depositPlayer(String player, double amount) {
        if (player == null) return failure("Player not found");
        try {
            UUID uuid = UUID.fromString(player);
            return deposit(uuid, amount);
        } catch (IllegalArgumentException e) {
            return failure("Invalid player UUID");
        }
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        if (player == null) return failure("Player not found");
        return deposit(player.getUniqueId(), amount);
    }

    private EconomyResponse withdraw(UUID uuid, double amount) {
        Optional<IEconomyAccount> accountOpt = economyManager.getAccount(uuid);
        if (accountOpt.isEmpty()) return failure("Account not found");
        TransactionResult result = accountOpt.get().withdraw(amount);
        return mapTransaction(result);
    }

    private EconomyResponse deposit(UUID uuid, double amount) {
        Optional<IEconomyAccount> accountOpt = economyManager.getAccount(uuid);
        if (accountOpt.isEmpty()) return failure("Account not found");
        TransactionResult result = accountOpt.get().deposit(amount);
        return mapTransaction(result);
    }

    private EconomyResponse mapTransaction(TransactionResult result) {
        if (result.isSuccess()) {
            return new EconomyResponse(result.getNewBalance().doubleValue(),
                    result.getAmountChanged().doubleValue(),
                    EconomyResponse.ResponseType.SUCCESS, null);
        } else if (result.isFailure()) {
            return new EconomyResponse(result.getOldBalance().doubleValue(),
                    result.getAmountChanged() != null ? result.getAmountChanged().doubleValue() : 0.0,
                    EconomyResponse.ResponseType.FAILURE, result.getErrorMessage());
        } else {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Transaction not implemented");
        }
    }

    private EconomyResponse failure(String message) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, message);
    }

    // -------- BANK NOT SUPPORTED --------
    @Override
    public EconomyResponse createBank(String name, String player) {
        return unsupported();
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return unsupported();
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return unsupported();
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return unsupported();
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return unsupported();
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return unsupported();
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return unsupported();
    }

    @Override
    public EconomyResponse isBankOwner(String name, String player) {
        return unsupported();
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return unsupported();
    }

    @Override
    public EconomyResponse isBankMember(String name, String player) {
        return unsupported();
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return unsupported();
    }


    private EconomyResponse unsupported() {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banking not supported");
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return hasAccount(player);
    }

    @Override
    public double getBalance(String playerName, String worldName) {
        return getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player, String worldName) {
        return getBalance(player);
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return has(player, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return withdrawPlayer(player, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return depositPlayer(player, amount);
    }

    @Override
    public List<String> getBanks() {
        return Collections.emptyList();
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
        if (offlinePlayer.hasPlayedBefore()) {
            return false;
        }
        return createPlayerAccount(offlinePlayer);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return economyManager.createAccount(player.getUniqueId(), player.getName()).isSuccess();
    }


    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return false;
    }
}
