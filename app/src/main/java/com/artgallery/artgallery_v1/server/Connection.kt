package com.artgallery.artgallery_v1.server

import java.sql.DriverManager
import java.sql.PreparedStatement

interface Connection {
    fun init() {
        val driver = "com.mysql.jdbc.Driver"
        val url = "jdbc:mysql://31.31.202.250:3306/artgallery"
        val user = "artgallery"
        val psd = "123456"
        try{
            Class.forName(driver).newInstance()
            println("Connected successfully")
        }catch(e:Exception){
            e.printStackTrace()
            println("Connection failed")
        }

        try {
            Thread {
                val conn = DriverManager.getConnection(url, user, psd)
                val statment = conn.createStatement()
                val result = statment.executeQuery("select * from users")
                println(result.getString("name"))
            }.start()
        }catch(e:Exception){
            e.printStackTrace()
            println("Connection failed 2")
        }
    }
}