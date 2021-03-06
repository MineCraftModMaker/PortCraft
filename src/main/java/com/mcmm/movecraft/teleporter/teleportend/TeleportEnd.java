package com.mcmm.movecraft.teleporter.teleportend;

import com.mcmm.movecraft.MoveCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by Ewald on 27.03.2017.
 *
 * Stellt den Endpunkt des Teleport Systems dar
 */
public class TeleportEnd extends Block{

    public TeleportEnd() {
        super(Material.ROCK);
        this.setCreativeTab(MoveCraft.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube(IBlockState iBlockState) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState iBlockState) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
        return EnumBlockRenderType.MODEL;
    }


    private final AxisAlignedBB TELEPORT_AABB = getAABBFromPixels(0, 0, 0, 16, 1, 16);


    @Nullable
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return TELEPORT_AABB;
    }



    private AxisAlignedBB getAABBFromPixels(int minX, int minY, int minZ, int maxX, int maxY, int maxZ)
    {
        final float PIXEL_WIDTH = 1.0F / 16.0F;
        return new AxisAlignedBB(minX * PIXEL_WIDTH, minY * PIXEL_WIDTH, minZ * PIXEL_WIDTH,
                maxX * PIXEL_WIDTH, maxY * PIXEL_WIDTH, maxZ * PIXEL_WIDTH);
    }


}


