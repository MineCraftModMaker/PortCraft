package com.mcmm.movecraft.bike;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Marco on 20.03.2017.
 */
public class StartupCommon {

    public static Bike bike;
    public static ItemBlock itemBike;

    /**
     * Setzt die Namen
     *    unlocalized: Mehrere Dinge können den gleichen haben
     *    registry:    Muss eindeutig sein
     *
     * Registriert sowohl den Block als auch das Item
     */
    public static void preInitCommon()
    {
        bike = (Bike) (new Bike().setUnlocalizedName("bike"));
        bike.setRegistryName("bike");
        GameRegistry.register(bike);

        itemBike = new ItemBlock(bike);
        itemBike.setRegistryName(bike.getRegistryName());
        GameRegistry.register(itemBike);
    }

    public static void initCommon()
    {
    }

    public static void postInitCommon()
    {
    }
}
