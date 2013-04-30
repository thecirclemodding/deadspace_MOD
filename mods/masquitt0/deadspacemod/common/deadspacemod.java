package mods.masquitt0.deadspacemod.common;

import mods.masquitt0.deadspacemod.client.item.*;
import mods.masquitt0.deadspacemod.client.item.render.*;
import mods.masquitt0.deadspacemod.client.entity.human.*;
import mods.masquitt0.deadspacemod.common.tileentity.*;
import mods.masquitt0.deadspacemod.client.projectile.*;
import mods.masquitt0.deadspacemod.client.icon.*;
import mods.masquitt0.deadspacemod.client.entity.necromorph.*;
import mods.masquitt0.deadspacemod.client.render.RenderMarker3A;
import mods.masquitt0.deadspacemod.client.render.RenderProjectilePlasmaEnergy;
import mods.masquitt0.deadspacemod.client.sound.*;
import mods.masquitt0.deadspacemod.client.tab.*;
import mods.masquitt0.deadspacemod.client.weapon.*;
import mods.masquitt0.deadspacemod.client.block.*;
import mods.masquitt0.deadspacemod.common.core.CommonProxy;
import mods.masquitt0.deadspacemod.common.handlers.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired = true,serverSideRequired = true,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"DeadSpaceClient"}, packetHandler = ClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"DeadSpaceServer"}, packetHandler = ServerPacketHandler.class)) //For server side packet handling
@Mod(modid = "deadspace_MOD", version = "BETA 0.0.2", name = "Dead Space" ,useMetadata = true)

