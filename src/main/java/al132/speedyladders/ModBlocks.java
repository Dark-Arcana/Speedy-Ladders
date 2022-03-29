package al132.speedyladders;



import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static List<Block> modBlocks = new ArrayList<>();

    public static LadderBlock stoneLadder = new BlockLadderBase("stone_ladder", Config.STONE_SPEED.get());
    public static LadderBlock ironLadder = new BlockLadderBase("iron_ladder", Config.IRON_SPEED.get());
    public static LadderBlock goldLadder = new BlockLadderBase("gold_ladder", Config.GOLD_SPEED.get());
    public static LadderBlock diamondLadder = new BlockLadderBase("diamond_ladder", Config.DIAMOND_SPEED.get());
}