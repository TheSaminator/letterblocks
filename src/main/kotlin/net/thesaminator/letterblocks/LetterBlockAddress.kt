package net.thesaminator.letterblocks

import net.minecraft.util.Identifier

data class LetterBlockAddress(val letter: Letter, val isWhite: Boolean) {
	val identifier: Identifier
		get() = Identifier(MOD_ID, "${if (isWhite) "white" else "black"}_${letter.name.toLowerCase()}")
	
	companion object {
		fun values() = Letter.values().map {
			LetterBlockAddress(it, false)
		} + Letter.values().map {
			LetterBlockAddress(it, true)
		}
	}
}
