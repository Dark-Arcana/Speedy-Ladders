package al132.speedyladders;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class BlockLadderBase extends LadderBlock {

    double speed;

    public BlockLadderBase(String name, double speed) {
        super(BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0f).noOcclusion()
                .sound(SoundType.LADDER));
        //.hardnessAndResistance(1.0F)
        //  .notSolid());

        this.speed = speed;
        setRegistryName(name);
        ModBlocks.modBlocks.add(this);
    }
}