public class deadspacemod {
  static void addAchievementLocalizations(){}
	static int startEntityId = 301;
//Entity Parse
		public static int getUniqueEntityId()
		{
			do
			{
				startEntityId++;
			}
			while(EntityList.getStringFromID(startEntityId) !=null);
			
			return startEntityId;
		}
		public static void registerEntityEgg(Class <? extends Entity> entity, int primaryColor, int secondaryColor)
		{
			int id = getUniqueEntityId();
			EntityList.IDtoClassMapping.put(id, entity);
			EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
}
//Item
	//Weaponry	
	public static Item PlasmaCutter211V;
	public static Item SeekerRifle;
	//Projectile
	public static Item ProjectilePlasmaEnergy;
	//Ammo
	public static Item AmmoSeekerShells;
	public static Item AmmoUniversal;
	public static Item AmmoPlasmaEnergy;
	public static Item AmmoJavelinSpears;
	//Medical Packs
	public static Item MedicPackSmall;
	public static Item MedicPackMedium;
	public static Item MedicPackLarge;
	//Resources
	public static Item ResourceScrapMetal;
	public static Item ResourceTungsten;
	public static Item ResourceTransducer;
	public static Item ResourceSomaticGel;
	//Miscellaneous
	public static Item ItemCredits;
	//Icons
	public static Item AchievementIconCutOfTheirLimbs;
	public static Item AchievementIconStrapped;
	public static Item AchievementIconCircuitsEdge;
	public static Item AchievementIconHealingGel;
	public static Item AchievementIconBleedStopper;
	public static Item AchievementIconEMT;
	public static Item AchievementIconSlowMo;
	public static Item TabIconDeadSpace;
	//Markers
	public static Item ItemMarker3A;
//Block
	public static Block BlockNecroCorruption;
	public static Block BlockMarker3A;
//Creative Tab
	public static CreativeTabs TabDeadSpace = new TabDeadSpace(CreativeTabs.getNextID(),"DeadSpaceClient");
//Achievement and Page
  //Necromorph Related
	//Cut Of Their Limbs!
	public static Achievement AchievementCutOfTheirLimbs;
  //Weaponry Related	
	//Strapped
	public static Achievement AchievementStrapped;
	//Circuit's Edge
	public static Achievement AchievementCircuitsEdge;
  //Medical Related
	//Healing Gel
	public static Achievement AchievementHealingGel;
	//Bleed Stopper
	public static Achievement AchievementBleedStopper;
	//EMT
	public static Achievement AchievementEMT;
  //Stasis Related
	//Slow Mo
	public static Achievement AchievementSlowMo;
	  //Achievement Page
	  public static AchievementPage DeadSpacePage;
//Projectile implement
	  public static DamageSource causeDSProjectileDamage(ProjectilePlasmaEnergy par0EntityArrow, Entity par1Entity) 
	  {
		  return (new EntityDamageSourceIndirect("plasmaenergyprojectile", par0EntityArrow, par1Entity)).setProjectile();
	  }

@Instance("DeadSpace") //The instance, this is very important later on
public static deadspacemod instance = new deadspacemod();

@SidedProxy(clientSide = "mods.masquitt0.deadspacemod.client.core.ClientProxy", serverSide = "mods.masquitt0.deadspacemod.client.core.ClientProxy") //Tells Forge the location of your proxies
public static CommonProxy proxy;

@PreInit()
public void loadConfig(FMLPreInitializationEvent c) {
ConfigCore cc = new ConfigCore();

cc.loadConfig(c);

//Items
	//Weaponry
	PlasmaCutter211V = (new PlasmaCutter211V(cc.plasmaCutter211V_itemID));
	SeekerRifle = (new SeekerRifle(cc.seekerRifle_itemID));
	//Projectile
	ProjectilePlasmaEnergy = (new Item(cc.plasmaEnergy_projectileID));
	//Ammo
	AmmoUniversal = new AmmoUniversal(cc.ammoUniversal_itemID);
	AmmoPlasmaEnergy = new AmmoPlasmaEnergy(cc.ammoPlasmaEnergy_itemID);
	AmmoJavelinSpears = new AmmoJavelinSpears(cc.ammoJavelinSpears_itemID);
	AmmoSeekerShells = new AmmoSeekerShells(cc.ammoSeekerShells_itemID);
	//Medical Packs
	MedicPackSmall = new MedicPackSmall(cc.medicPackSmall_itemID, 4, 2.0F, true);
	MedicPackMedium = new MedicPackMedium(cc.medicPackMedium_itemID, 6, 2.0F, true);
	MedicPackLarge = new MedicPackLarge(cc.medicPackLarge_itemID, 12, 2.0F, true);
	//Resources
	ResourceScrapMetal = new ResourceScrapMetal(cc.resourceScrapMetal_itemID);
	ResourceTungsten = new ResourceTungsten(cc.resourceTungsten_itemID);
	ResourceTransducer = new ResourceTransducer(cc.resourceTransducer_itemID);
	ResourceSomaticGel = new ResourceSomaticGel(cc.resourceSomaticGel_itemID);
	//Misc
	ItemCredits = new ItemCredits(cc.itemCredits_itemID);
	//Icons
	AchievementIconCutOfTheirLimbs = new AchievementIconCutOfTheirLimbs(cc.achievementCutOfTheirLimbs_iconID);
	AchievementIconStrapped = new AchievementIconStrapped(cc.achievementStrapped_iconID);
	AchievementIconCircuitsEdge = new AchievementIconCircuitsEdge(cc.achievementCircuitsEdge_iconID);
	AchievementIconHealingGel = new AchievementIconHealingGel(cc.achievementHealingGel_iconID);
	AchievementIconBleedStopper = new AchievementIconBleedStopper(cc.achievementBleedStopper_iconID);
	AchievementIconEMT = new AchievementIconEMT(cc.achievementEMT_iconID);
	AchievementIconSlowMo = new AchievementIconSlowMo(cc.achievementSlowMo_iconID);
	
	TabIconDeadSpace = new TabIconDeadSpace(cc.tabDeadSpace_iconID);
	//Markers
	//ItemMarker3A = (new ItemPlacerMarker3A(cc.itemMarker3a_itemID, null)).setUnlocalizedName("ItemMarker3A");
//Blocks
	//Necro Blocks
	BlockNecroCorruption = new BlockNecroCorruption(cc.blockNecroCorruption_blockID, 0, Material.rock);
//Achievements
 //Necromorph Related
    //Cut Of Their Limbs!	
    AchievementCutOfTheirLimbs =  new Achievement(270, "AchievementCutOfTheirLimbs", 1, 2, deadspacemod.AchievementIconCutOfTheirLimbs, AchievementList.killEnemy).setSpecial().registerAchievement();
 //Weaponry Related
    //Strapped
    AchievementStrapped =  new Achievement(271, "AchievementStrapped", 5, 2, deadspacemod.AchievementIconStrapped, AchievementList.buildWorkBench).setSpecial().registerAchievement();
    //Circuit's Edge
    AchievementCircuitsEdge =  new Achievement(272, "AchievementCircuitsEdge", 5, 4, deadspacemod.AchievementIconCircuitsEdge, deadspacemod.AchievementCircuitsEdge).setSpecial().registerAchievement();
 //Medical Related
    //Healing Gel
    AchievementHealingGel =  new Achievement(273, "AchievementHealingGel", 7, 2, deadspacemod.AchievementIconHealingGel, AchievementList.buildWorkBench).setSpecial().registerAchievement();
    //Bleed Stopper
    AchievementBleedStopper =  new Achievement(274, "AchievementBleedStopper", 7, 4, deadspacemod.AchievementIconBleedStopper, AchievementList.buildWorkBench).setSpecial().registerAchievement();
    //EMT
    AchievementEMT =  new Achievement(275, "AchievementEMT", 7, 6, deadspacemod.AchievementIconEMT, AchievementList.buildWorkBench).setSpecial().registerAchievement();
 //Stasis Related
    AchievementSlowMo =  new Achievement(276, "AchievementSlowMo", 7, 8, deadspacemod.AchievementIconSlowMo, AchievementList.buildWorkBench).setSpecial().registerAchievement();
//Achievement Page
	DeadSpacePage = new AchievementPage("Dead Space", AchievementCutOfTheirLimbs, AchievementStrapped, AchievementCircuitsEdge, AchievementHealingGel, AchievementBleedStopper, AchievementEMT, AchievementSlowMo);
}
@Init
public void load(FMLInitializationEvent event){
//Necromorph	
  //Slasher Registries
		//Main Entity Registry
		EntityRegistry.registerModEntity(EntitySlasher.class, "Slasher", 1, this, 80, 3, true);
		//Entity Natural Spawning
		EntityRegistry.addSpawn(EntitySlasher.class, 10, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		//Entity Render
		RenderingRegistry.registerEntityRenderingHandler(EntitySlasher.class, new RenderSlasher(new ModelSlasher(), 0.6F));
		//Egg Registry
		registerEntityEgg(EntitySlasher.class,  0xFFFDB8, 0xFF0000);
		//Dungeon Spawning
		DungeonHooks.addDungeonMob("Slasher", 150);
	//Enhanced Slasher Registries	
		//Main Entity Registry
		EntityRegistry.registerModEntity(EntityEnhancedSlasher.class, "EnhancedSlasher", 2, this, 80, 3, true);
		//Entity Natural Spawning
		EntityRegistry.addSpawn(EntityEnhancedSlasher.class, 10, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		//Entity Render
		RenderingRegistry.registerEntityRenderingHandler(EntityEnhancedSlasher.class, new RenderEnhancedSlasher(new ModelEnhancedSlasher(), 0.6F));
		//Egg Registry
		registerEntityEgg(EntityEnhancedSlasher.class,  0xFFFDB8, 0xFF0000);
		//Dungeon Spawning
		DungeonHooks.addDungeonMob("EnhancedSlasher", 150);
	/** //Human Slasher Registries	
		//Main Entity Registry
		EntityRegistry.registerModEntity(EntityHumanMaleSlasher.class, "HumanMaleSlasher", 3, this, 80, 3, true);
		//Entity Natural Spawning
		EntityRegistry.addSpawn(EntityHumanMaleSlasher.class, 10, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		//Entity Render
		RenderingRegistry.registerEntityRenderingHandler(EntityHumanMaleSlasher.class, new RenderHumanMaleSlasher(new ModelHumanMaleSlasher(), 0.6F));
		//Egg Registry
		registerEntityEgg(EntityHumanMaleSlasher.class,  0xFFFDB8, 0xFF0000);
		//Dungeon Spawning
		DungeonHooks.addDungeonMob("EnhancedSlasher", 150);
	*/	
		
	//Leaper Registries
		//Main Entity Registry
		EntityRegistry.registerModEntity(EntityLeaper.class, "Leaper", 3, this, 80, 3, true);
		//Entity Natural Spawning
		EntityRegistry.addSpawn(EntityLeaper.class, 10, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		//Entity Render
		RenderingRegistry.registerEntityRenderingHandler(EntityLeaper.class, new RenderLeaper(new ModelLeaper(), 0.6F));
		//Egg Registry
		registerEntityEgg(EntityLeaper.class,  0xFFFDB8, 0xFF0000);
		//Dungeon Spawning
		DungeonHooks.addDungeonMob("Leaper", 150);
	//Enhanced Leaper Registries
		//Main Entity Registry
		EntityRegistry.registerModEntity(EntityEnhancedLeaper.class, "EnhancedLeaper", 4, this, 80, 3, true);
		//Entity Natural Spawning
		EntityRegistry.addSpawn(EntityEnhancedLeaper.class, 10, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		//Entity Render
		RenderingRegistry.registerEntityRenderingHandler(EntityEnhancedLeaper.class, new RenderEnhancedLeaper(new ModelEnhancedLeaper(), 0.6F));
		//Egg Registry
		registerEntityEgg(EntityEnhancedLeaper.class,  0xFFFDB8, 0xFF0000);
		//Dungeon Spawning
		DungeonHooks.addDungeonMob("EnhancedLeaper", 150);
//Humans
	//Isaac Clarke Registries
		//Main Entity Registry
		EntityRegistry.registerModEntity(EntityIsaacClarke.class, "IsaacClarke", 5, this, 80, 3, true);
		//Entity Natural Spawning
		EntityRegistry.addSpawn(EntityIsaacClarke.class, 2, 2, 4, EnumCreatureType.ambient, BiomeGenBase.beach, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.swampland);
		//Entity Render
		RenderingRegistry.registerEntityRenderingHandler(EntityIsaacClarke.class, new RenderIsaacClarke(new ModelIsaacClarke(), 0.6F));
		//Egg Registry
		registerEntityEgg(EntityIsaacClarke.class,  0x996600, 0x3366FF);
//Item Custom Renderer
		//Plasma Cutter 211V
		MinecraftForgeClient.registerItemRenderer(deadspacemod.PlasmaCutter211V.itemID, (IItemRenderer)new ItemRenderPlasmaCutter211V());
		//Seeker Rifle
		MinecraftForgeClient.registerItemRenderer(deadspacemod.SeekerRifle.itemID, (IItemRenderer)new ItemRenderSeekerRifle());
		//Page
		AchievementPage.registerAchievementPage(DeadSpacePage);
//Achievement Name and Description
		this.addAchievementLocalizations();
		{
	//Necromorph Related		
		//Cut Of Their Limbs!
		this.addAchievementName("AchievementCutOfTheirLimbs", "Cut Of Their Limbs!");
		this.addAchievementDesc("AchievementCutOfTheirLimbs", "Executed your first necromorph.");
	//Weaponry Related
		//Strapped
		this.addAchievementName("AchievementStrapped", "Strapped");
		this.addAchievementDesc("AchievementStrapped", "Crafted your first weapon.");
		//Circuit's Edge
		this.addAchievementName("AchievementCircuitsEdge", "Circuit's Edge");
		this.addAchievementDesc("AchievementCircuitsEdge", "Applied a circuit to your crafted weapon.");
	//Medical Related
		//Healing Gel
		this.addAchievementName("AchievementHealingGel", "Healing Gel");
		this.addAchievementDesc("AchievementHealingGel", "Crafted a small medic-pack.");
		//Bleed Stopper
		this.addAchievementName("AchievementBleedStopper", "Bleed Stopper");
		this.addAchievementDesc("AchievementBleedStopper", "Crafted a medium medic-pack.");
		//EMT
		this.addAchievementName("AchievementEMT", "EMT");
		this.addAchievementDesc("AchievementEMT", "Crafted a large medic-pack.");
	//Stasis Related
		//Slow Mo
		this.addAchievementName("AchievementSlowMo", "Slow Mo");
		this.addAchievementDesc("AchievementSlowMo", "Executed a necromorph while it's on Stasis.");
		}
}
//Achievement Description Integer
		private void addAchievementName(String ach, String name)
		{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
		}

		private void addAchievementDesc(String ach, String desc)
		{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
//Achievement Handlers
		GameRegistry.registerCraftingHandler(new CraftingHandler());
		//GameRegistry.registerPickupHandler(new PickupHandler());
//Proxies
		proxy.addNames();
		proxy.addRecipes();
		proxy.registerBlocks();
		proxy.registerTiles();
//Keybind
		KeyBindingRegistry.registerKeyBinding(new DSKeyHandler());
//Rendering
		RenderingRegistry.registerEntityRenderingHandler(ProjectilePlasmaEnergy.class, new RenderProjectilePlasmaEnergy());
//Sound
		MinecraftForge.EVENT_BUS.register(new SoundSlasher());
		
}
}

