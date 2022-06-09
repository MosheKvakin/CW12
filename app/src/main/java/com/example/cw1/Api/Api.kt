package com.example.cw1.Api
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
/*import kotlinx.coroutines.runBlocking*/
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json



class Api {
    private val _base = "api-fns.ru"
    private val _egr = "/api/egr?req"
    private val _api_key = "6b3262858c0ace097c87cb1f235b0c43166cc457"

    private val _base1 = "api.themoviedb.org"
    private val _movies1 = "3/movie/popular"
    private val _movie1 = "3/movie/"

//    https://api-fns.ru/api/egr?req=1032502271548&key=<Ваш ключ>


    val kLogger = object : Logger {
        override fun log(message: String) {
            if (message.contains("_api_key=")) {
                println("${message.split("=").first()}=_api_key")
            } else {
                println(message)
            }
        }
    }





    private val ktorClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })


        }
        install(Logging) {
            logger = kLogger
            level = LogLevel.BODY
        }


    }



    val clientGson = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        expectSuccess = true
    }


    suspend fun getItemEgr(req: String): ItemsEgr = ktorClient.use {
        it.get(
            host = _base,
            path = "api/egr?req=$req&key=$_api_key"
        ) {
            //parameter("api_key", BuildConfig.API_KEY)
        }
    }



    suspend fun getFSS(req: String): FSS = ktorClient.use {
        it.get(
            host = _base,
            path = "api/egr?req=$req&key=$_api_key"
        ) {
            //parameter("api_key", BuildConfig.API_KEY)
        }
    }

//Паспорт
suspend fun getPassport(req: String): PassportExpire = ktorClient.use {
    it.get(
        //https://api-fns.ru/api/mvdpass?docno=7500548998&key=<Ваш ключ>
        host = "api-fns.ru",
        path = "api/mvdpass?docno=$req&key=$_api_key"
    ) {
        //parameter("api_key", BuildConfig.API_KEY)
    }
}



    //https://api-fns.ru/api/innfl?fam=Иванов&nam=Степан&otch=Петрович&bdate=02.01.1935&doctype=21&docno=7500548998&key=<Ваш ключ>

    suspend fun getTINPerson(): TINPerson = ktorClient.use {
        it.get(
            host = "api-fns.ru",
            path ="api/innfl?fam=&nam=а&otch=&bdate=12.06.1972&doctype=21&docno=&key=$_api_key"
        ) {
           // parameter("key", _api_key)
        }
    }

    suspend fun getTINPerson2(
        _fam:String,
        _nam:String,
        _otch:String,
        _bdate:String,
        _doctype:String,
        _docno:String
    ): TINPersonItems = ktorClient.use {
        it.get(
            host = "api-fns.ru",
            path ="api/innfl?fam=$_fam&nam=$_nam&otch=$_otch&bdate=$_bdate&doctype=$_doctype&docno=$_docno&key=$_api_key"
        ) {
            // parameter("key", _api_key) А так не работает ))
        }
    }





    //https://api-fns.ru/api/innfl?fam=Иванов&nam=Степан&otch=Петрович&bdate=02.01.1935&doctype=21&docno=7500548998&key=<Ваш ключ>

    suspend fun getTINPerson1(): TINPerson = ktorClient.use {
        it.get(
            host = "api-fns.ru",
            path ="&key=$_api_key"
        ) {
            // parameter("key", _api_key)
        }
    }





    suspend fun getMovie1(movieId: Int): Movie = clientGson.use {
        it.get(
            host = _base1,
            path = _movie1 + movieId.toString()
        ) {
            parameter("api_key", "14b7da11c0f7fbc92f26a7cbcd9ff925")
        }
    }


    suspend fun getMovie(movieId: Int): Movie = ktorClient.use {
        it.get(
            host = _base1,
            path = _movie1 + movieId.toString()
        ) {
            parameter("api_key", "14b7da11c0f7fbc92f26a7cbcd9ff925")
        }
    }

    suspend fun getMovies(): Movies = ktorClient.use {
        it.get(
            host = _base,
            path = _movies1
        ) {
            parameter("api_key", "14b7da11c0f7fbc92f26a7cbcd9ff925")
        }
    }




}