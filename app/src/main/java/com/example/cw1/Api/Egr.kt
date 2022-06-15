package com.example.cw1.Api
/*
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

 */
import kotlinx.serialization.*
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
/*import kotlinx.coroutines.runBlocking*/
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

//@OptIn(ExperimentalSerializationApi::class)

@Serializable
data class ItemsEgr(
    @SerialName("items")
    val items: List<Egr> = ArrayList()
)

/*"Контакты":
{"Телефон":["+79288478827","+79186330283","+79183774782","+78612527933"]}
*/

@Serializable
data class Egr(
    @SerialName("ЮЛ")
   //val ulItem: List<UlItem> = ArrayList()
    val ulItem: UlItem

    //@SerialName("ФСС")
    //val ipItemFSS: FSS?,

   // @SerialName("НомТел")
   // val numtel: String? = null,
    //@SerialName("E-mail")
   //val email: String? = null
//
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class FSS(
    @SerialName("РегНомФСС")
    val regNumFSS: String?,
    @SerialName("ДатаРегФСС")
    val dateRegFSS: String?,
    @SerialName("КодФСС")
    val codeFSS: String?
)

/*
"ЮЛ": {
    "ИНН": "2540096950",
    "КПП": "254001001",
    "ОГРН": "1032502271548",
    "ДатаРег": "2003-09-08",
    "ОКОПФ": "Общества с ограниченной ответственностью",
    "Статус": "Действующее",
    "СпОбрЮЛ": "Создание юридического лица",
    "НО": {
        "Рег": "2536 (Инспекция Федеральной налоговой службы по Ленинскому району г.Владивостока)",
        "РегДата": "2016-01-22",
        "Учет": "2540 (Инспекция Федеральной налоговой службы по Фрунзенскому району г.Владивостока)",
        "УчетДата": "2003-09-08"
    },


    "Контакты":
    {"Телефон":["+79288478827","+79186330283","+79183774782","+78612527933"]}
    */

@Serializable
data class UlItem(
    @SerialName("ИНН")
    val inn: String?=null,
    @SerialName("КПП")
    val kpp: String?=null,
  /*  @SerialName("ОГРН")
    val ogrn: String?=null
    */

/*
"ДатаРег": "2003-09-08",
"ОКОПФ": "Общества с ограниченной ответственностью",
"Статус": "Действующее",
"СпОбрЮЛ": "Создание юридического лица",
 */
)



///Паспорт - действителен или нет
/*
Примеры запросов.
https://api-fns.ru/api/mvdpass?docno=7500548998&key=<Ваш ключ>

Пример ответа сервера...

{
    "result": "Cреди недействительных не значится"
}

 */

@Serializable
data class PassportExpire(
    @SerialName("result")
    val result: String?
)


/*
Узнать ИНН физического лица
Возвращает ИНН физического лица на основании введенных паспортных данных.

Адрес сервиса: https://api-fns.ru/api/innfl

Используется запрос GET или POST.

Параметры запроса:
Параметр	Тип данных и пример	Описание
fam	string
fam=Иванов	Фамилия
nam	string
nam=Степан	Имя
otch	string
otch=Петрович	Отчество. Если отчество отсутствует - укажите otch=нет
bdate	string
bdate=02.01.1935	Дата рождения в формате ДД.ММ.ГГГГ
doctype	string
doctype=21	Вид документа, удостоверяющего личность:
01 - Паспорт гражданина СССР
03 - Свидетельство о рождении
10 - Паспорт иностранного гражданина
12 - Вид на жительство в Российской Федерации
15 - Разрешение на временное проживание в Российской Федерации
19 - Свидетельство о предоставлении временного убежища на территории Российской Федерации
21 - Паспорт гражданина Российской Федерации
23 - Свидетельство о рождении, выданное уполномоченным органом иностранного государства
Необязательное поле (по умолчанию - код 21).

docno	string
docno=4611123456	Серия и номер документа (можно вводить как с пробелами, так и без пробелов между серией и номером)
key	string	Ваш ключ доступа к API
Возвращаемый документ (HTTP response) представляет собой структурированный документ JSON, содержащий информацию о найденном ИНН.

Поля возвращаемого документа:
Имя	Тип	Описание
items	Array	Массив результатов поиска
ИНН	String	ИНН искомого физического лица
error	String	Сообщение об ошибке, если введены некорректные данные
Примеры запросов.
//https://api-fns.ru/api/innfl?fam=Иванов&nam=Степан&otch=Петрович&bdate=02.01.1935&doctype=21&docno=7500548998&key=<Ваш ключ>

Пример ответа сервера...

{
    "items": [
    {
        "ИНН": "504603092301"
    }
    ]
}
*/



@Serializable
data class TINPerson(
    @SerialName("ИНН")
    val result: String?=null
)

@Serializable
data class TINPersonItems(
    @SerialName("items")
    val results: List<TINPerson> = ArrayList()
)





@Serializable
class Movies
    (
    @SerialName("page")
    val page: Int,
    @SerialName("result")
    val result : List<Movie> =ArrayList(),
    @SerialName("total_results")
    val total_results: Int,
    @SerialName("total_pages")
    val total_pages:  Int
)


@Serializable
data class Movie
    (
    @SerialName("id") //TODO рассказать
    val id: Int,
    @SerialName("title")
    val title: String? = null,
    @SerialName("poster")
    val poster: String? = null,
    //
    @SerialName("poster_patch")
    val posterPath: String? = null,
    //
    @SerialName("original_title")
    val originalTitle: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("release_date")
    val releaseDate: String? = null
)


