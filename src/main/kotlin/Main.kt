enum class Color {
    RED,
    GREEN,
    BLUE,
    GRAY,
    YELLOW,
    ORANGE,
    WHITE
}

data class ToDoListItem(var checked: Boolean, val name: String) {
    override fun toString(): String {
        if(checked) return "X - $name"
        else return "O - $name"
    }

    fun changeCondition() {
        checked = !checked
    }
}

sealed class NoteContent {
    data class Text(val strings: String) : NoteContent() {
        override fun toString(): String {
            return "$strings\n"
        }
    }

    data class Image(val link: String) : NoteContent() {
        override fun toString(): String {
            return "Image located on $link\n"
        }
    }

    data class ToDoList(val list: List<ToDoListItem>) : NoteContent() {
        override fun toString(): String {
            var res: String = ""
            for (task in list) res += "$task \n"
            return res
        }
    }
}

data class Note(
    var title: String,
    var color: Color,
    val content: List<NoteContent>
) {
    override fun toString(): String {
        var res: String = "\nЗАМЕТКА - $title с $color фоном\n"
        for (con in content) res += "$con"
        return res
    }
}

fun main() {
    val users: List<Note> = listOf(
        Note(
            "Подарки",
            Color.BLUE,
            listOf(
                NoteContent.Image("https://ixbt.online/live/images/original/01/65/94/2019/11/29/e471fcb78a.jpg"),
                NoteContent.ToDoList(
                    listOf(
                        ToDoListItem(true, "Мама"),
                        ToDoListItem(true, "Папа"),
                        ToDoListItem(false, "Бабушка")
                    )
                ),
                NoteContent.Text(
                    """Подарки надо заказать до 25, иначе не успеют доставить
                |Сходить в этажи""".trimMargin()
                )
            )
        ),
        Note(
            "Долги",
            Color.RED,
            listOf(
                NoteContent.ToDoList(
                    listOf(
                        ToDoListItem(false, "Kotlin"),
                        ToDoListItem(false, "GeomMod"),
                        ToDoListItem(true, "SQL")
                    )
                ),
                NoteContent.Text(
                    """Как же хочется каникулы...
                |На каток...
                |На павука...
            """.trimMargin()
                ),
                NoteContent.Image("https://memepedia.ru/wp-content/uploads/2021/07/never-gonna-give-you-up.jpg")
            )
        )
    )
    println(users)
}