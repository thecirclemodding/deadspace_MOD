package mods.masquitt0.deadspacemod.common.core;

import mods.masquitt0.deadspacemod.common.deadspacemod;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy implements IGuiHandler{ //THIS IS IMPORTANT, CANNOT BE A PROXY/GUI HANDLER WITHOUT THIS!!
public void registerRenderInformation() //Client side texture registering
{
}
@Override
public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
return null;
}
@Override
public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
return null;
}
public void registerTiles(){
}
public void registerBlocks(){
//Block Registry
  //Necromorph Blocks
		GameRegistry.registerBlock(deadspacemod.BlockNecroCorruption);
		//GameRegistry.registerBlock(deadspacemod.BlockMarker3A);
}
public void addNames(){
//Block Naming
	//Necromorph Blocks
		LanguageRegistry.addName(deadspacemod.BlockNecroCorruption, "The Corruption");
//Item Naming		
	//Weapon Naming
		LanguageRegistry.addName(deadspacemod.PlasmaCutter211V, "Plasma Cutter");
		LanguageRegistry.addName(deadspacemod.SeekerRifle, "Seeker Rifle");
	//Projectile
		LanguageRegistry.addName(deadspacemod.ProjectilePlasmaEnergy, "projectile.plasmaEnergy");
	//Ammo Naming
		LanguageRegistry.addName(deadspacemod.AmmoUniversal, "Universal Ammo");
		LanguageRegistry.addName(deadspacemod.AmmoPlasmaEnergy, "Plasma Energy");
		LanguageRegistry.addName(deadspacemod.AmmoJavelinSpears, "Javelin Spears");
		LanguageRegistry.addName(deadspacemod.AmmoSeekerShells, "Seeker Shells");
	//Medical Pack Naming
		LanguageRegistry.addName(deadspacemod.MedicPackSmall, "Small Medic-Pack");
		LanguageRegistry.addName(deadspacemod.MedicPackMedium, "Medium Medic-Pack");
		LanguageRegistry.addName(deadspacemod.MedicPackLarge, "Large Medic-Pack");
	//Resource Naming
		LanguageRegistry.addName(deadspacemod.ResourceScrapMetal, "Scrap Metal");
		LanguageRegistry.addName(deadspacemod.ResourceTungsten, "Tungsten");
		LanguageRegistry.addName(deadspacemod.ResourceTransducer, "Transducer");
		LanguageRegistry.addName(deadspacemod.ResourceSomaticGel, "Somatic Gel");
	//Miscellaneous
		LanguageRegistry.addName(deadspacemod.ItemCredits, "Credits");
	//Markers
		//LanguageRegistry.addName(deadspacemod.ItemMarker3A, "Marker");
//Server Support
	//Name Registry
		LanguageRegistry.instance().addStringLocalization("entity.deadspace_MOD.Slasher.name", "Slasher");
		LanguageRegistry.instance().addStringLocalization("entity.deadspace_MOD.EnhancedSlasher.name", "Enhanced Slasher");
		LanguageRegistry.instance().addStringLocalization("entity.deadspace_MOD.Leaper.name", "Leaper");
		LanguageRegistry.instance().addStringLocalization("entity.deadspace_MOD.EnhancedLeaper.name", "Enhanced Leaper");
		LanguageRegistry.instance().addStringLocalization("entity.deadspace_MOD.IsaacClarke.name", "Isaac Clarke");
}
public void addRecipes(){
//Crafting Recipes
	//Weapon Crafting Recipes
		//211-V Plasma Cutter
			GameRegistry.addShapedRecipe(new ItemStack(deadspacemod.PlasmaCutter211V,1,0), new Object []
			{
			"XXX"," Y ","XXX", 'X', deadspacemod.ResourceTungsten, 'Y', deadspacemod.ResourceScrapMetal
			});
	//Medical Pack Crafting Recipes
		//Small Medic-Pack
			GameRegistry.addShapedRecipe(new ItemStack(deadspacemod.MedicPackSmall,1,0), new Object []
			{
			"X","X","X", 'X', deadspacemod.ResourceSomaticGel
			});
		//Medium Medic-Pack
			GameRegistry.addShapedRecipe(new ItemStack(deadspacemod.MedicPackMedium,1,0), new Object []
			{
			"XX","XX","XX", 'X', deadspacemod.ResourceSomaticGel
			});
		//Large Medic-Pack
			GameRegistry.addShapedRecipe(new ItemStack(deadspacemod.MedicPackLarge,1,0), new Object []
			{
			"XXX","XXX","XXX", 'X', deadspacemod.ResourceSomaticGel
			});			
}
}
