package com.mcmm.movecraft;

import com.mcmm.blocks.StartupCommon;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Marco on 17.03.2017.
 */
@Mod(modid = MoveCraft.MODID, version = MoveCraft.VERSION)
public class MoveCraft {
    public static final String MODID = "movecraft";
    public static final String VERSION = "0.1";

    public static Block greenstone;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        new StartupCommon().preInitCommon();
        System.out.println("DIRTY BLOCK >> "+ Blocks.DIRT.getUnlocalizedName());
    }
}