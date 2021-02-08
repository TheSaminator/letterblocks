package net.thesaminator.letterblocks

import net.minecraft.util.Identifier

data class LetterBlockAddress(val letter: Letter, val color: LetterColor) {
	val identifier: Identifier
		get() = Identifier(MOD_ID, "${color.name.toLowerCase()}_${letter.name.toLowerCase()}")
	
	companion object {
		fun values() = LetterColor.values().flatMap { color ->
			Letter.values().map { letter ->
				LetterBlockAddress(letter, color)
			}
		}
		
		fun withAlphabet(alphabet: Alphabet): List<LetterBlockAddress> {
			val letters = Letter.values().filter { it.alphabet == alphabet }
			
			return LetterColor.values().flatMap { color ->
				letters.map { letter ->
					LetterBlockAddress(letter, color)
				}
			}
		}
	}
}
