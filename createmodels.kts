import java.io.File

val letters = listOf(
	"a",
	"a_longum",
	"ae",
	"b",
	"c",
	"d",
	"e",
	"e_longum",
	"f",
	"g",
	"h",
	"i",
	"i_longum",
	"j",
	"k",
	"l",
	"m",
	"n",
	"o",
	"o_longum",
	"oe",
	"p",
	"q",
	"r",
	"s",
	"t",
	"u",
	"u_longum",
	"v",
	"v_longum",
	"w",
	"x",
	"y",
	"y_longum",
	"z",
	"interpunct"
)

val colors = listOf("white", "black")

val root = File("src/main/resources/assets/letterblocks")

var languageJson = "{\r\n"

colors.forEach { color ->
	letters.forEach { letter ->
		val model = File(root, "models/block/${color}_${letter}.json")
		
		model.writeText("""
			{
				"parent": "letterblocks:block/letter_block",
				"textures": {
					"texture": "letterblocks:block/${color}_${letter}"
				}
			}
		""".trimIndent())
		
		val blockState = File(root, "blockstates/${color}_${letter}.json")
		
		blockState.writeText("""
			{
				"variants": {
					"facing=east": { "model": "letterblocks:block/${color}_${letter}", "y": 90 },
					"facing=south": { "model": "letterblocks:block/${color}_${letter}", "y": 180 },
					"facing=west": { "model": "letterblocks:block/${color}_${letter}", "y": 270 },
					"facing=north": { "model": "letterblocks:block/${color}_${letter}" }
				}
			}
		""".trimIndent())
		
		val item = File(root, "models/item/${color}_${letter}.json")
		
		item.writeText("""
			{
				"parent": "minecraft:item/generated",
				"textures": {
					"layer0": "letterblocks:block/${color}_${letter}"
				}
			}
		""".trimIndent())
		
		val name = "${color.capitalize()} ${letter.split("_").map { it.capitalize() }.joinToString(separator = " ")}"
		
		languageJson += "\t\"block.letterblocks.${color}_${letter}\": \"$name\",\r\n"
	}
}

languageJson += "\t\"itemGroup.letterblocks.letterblocks\": \"Letter Blocks\"\r\n"
languageJson += "}\r\n"

val language = File(root, "lang/en_us.json")
language.writeText(languageJson)
