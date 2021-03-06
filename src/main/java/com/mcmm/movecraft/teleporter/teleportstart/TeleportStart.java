package com.mcmm.movecraft.teleporter.teleportstart;

import com.mcmm.movecraft.MoveCraft;
import com.mcmm.movecraft.teleporter.PortConnection;
import com.mcmm.movecraft.teleporter.TeleportData;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.LinkedList;

/**
 * Created by Ewald on 27.03.2017.
 *
 * Stellt den Startpunkt des Teleport Systems dar
 */
public class TeleportStart extends Block  {

    private double endX;
    private double endY;
    private double endZ;
    private LinkedList<PortConnection> connectionList;


    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public double getEndZ() {
        return endZ;
    }

    public void setEndZ(double endZ) {
        this.endZ = endZ;
    }



    public TeleportStart() {
        super(Material.ROCK);
        this.setCreativeTab(MoveCraft.creativeTab);
        connectionList = new LinkedList<>();
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


    @Mod.EventHandler
    public void onClicked(PlayerInteractEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();
        player.getHeldItem(EnumHand.MAIN_HAND);
    }

    /**
     *
     * @param worldIn
     * @param pos
     * @param state
     * @param entityIn
     * Die Methode wird dann aufgerufen wenn ein Entity es auf der Oberseite Berührt
     * Sie fragt ab ob es eine passende Verknüpfung für den Teleporter gibt oder nicht
     * Teleport das Entity bei erfolgreicher Abfrage
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        // Die folgenden Zeilen dienen zum herausfinden wo genau das Entity sich auf den Teleporter befindet
        double x = entityIn.getPositionVector().xCoord;
        double x1 = Math.abs(x - (int)x);
        double z = entityIn.getPositionVector().zCoord;
        double z1 = Math.abs(z - (int)z);
        // Wenn sich das Entity ziemlich in der Mitte befindet wird abgefragt
        if(x1 > 0.3 && x1 < 0.7 && z1 > 0.3 && z1 < 0.7) {
//            MapStorage storage = worldIn.getPerWorldStorage();
//            storage.setData("MOVECRAFT_DATA", TeleportData.get(worldIn));
//            System.out.println(((TeleportData)storage.getOrLoadData(TeleportData.class, "MOVECRAFT_DATA")).getList().toString());
//            connectionList = TeleportData.get(worldIn).getList();
            connectionList = TeleportData.get(worldIn).getList();
            // Wenn es eine passende Verknüpfung gibt wird das Entity teleportiert.
            for (PortConnection pc :
                    connectionList) {
                if(pos.getX() == pc.getStartX() && pos.getY() == pc.getStartY() && pos.getZ() == pc.getStartZ())
                {
                    entityIn.setPosition(pc.getEndX(), pc.getEndY(), pc.getEndZ());
                    break;
                }

            }

        }

    }

    /**
     *
     * @param blockState
     * @param worldIn
     * @param pos
     * Stellt die Hixbox des Blocks dar
     * Wichtig darum weil der Block sonst nicht wirklich mit dem Entity collidiert
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0624D, 1.0D);
    }



}
