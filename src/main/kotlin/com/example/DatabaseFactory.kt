package com.example

import org.jetbrains.exposed.sql.*


object DatabaseConfig {
    private const val url = "jdbc:sqlite:database.sqlite"
    private const val driver = "org.sqlite.JDBC"

    fun connect(): Database {
        return Database.connect(url, driver)
    }
}

object User : Table("user") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 255)
    val email = varchar("email", length = 50)
    val password = varchar("password", length = 255)
    val phone_number = varchar("phone_number", length = 255)
    val birth_date = varchar("birth_date", length = 255)

}