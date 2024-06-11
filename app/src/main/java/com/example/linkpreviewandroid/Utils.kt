package com.example.linkpreviewandroid

import io.reactivex.rxjava3.core.Observable
import org.jsoup.Jsoup

object Utils {
    @JvmStatic
    fun getJsoupData(url:String):Observable<MetaData> {
        return Observable.fromCallable{
            try {
               val document =  Jsoup.connect(url).timeout(0).get()

                val imageUrl = document.select("meta[property=og:image]").attr("content")
                val title = document.select("meta[property=og:title]").attr("content")
                val videoUrl = document.select("meta[property=og:video]").attr("content")
                val description = document.select("meta[property=og:description]").attr("content")

                MetaData(title= title, image = imageUrl, video = videoUrl, description = description)
            }
            catch (e:Exception){
                println("getJsoupData $e")
                throw RuntimeException(e)
            }
        }
    }
}

data class MetaData(
    val title:String,
    val description:String,
    val image:String,
    val video:String

)