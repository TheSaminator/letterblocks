package net.thesaminator.letterblocks

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

const val MOD_ID = "letterblocks"

val itemGroup = FabricItemGroupBuilder.create(Identifier(MOD_ID, "letterblocks"))
	.icon { ItemStack(items[LetterBlockAddress(Letter.A, false)]) }
	.build()

val items = mutableMapOf<LetterBlockAddress, BlockItem>()

fun init() {
	LetterBlockAddress.values().forEach { addr ->
		items[addr] = Registry.register(
			Registry.ITEM,
			addr.identifier,
			BlockItem(
				Registry.register(
					Registry.BLOCK,
					addr.identifier,
					LetterBlock()
				),
				Item.Settings().group(itemGroup)
			)
		)
	}
}

@Environment(EnvType.CLIENT)
fun initClient() {
	items.forEach { (_, item) ->
		BlockRenderLayerMap.INSTANCE.putBlock(item.block, RenderLayer.getCutout())
	}
}
