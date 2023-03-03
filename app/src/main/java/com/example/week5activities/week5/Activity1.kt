@file:Suppress("NAME_SHADOWING")

package com.example.week5activities.week5

val bookName = arrayListOf(
    "The Frogs & the Ox", "Belling the Cat", "The Town Mouse & the Country Mouse",
    "The Fox & the Grapes", "The Wolf & the Crane", "The Lion & the Mouse",
    "The Gnat & the Bull", "The Plane Tree", "The Owl & the Grasshopper",
    "The Oak & the Reeds", "The Crow & the Pitcher", "The Two Goats",
    "The Wild Boar & the Fox", "The Heron", "The Fox & the Stork",
    "The Stag & His Reflection", "The Cock & the Fox", "The Fox & the Goat",
    "The Fox & the Leopard", "The Frog & the Mouse",
)

fun main() {
    while (true) {
        println("====My Fable's Collections!====")
        println("1. Book Records.")
        println("2. Add or Remove book.")
        println("3. Show book")
        println("4. Wild Search")
        println("5. Exit")
        print("Select option: ")
        val choice = readLine()?.toIntOrNull() ?: 6
        if (choice > 6) {
            println("\nInvalid option!\n")
        }
        when (choice) {
            1 -> isBookInRecord()
            2 -> addAndRemove()
            3 -> showBooks()
            4 -> searchBookWildSearch()
            5 -> {
                println("\nTerminating Program!...")
                return
            }
            6 -> {
                println("\nInvalid option!\n")
            }
        }
    }
}

fun isBookInRecord() {
    while (true) {
        println("\n(type 'done' to exit)")
        println("Please enter a book you want to check:")
        val bookInRecord = readLine().toString()

        val searchResults = searchBookNameThatEquals(bookName, bookInRecord)
        if (bookInRecord.isBlank()) {
            println("Please enter book name!")
            continue
        } else if (bookInRecord.contains("done")) {
            println("")
            break
        } else if (searchResults.isEmpty()) {
            println("\nBook is not in the Record!")
        } else {
            println("\nBook is in the Record!")
        }
    }
}

fun addAndRemove() {
    println("\nwhat do you want to do:")
    println("type 'add' to add books or type 'remove' to remove books")
    val response = readLine().toString()
    if (response.isBlank()) {
        println("Invalid response!\n")
    } else if (!response.contains("add"))
        if (!response.contains("remove")) {
            println("Invalid response!\n")
        }
    when (response) {
        "add" -> {
            while (true) {
                println("\n(type 'done' to exit)")
                println("Enter Book you want to add:")
                val addNewBook = readLine().toString()
                if (addNewBook.isBlank()) {
                    println("Please enter book name!")
                } else if (addNewBook.contains("done")) {
                    println("")
                    break
                } else if (addNewBook.isNotEmpty()) {
                    bookName.add(addNewBook)
                    println("\nBook added successfully!")
                    continue
                } else if (addNewBook.isEmpty()) {
                    println("Invalid book name!")
                    continue
                }
            }
        }
        "remove" -> {
            while (true) {
                println("\n(type 'done' to exit)")
                println("Enter book name you want to remove:")
                val removeBook = readLine().toString()
                val searchResults = searchBookNameThatEquals(bookName, removeBook)
                if (removeBook.isBlank()) {
                    println("Please enter book name!")
                } else if (removeBook.contains("done")) {
                    println("")
                    break
                } else if (searchResults.isNotEmpty()) {
                    bookName.remove(removeBook)
                    println("\nBook removed successfully!\n")
                    continue
                } else {
                    println("\nBook is not in the list!\n")
                    continue
                }
            }
        }
    }
}

fun showBooks() {
    println("\nCollections:")
    for (i in bookName)
        println("> $i")
    println("Total books: ${countBooks()}\n")
}

fun countBooks(): Int {
    return bookName.size
}

fun searchBookWildSearch() {
    while (true) {
        println("\nEnter Book you want to search: ")
        val searchBook = readLine().toString()
        val wildSearch = searchBookName(bookName, searchBook)
        if (searchBook.isBlank()) {
            println("Please enter book name!")
        } else if (searchBook.contains("done")) {
            println("")
            break
        } else if (wildSearch.isNotEmpty()) {
            println("\nSearch results:")
            wildSearch.forEach {
                println("> $it")
            }
        } else {
            println("\nNo results found!")
        }
    }
}
// this search function is for searchBookWildSearch function,
// it will find and print the list of bookname that matches to the user input
fun searchBookName(bookName: ArrayList<String>, query: String): List<String> {
    val keywords = query.split(" ").map { it.trim() }
    return bookName.filter { bookName ->
        keywords.all {
            bookName.contains(it, ignoreCase = true)
        }
    }
}
// this search function is for isBookInRecord function,
// it will find the word given by the user that's EQUAL to the list of bookname
fun searchBookNameThatEquals(bookName: ArrayList<String>, query: String): List<String> {
    val keywords = query.map { it }
    return bookName.filter { bookName ->
        keywords.all {
            bookName.equals(query, ignoreCase = true)
        }
    }
}