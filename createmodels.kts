import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.io.File
import javax.imageio.ImageIO

val letters = mapOf(
	// Latin
	"latin_a" to "A",
	"latin_a_longum" to "Long A",
	"latin_ae" to "AE",
	"latin_b" to "B",
	"latin_c" to "C",
	"latin_d" to "D",
	"latin_e" to "E",
	"latin_e_longum" to "Long E",
	"latin_f" to "F",
	"latin_g" to "G",
	"latin_h" to "H",
	"latin_i" to "I",
	"latin_i_longum" to "Long I",
	"latin_j" to "J",
	"latin_k" to "K",
	"latin_l" to "L",
	"latin_m" to "M",
	"latin_n" to "N",
	"latin_o" to "O",
	"latin_o_longum" to "Long O",
	"latin_oe" to "OE",
	"latin_p" to "P",
	"latin_q" to "Q",
	"latin_r" to "R",
	"latin_s" to "S",
	"latin_t" to "T",
	"latin_u" to "U",
	"latin_u_longum" to "Long U",
	"latin_v" to "V",
	"latin_v_longum" to "Long V",
	"latin_w" to "W",
	"latin_x" to "X",
	"latin_y" to "Y",
	"latin_y_longum" to "Long Y",
	"latin_z" to "Z",
	"latin_interpunct" to "Interpunct",
	// Russian
	"russian_a" to "Russian A",
	"russian_b" to "Russian B",
	"russian_v" to "Russian V",
	"russian_g" to "Russian G",
	"russian_d" to "Russian D",
	"russian_je" to "Russian Ye",
	"russian_jo" to "Russian Yo",
	"russian_zh" to "Russian Zh",
	"russian_z" to "Russian Z",
	"russian_i" to "Russian I",
	"russian_j" to "Russian Short I",
	"russian_k" to "Russian K",
	"russian_l" to "Russian L",
	"russian_m" to "Russian M",
	"russian_n" to "Russian N",
	"russian_o" to "Russian O",
	"russian_p" to "Russian P",
	"russian_r" to "Russian R",
	"russian_s" to "Russian S",
	"russian_t" to "Russian T",
	"russian_u" to "Russian U",
	"russian_f" to "Russian F",
	"russian_h" to "Russian KH",
	"russian_c" to "Russian TS",
	"russian_cz" to "Russian CH",
	"russian_sz" to "Russian SH",
	"russian_szcz" to "Russian SHCH",
	"russian_tvjordyj_znak" to "Russian Hard Sign",
	"russian_y" to "Russian Y",
	"russian_mjahkij_znak" to "Russian Soft Sign",
	"russian_e" to "Russian E",
	"russian_ju" to "Russian Yu",
	"russian_ja" to "Russian Ya",
	"russian_jat" to "Russian Yat",
	"russian_fita" to "Russian Fita",
	"russian_izhica" to "Russian Izhitsa",
	// pIqaD
	"klingon_a" to "Klingon a",
	"klingon_b" to "Klingon b",
	"klingon_ch" to "Klingon ch",
	"klingon_d" to "Klingon D",
	"klingon_e" to "Klingon e",
	"klingon_gh" to "Klingon gh",
	"klingon_hh" to "Klingon H",
	"klingon_i" to "Klingon I",
	"klingon_j" to "Klingon j",
	"klingon_l" to "Klingon l",
	"klingon_m" to "Klingon m",
	"klingon_n" to "Klingon n",
	"klingon_ng" to "Klingon ng",
	"klingon_o" to "Klingon o",
	"klingon_p" to "Klingon p",
	"klingon_q" to "Klingon q",
	"klingon_qh" to "Klingon Q",
	"klingon_r" to "Klingon r",
	"klingon_s" to "Klingon S",
	"klingon_t" to "Klingon t",
	"klingon_tlh" to "Klingon tlh",
	"klingon_u" to "Klingon u",
	"klingon_v" to "Klingon v",
	"klingon_w" to "Klingon w",
	"klingon_y" to "Klingon y",
	"klingon_qaghwi" to "Klingon qaghwI'",
	"klingon_zero" to "Klingon 0",
	"klingon_one" to "Klingon 1",
	"klingon_two" to "Klingon 2",
	"klingon_three" to "Klingon 3",
	"klingon_four" to "Klingon 4",
	"klingon_five" to "Klingon 5",
	"klingon_six" to "Klingon 6",
	"klingon_seven" to "Klingon 7",
	"klingon_eight" to "Klingon 8",
	"klingon_nine" to "Klingon 9",
)

