package io.github.ph1lou.werewolfapi.registers;

import io.github.ph1lou.werewolfapi.ICommand;
import io.github.ph1lou.werewolfapi.enums.StatePlayer;
import io.github.ph1lou.werewolfapi.enums.StateGame;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class CommandRegister implements IRegister {

    private final Set<String> roleKeys = new HashSet<>();

    private final String key;

    private boolean roleOnly = false;

    private boolean moderatorAccess = false;

    private final Set<StatePlayer> statePlayerAccesses = new HashSet<>();

    private final Set<StateGame> stateWW = new HashSet<>();

    private final Set<Integer> argNumbers = new HashSet<>();

    private boolean hostAccess = false;

    private boolean autoCompletion = true;

    private boolean requiredPower = false;

    private boolean requiredAbilityEnabled = false;

    private boolean requiredPlayerInGame = false;

    private final ICommand command;

    private final Plugin plugin;

    private String description="";

    public CommandRegister(Plugin plugin, String key, ICommand command){
        this.plugin=plugin;
        this.key=key;
        this.command=command;
    }

    public boolean getRoleOnly(){
        return roleOnly;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Plugin getAddon() {
        return this.plugin;
    }

    public boolean isRoleKey(String roleKey) {
        return this.roleKeys.contains(roleKey);
    }

    public String getOneRoleKey(){
        return roleKeys.stream().findFirst().orElse("");
    }

    public ICommand getCommand() {
        return command;
    }

    public boolean isRoleOnly() {
        return roleOnly;
    }

    public boolean isModeratorAccess() {
        return moderatorAccess;
    }

    public boolean isHostAccess() {
        return hostAccess;
    }

    public boolean isAutoCompletion() {
        return autoCompletion;
    }

    /**
     * Active le fait que la commande peut être utilisé par les modérateurs
     * @return l'instance du register
     */
    public CommandRegister setModeratorAccess() {
        this.moderatorAccess = true;
        return this;
    }

    /**
     * Ajoute la clef d'un rôle nécessaire pour effectuer la commande
     * @param roleKeys clef du rôle
     * @return l'instance du register
     */
    public CommandRegister addRoleKey(String roleKeys) {
        this.roleOnly=true;
        this.requiredPlayerInGame=true;
        this.roleKeys.add(roleKeys);
        return this;
    }


    /**
     * Active le fait que la commande peut être utilisé par les hosts
     * @return l'instance du register
     */
    public CommandRegister setHostAccess() {
        this.hostAccess = true;
        return this;
    }

    /**
     * Désactive l'autocomplétion de la commande
     * @return l'instance du register
     */
    public CommandRegister unsetAutoCompletion() {
        this.autoCompletion = false;
        return this;
    }

    /**
     * Ajoute un état du joueur durant lequel la commande peut être effectuée
     * @param statePlayerAccess état du joueur
     * @return l'instance du register
     */
    public CommandRegister addStateAccess(StatePlayer statePlayerAccess) {
        this.statePlayerAccesses.add(statePlayerAccess);
        this.requiredPlayerInGame = true;
        return this;
    }

    public boolean isArgNumbers(int args) {
        return argNumbers.isEmpty() || argNumbers.contains(args);
    }

    /**
     * Ajoute un nombre d'argument que le joueur doit entrer après sa commande
     * @param argNumbers nombre d'argument
     * @return l'instance du register
     */
    public CommandRegister addArgNumbers(int argNumbers) {
        this.argNumbers.add(argNumbers);
        return this;
    }



    /**
     * Spécifie que le joueur effectuant la commande doit avoir un rôle qui implémente IPower et qui a encore son pouvoir
     * @return l'instance du register
     */
    public CommandRegister setRequiredPower() {
        this.requiredPower = true;
        this.requiredPlayerInGame=true;
        return this;
    }

    /**
     * Spécifie que le joueur effectuant la commande ne doit pas avoir ses pouvoirs désactivés
     * @return l'instance du register
     */
    public CommandRegister setRequiredAbilityEnabled() {
        this.requiredAbilityEnabled = true;
        this.requiredPlayerInGame = true;
        return this;
    }

    /**
     * Ajoute une période de la partie où la commande peut être effectuée
     * @param stateLG moment de la partie
     * @return l'instance du register
     */
    public CommandRegister addStateWW(StateGame stateLG) {
        stateWW.add(stateLG);
        return this;
    }

    /**
     * Ajoute une descriptions à la commande dans la commande d'aide
     * @param description clef de la description
     * @return l'instance du register
     */
    public CommandRegister setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isStateWW(StateGame stateLG) {
        return stateWW.isEmpty() || stateWW.contains(stateLG);
    }

    public boolean isRequiredPower() {
        return requiredPower;
    }

    public boolean isRequiredAbilityEnabled() {
        return requiredAbilityEnabled;
    }

    public int getMinArgNumbers() {
        return argNumbers.stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    public boolean isRequiredPlayerInGame() {
        return requiredPlayerInGame;
    }


    public boolean isStateAccess(StatePlayer statePlayer) {
        return statePlayerAccesses.isEmpty() || statePlayerAccesses.contains(statePlayer);
    }


    public String getDescription() {
        return description;
    }


}