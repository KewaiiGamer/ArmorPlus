/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum DevItems implements IStringSerializable {
    THE_DRAGON_TEAM("the_dragon_team"),
    MODDED_CITY("modded_city"),
    JON_BAMS("jon_bams", true),
    TWITCH("twitch"),
    BEAM("beam");

    private final String name;

    private final boolean subTypes;

    DevItems(String nameIn) {
        this(nameIn, false);
    }

    DevItems(String nameIn, boolean hasSubTypes) {
        this.name = nameIn;
        this.subTypes = hasSubTypes;
    }

    public String toString() {
        return this.name;
    }

    public boolean hasSubTypes() {
        return subTypes;
    }

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }
}