val colors = listOf("white", "red", "black")

val root = File("src/main/resources/assets/letterblocks")

var languageJson = "{\r\n"

letters.keys.forEach { letter ->
	val blackLetter = File(root, "textures/block/black_${letter}.png")
	val whiteLetter = File(root, "textures/block/white_${letter}.png")
	val redLetter = File(root, "textures/block/red_${letter}.png")
	
	if (whiteLetter.exists())
		whiteLetter.delete()
	
	if (redLetter.exists())
		redLetter.delete()
	
	val blackLetterPng = ImageIO.read(blackLetter)
	val whiteLetterPng = BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB)
	val redLetterPng = BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB)
	
	val pixels = (blackLetterPng.raster.dataBuffer as DataBufferByte).data
	val newPixels = IntArray(16 * 16)
	val redPixels = IntArray(16 * 16)
	(0 until 16 * 16).forEach { pxi ->
		val ai = pxi * 4
		
		val a = pixels[ai].toInt()
		
		val newArgb = (a shl 24) or (255 shl 16) or (255 shl 8) or 255
		newPixels[pxi] = newArgb
		
		val redArgb = (a shl 24) or (204 shl 16) or (51 shl 8) or 51
		redPixels[pxi] = redArgb
	}
	
	whiteLetterPng.setRGB(0, 0, 16, 16, newPixels, 0, 16)
	ImageIO.write(whiteLetterPng, "PNG", whiteLetter)
	
	redLetterPng.setRGB(0, 0, 16, 16, redPixels, 0, 16)
	ImageIO.write(redLetterPng, "PNG", redLetter)
}

colors.forEach { color ->
	val colorName = color.capitalize()
	
	letters.forEach { (letter, locName) ->
		val model = File(root, "models/block/${color}_${letter}.json")
		
		model.writeText(
			"""
			{
				"parent": "letterblocks:block/letter_block",
				"textures": {
					"texture": "letterblocks:block/${color}_${letter}"
				}
			}
		""".trimIndent()
		)
		
		val blockState = File(root, "blockstates/${color}_${letter}.json")
		
		blockState.writeText(
			"""
			{
				"variants": {
					"facing=east": { "model": "letterblocks:block/${color}_${letter}", "y": 90 },
					"facing=south": { "model": "letterblocks:block/${color}_${letter}", "y": 180 },
					"facing=west": { "model": "letterblocks:block/${color}_${letter}", "y": 270 },
					"facing=north": { "model": "letterblocks:block/${color}_${letter}" }
				}
			}
		""".trimIndent()
		)
		
		val item = File(root, "models/item/${color}_${letter}.json")
		
		item.writeText(
			"""
			{
				"parent": "minecraft:item/generated",
				"textures": {
					"layer0": "letterblocks:block/${color}_${letter}"
				}
			}
		""".trimIndent()
		)
		
		val name = "$colorName $locName"
		
		languageJson += "\t\"block.letterblocks.${color}_${letter}\": \"$name\",\r\n"
	}
}

languageJson += "\t\"itemGroup.letterblocks.letterblocks_latin\": \"Letter Blocks (Latin)\",\r\n"
languageJson += "\t\"itemGroup.letterblocks.letterblocks_russian\": \"Letter Blocks (Russian)\",\r\n"
languageJson += "\t\"itemGroup.letterblocks.letterblocks_klingon\": \"Letter Blocks (Klingon)\"\r\n"
languageJson += "}\r\n"

val language = File(root, "lang/en_us.json")
language.writeText(languageJson)
