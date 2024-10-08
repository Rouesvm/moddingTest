package com.rouesvm.emeraldtools.blocks;

import com.rouesvm.emeraldtools.Main;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.utils.PolymerClientDecoded;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public class GenericPolymerBlock extends Block implements PolymerTexturedBlock, PolymerKeepModel, PolymerClientDecoded {
    private final BlockState polymerBlockState;

    public GenericPolymerBlock(Settings settings, String name) {
        super(settings);
        this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK,
                PolymerBlockModel.of(Identifier.of(Main.MOD_ID, name.toLowerCase()))
        );
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return this.polymerBlockState;
    }
}
