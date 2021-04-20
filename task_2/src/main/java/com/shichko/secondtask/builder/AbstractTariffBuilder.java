package com.shichko.secondtask.builder;

import com.shichko.secondtask.entity.Tariff;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTariffBuilder {
    protected Set<Tariff> tariffs;

    public AbstractTariffBuilder() {
        tariffs = new HashSet<>();
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public abstract void buildSetTariffs(String filename);
}
