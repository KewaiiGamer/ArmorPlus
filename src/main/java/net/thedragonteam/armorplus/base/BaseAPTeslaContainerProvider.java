/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.base;

import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.common.Optional.Method;

import javax.annotation.Nonnull;

public class BaseAPTeslaContainerProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider {

    private final BaseTeslaContainer container;
    private int power;
    private int maxCapacity;
    private int output;
    private int input;

    public BaseAPTeslaContainerProvider(BaseTeslaContainer container, int power, int maxCapacity, int input, int output) {
        this.container = container;
        this.power = power;
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
        container.setCapacity(maxCapacity);
        container.setOutputRate(output);
        container.setInputRate(input);
    }

    public BaseAPTeslaContainerProvider(BaseTeslaContainer container, int maxCapacity, int input, int output) {
        this.container = container;
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
        container.setCapacity(maxCapacity);
        container.setOutputRate(output);
        container.setInputRate(input);
    }

    @Method(modid = "tesla")
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_HOLDER;
    }

    @Method(modid = "tesla")
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        return capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_HOLDER ? (T) this.container : null;
    }

    @Method(modid = "tesla")
    @Override
    public NBTTagCompound serializeNBT() {
        return this.container.serializeNBT();
    }

    @Method(modid = "tesla")
    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.container.deserializeNBT(nbt);
    }
}