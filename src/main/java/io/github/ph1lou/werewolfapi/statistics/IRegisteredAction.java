package io.github.ph1lou.werewolfapi.statistics;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface IRegisteredAction {

    String getEvent();

    @Nullable UUID getUuid();

    @Nullable List<UUID> getUuidS();

    int getTimer();

    String getExtraInfo();

    int getExtraInt();
}
