package mods.masquitt0.deadspacemod.common;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mods.masquitt0.deadspacemod.client.icon.TabIconDeadSpace;

public class ConfigCore {

//Items
  //Weaponry
	public static int plasmaCutter211V_itemID;
	public static int seekerRifle_itemID;
	//Projectile
	public static int plasmaEnergy_projectileID;
	//Ammo
	public static int ammoUniversal_itemID;
	public static int ammoPlasmaEnergy_itemID;
	public static int ammoJavelinSpears_itemID;
	public static int ammoSeekerShells_itemID;
	//Medical Packs
	public static int medicPackSmall_itemID;
	public static int medicPackMedium_itemID;
	public static int medicPackLarge_itemID;
	//Resources
	public static int resourceScrapMetal_itemID;
	public static int resourceTungsten_itemID;
	public static int resourceTransducer_itemID;
	public static int resourceSomaticGel_itemID;
	//Misc
	public static int itemCredits_itemID;
	//Markers
	public static int itemMarker3a_itemID;
	//Icons
	public static int achievementCutOfTheirLimbs_iconID;
	public static int achievementStrapped_iconID;
	public static int achievementCircuitsEdge_iconID;
	public static int achievementHealingGel_iconID;
	public static int achievementBleedStopper_iconID;
	public static int achievementEMT_iconID;
	public static int achievementSlowMo_iconID;
	public static int tabDeadSpace_iconID;
//Blocks
	//Necro Blocks
	public static int blockNecroCorruption_blockID;
	public static int blockMarker3A_blockID;
//Boolean	
	public static boolean isAwesome;
	public static boolean note1;
	
public static void loadConfig(FMLPreInitializationEvent c){
		Configuration config = new Configuration(c.getSuggestedConfigurationFile()); //Gets the file

		config.load();	//Loads it
/**
		Property ammoUniv; //This is a property, see below
		ammoUniv = config.getItem("Universal Ammo", 1001); //This gets the property
		ammoUniv.comment = "The base ruby item"; //This adds a comment
		ammoUniversal_itemID = ammoUniv.getInt(); //This gets the value	
*/		
//Items
		//Weaponry
		plasmaCutter211V_itemID = config.getItem("Plasma Cutter", 10000).getInt();
		seekerRifle_itemID = config.getItem("SeekerRifle", 10001).getInt();
		//Projectile
		plasmaEnergy_projectileID = config.getItem("Plasma Energy Projectile", 10002).getInt();
		//Item
		ammoUniversal_itemID = config.getItem("Universal Ammo", 8003).getInt();
		ammoPlasmaEnergy_itemID = config.getItem("Plasma Energy", 8004).getInt();
		ammoJavelinSpears_itemID = config.getItem("Javelin Spears", 8005).getInt();
		ammoSeekerShells_itemID = config.getItem("Seeker Shells", 8006).getInt();
		//Medical Packs
		medicPackSmall_itemID = config.getItem("Small Medic-Pack", 8007).getInt();
		medicPackMedium_itemID = config.getItem("Medium Medic-Pack", 8008).getInt();
		medicPackLarge_itemID = config.getItem("Large Medic-Pack", 8009).getInt();
		//Resources
		resourceScrapMetal_itemID = config.getItem("Scrap Metal", 8010).getInt();
		resourceTungsten_itemID = config.getItem("Tungsten", 8011).getInt();
		resourceTransducer_itemID = config.getItem("Transducer", 8012).getInt();
		resourceSomaticGel_itemID = config.getItem("Somatic Gel", 8013).getInt();
		//Misc
		itemCredits_itemID = config.getItem("Credits", 7514).getInt();
		//Markers
		itemMarker3a_itemID = config.getItem("Marker", 7599).getInt();
		//Icons
		achievementCutOfTheirLimbs_iconID = config.getItem("icon.ach_500", 6000).getInt();
		achievementStrapped_iconID = config.getItem("icon.ach_501", 6001).getInt();
		achievementCircuitsEdge_iconID = config.getItem("icon.ach_502", 6002).getInt();
		achievementHealingGel_iconID = config.getItem("icon.ach_503", 6003).getInt();
		achievementBleedStopper_iconID = config.getItem("icon.ach_504", 6004).getInt();
		achievementEMT_iconID = config.getItem("icon.ach_505", 6005).getInt();
		achievementSlowMo_iconID = config.getItem("icon.ach_506", 6006).getInt();
		tabDeadSpace_iconID = config.getItem("Icon of 507", 6007).getInt();
//Blocks
		blockNecroCorruption_blockID = config.getBlock("The Corruption", 4000).getInt();
		blockMarker3A_blockID = config.getBlock("Marker3a", 901).getInt();
		
		config.save(); //Saves the file
		
		//General
		isAwesome = config.get(config.CATEGORY_GENERAL, "Is the mod awesome", true).getBoolean(true);
		note1 = config.get(config.CATEGORY_GENERAL, "icon.ach_XXX is the icon for the achievements! They act as Items.", false).getBoolean(false);

		config.save(); //Saves the file
}
}
