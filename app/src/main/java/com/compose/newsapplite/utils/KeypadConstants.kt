package com.compose.newsapplite.utils

object KeypadConstants {
    enum class KeypadRow {
        KEYPAD_ROW_1,
        KEYPAD_ROW_2,
        KEYPAD_ROW_3,
        KEYPAD_ROW_4
    }

    enum class KeypadCharacter(val characterRowMap: Pair<String, KeypadRow>) {
        KEYPAD_CHARACTER_A(Pair("a", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_B(Pair("b", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_C(Pair("c", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_D(Pair("d", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_E(Pair("e", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_F(Pair("f", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_G(Pair("g", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_H(Pair("h", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_I(Pair("i", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_J(Pair("j", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_K(Pair("k", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_L(Pair("l", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_M(Pair("m", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_N(Pair("n", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_O(Pair("o", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_P(Pair("p", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_Q(Pair("q", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_R(Pair("r", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_S(Pair("s", KeypadRow.KEYPAD_ROW_2)),
        KEYPAD_CHARACTER_T(Pair("t", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_U(Pair("u", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_V(Pair("v", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_W(Pair("w", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_X(Pair("x", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_Y(Pair("y", KeypadRow.KEYPAD_ROW_1)),
        KEYPAD_CHARACTER_Z(Pair("z", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_CAPS(Pair("caps", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_BACKSPACE(Pair("backspace", KeypadRow.KEYPAD_ROW_3)),
        KEYPAD_CHARACTER_SPACE(Pair("space bar", KeypadRow.KEYPAD_ROW_4)),
        KEYPAD_CHARACTER_OK(Pair("ok", KeypadRow.KEYPAD_ROW_4)),
    }

    private val itemsForRow1 = listOf(
        KeypadCharacter.KEYPAD_CHARACTER_Q,
        KeypadCharacter.KEYPAD_CHARACTER_W,
        KeypadCharacter.KEYPAD_CHARACTER_E,
        KeypadCharacter.KEYPAD_CHARACTER_R,
        KeypadCharacter.KEYPAD_CHARACTER_T,
        KeypadCharacter.KEYPAD_CHARACTER_Y,
        KeypadCharacter.KEYPAD_CHARACTER_U,
        KeypadCharacter.KEYPAD_CHARACTER_I,
        KeypadCharacter.KEYPAD_CHARACTER_O,
        KeypadCharacter.KEYPAD_CHARACTER_P
    )
    private val itemsForRow2 = listOf(
        KeypadCharacter.KEYPAD_CHARACTER_A,
        KeypadCharacter.KEYPAD_CHARACTER_S,
        KeypadCharacter.KEYPAD_CHARACTER_D,
        KeypadCharacter.KEYPAD_CHARACTER_F,
        KeypadCharacter.KEYPAD_CHARACTER_G,
        KeypadCharacter.KEYPAD_CHARACTER_H,
        KeypadCharacter.KEYPAD_CHARACTER_J,
        KeypadCharacter.KEYPAD_CHARACTER_K,
        KeypadCharacter.KEYPAD_CHARACTER_L
    )
    private val itemsForRow3 = listOf(
        KeypadCharacter.KEYPAD_CHARACTER_CAPS,
        KeypadCharacter.KEYPAD_CHARACTER_Z,
        KeypadCharacter.KEYPAD_CHARACTER_X,
        KeypadCharacter.KEYPAD_CHARACTER_C,
        KeypadCharacter.KEYPAD_CHARACTER_V,
        KeypadCharacter.KEYPAD_CHARACTER_B,
        KeypadCharacter.KEYPAD_CHARACTER_N,
        KeypadCharacter.KEYPAD_CHARACTER_M,
        KeypadCharacter.KEYPAD_CHARACTER_BACKSPACE
    )
    private val itemsForRow4 = listOf(
        KeypadCharacter.KEYPAD_CHARACTER_SPACE,
        KeypadCharacter.KEYPAD_CHARACTER_OK
    )


    fun getKeypadItems(keypadRow: KeypadRow): List<KeypadCharacter> {
        return when (keypadRow) {
            KeypadRow.KEYPAD_ROW_1 -> itemsForRow1
            KeypadRow.KEYPAD_ROW_2 -> itemsForRow2
            KeypadRow.KEYPAD_ROW_3 -> itemsForRow3
            KeypadRow.KEYPAD_ROW_4 -> itemsForRow4
        }
    }
}