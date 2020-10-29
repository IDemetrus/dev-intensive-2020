package com.example.devintensive

import com.example.devintensive.extensions.TimeUnits
import com.example.devintensive.extensions.add
import com.example.devintensive.extensions.format
import com.example.devintensive.extensions.humanizeDiff
import com.example.devintensive.models.*
import com.example.devintensive.utils.Utils
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_instance(){
        val user = User("1")
        val user1 = User("2", "John", "Wick")
        val user2 = User("3", "John", "Larsen", null, lastVisit = Date(),
            isOnline = true)
    }
    @Test
    fun test_factory(){
        val user1 = User.makeUser("Doe John")
        val user2 = user1.copy(id="2", lastName = "Malcovich")
        print("$user1 \n$user2")
    }
    @Test
    fun test_extensions(){
        val user1 = User.makeUser("John Wick")
        user1.lastVisit = Date()
        val user2 = user1.copy(lastVisit = Date().add(3,TimeUnits.HOURS))
        print("${user1.lastVisit?.format()} \n${user2.lastVisit?.format()}")
    }
    @Test
    fun test_initials(){
        val user1 = User.makeUser("John Wick")
        val userInit = Utils.toInitials("Jot Plp")
        print(userInit)
    }
    @Test
    fun test_transliteration(){
        val user1 = User.makeUser("John Travolta")
        user1.lastVisit = Date().add(20, TimeUnits.MINUTES)
        print(user1.lastVisit!!.humanizeDiff())
        val toLatin = Utils.transliteration("Иван Ивановяч", "_")
        //print(toLatin)
    }
    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("John Travolta")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "Hello Nigga", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("1"), payload = "image.url", type = "image")
        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }
}