## LINK PREVIEW USING JSOUP 🔗

Mohan A @Andorid Developer

#### A preview of url link usually contains the title, a description, the domain name and an image.

![edit](https://github.com/mohanhu/LinkPreviewAndroid/assets/88998744/b8c1e2ab-aea1-4f1e-95c0-de11d0ba642d)

To import below depedencies 👇
```java
implementation 'com.github.bumptech.glide:glide:4.16.0'
implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
implementation 'io.reactivex.rxjava3:rxjava:3.1.5'
implementation 'org.jsoup:jsoup:1.14.3'
```
This one help to retrieve MetaData using url link support browser and youtube ❤️ .

```java
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
```
## Thanks 👨🏻‍💻

