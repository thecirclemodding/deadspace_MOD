//*package mods.masquitt0.deadspacemod.common.handlers;

import mods.masquitt0.deadspacemod.common.deadspacemod;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import cpw.mods.fml.common.IPickupNotifier;

public class PickupHandler implements IPickupNotifier
{
@Override
public void notifyPickup(EntityItem item, EntityPlayer player)
{
//First Blood  
if(item.func_92014_d().itemID == deadspacemod.ItemCredits.itemID)
{
player.addStat(deadspacemod.AchievementCutOfTheirLimbs, 1);
}
}
}
