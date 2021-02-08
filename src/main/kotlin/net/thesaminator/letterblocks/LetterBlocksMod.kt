package net.thesaminator.letterblocks

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

const val MOD_ID = "letterblocks"

val latinItemGroup = FabricItemGroupBuilder.create(Identifier(MOD_ID, "letterblocks_latin"))
	.icon { ItemStack(items[LetterBlockAddress(Letter.LATIN_A, LetterColor.BLACK)]) }
	.build()

val russianItemGroup = FabricItemGroupBuilder.create(Identifier(MOD_ID, "letterblocks_russian"))
	.icon { ItemStack(items[LetterBlockAddress(Letter.RUSSIAN_JA, LetterColor.BLACK)]) }
	.build()

val klingonItemGroup = FabricItemGroupBuilder.create(Identifier(MOD_ID, "letterblocks_klingon"))
	.icon { ItemStack(items[LetterBlockAddress(Letter.KLINGON_TLH, LetterColor.BLACK)]) }
	.build()

val items = mutableMapOf<LetterBlockAddress, BlockItem>()

fun init() {
	LetterBlockAddress.withAlphabet(Alphabet.LATIN).forEach { addr ->
		items[addr] = Registry.register(
			Registry.ITEM,
			addr.identifier,
			BlockItem(
				Registry.register(
					Registry.BLOCK,
					addr.identifier,
					LetterBlock()
				),
				Item.Settings().group(latinItemGroup)
			)
		)
	}
	
	LetterBlockAddress.withAlphabet(Alphabet.RUSSIAN).forEach { addr ->
		items[addr] = Registry.register(
			Registry.ITEM,
			addr.identifier,
			BlockItem(
				Registry.register(
					Registry.BLOCK,
					addr.identifier,
					LetterBlock()
				),
				Item.Settings().group(russianItemGroup)
			)
		)
	}
	
	LetterBlockAddress.withAlphabet(Alphabet.KLINGON).forEach { addr ->
		items[addr] = Registry.register(
			Registry.ITEM,
			addr.identifier,
			BlockItem(
				Registry.register(
					Registry.BLOCK,
					addr.identifier,
					LetterBlock()
				),
				Item.Settings().group(klingonItemGroup)
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
