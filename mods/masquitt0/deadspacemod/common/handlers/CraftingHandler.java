package mods.masquitt0.deadspacemod.common.handlers;

import mods.masquitt0.deadspacemod.common.deadspacemod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler
{

@Override
public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
{
//Healing Gel
if (item.itemID == deadspacemod.MedicPackSmall.itemID)
{
player.addStat(deadspacemod.AchievementHealingGel, 1);
}
//Bleed Stopper
if (item.itemID == deadspacemod.MedicPackMedium.itemID)
{
player.addStat(deadspacemod.AchievementBleedStopper, 1);
}
//EMT
if (item.itemID == deadspacemod.MedicPackLarge.itemID)
{
player.addStat(deadspacemod.AchievementEMT, 1);
}
}
@Override
public void onSmelting(EntityPlayer player, ItemStack item)
{

}
}
