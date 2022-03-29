package al132.speedyladders;


import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.math.Vector3d;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("speedyladders")
public class SpeedyLadders {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("speedyladders") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.diamondLadder);
        }
    };


    public SpeedyLadders() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("speedyladders-common.toml"));
    }

    @SubscribeEvent
    public void onPlayerTick(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntityLiving() instanceof Player) {
            Player player = (Player) e.getEntityLiving();
            Block block = player.level.getBlockState(new BlockPos(player.position().x, player.position().y,
                    player.position().z)).getBlock();
            if (block instanceof BlockLadderBase) {
                BlockLadderBase ladder = (BlockLadderBase) block;
                if (!player.isCrouching()) {
                    if (player.horizontalCollision) {
                        player.move(MoverType.SELF, new Vec3(0, ladder.speed, 0));
                    } else player.move(MoverType.SELF, new Vec3(0, -ladder.speed, 0));
                }
            }
        }
    }

    private void clientSetup(FMLClientSetupEvent e) {
        for (Block block : ModBlocks.modBlocks) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> e) {
            ModBlocks.modBlocks.forEach(x -> e.getRegistry().register(x));
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> e) {
            ModBlocks.modBlocks.forEach(x ->
                    e.getRegistry().register(new BlockItem(x, new Item.Properties().tab(ITEM_GROUP)).setRegistryName(x.getRegistryName())));
        }
    }
}