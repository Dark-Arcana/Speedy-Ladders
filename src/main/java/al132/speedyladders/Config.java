package al132.speedyladders;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class Config {
    public static String CATEGORY_GENERAL = "general";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.DoubleValue STONE_SPEED;
    public static ForgeConfigSpec.DoubleValue IRON_SPEED;
    public static ForgeConfigSpec.DoubleValue GOLD_SPEED;
    public static ForgeConfigSpec.DoubleValue DIAMOND_SPEED;


    static {
        initWorldgenConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static void initWorldgenConfig() {
        COMMON_BUILDER.comment("Speed").push(CATEGORY_GENERAL);
        STONE_SPEED = COMMON_BUILDER.comment("Stone Ladder Speed")
                .defineInRange("stoneLadderSpeed", 0.06, 0.01, 10);
        IRON_SPEED = COMMON_BUILDER.comment("Iron Ladder Speed")
                .defineInRange("ironLadderSpeed", 0.12, 0.01, 10);
        GOLD_SPEED = COMMON_BUILDER.comment("Gold Ladder Speed")
                .defineInRange("goldLadderSpeed", 0.20, 0.01, 10);
        DIAMOND_SPEED = COMMON_BUILDER.comment("Diamond Ladder Speed")
                .defineInRange("diamondLadderSpeed", 0.30, 0.01, 10);
        COMMON_BUILDER.pop();
    }


    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}
