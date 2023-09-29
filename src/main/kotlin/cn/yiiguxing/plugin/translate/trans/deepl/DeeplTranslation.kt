package cn.yiiguxing.plugin.translate.trans.deepl

import cn.yiiguxing.plugin.translate.trans.Lang
import cn.yiiguxing.plugin.translate.trans.Translation
import cn.yiiguxing.plugin.translate.trans.TranslationAdapter
import cn.yiiguxing.plugin.translate.util.i
import com.google.gson.annotations.SerializedName
import com.intellij.openapi.diagnostic.Logger

data class DeeplTranslations(
        private val logger: Logger = Logger.getInstance(DeeplTranslations::class.java),

        var original: String? = null,
        var srcLang: Lang,
        var targetLang: Lang,
        var data: String? = null,
        var code: Int,
//    @SerializedName("translations" ) var translations : ArrayList<DeeplTranslation> = arrayListOf()
) : TranslationAdapter {


    //    val isSuccessful get() = translations[0].code == 0 || translations[0].code == 52000
    val isSuccessful get() = code == 200

    override fun toTranslation(): Translation {
//        val resultTranslation = translations[0]
//        check(original != null) { "Cannot convert to Translation: original=null" }
//        check(isSuccessful) { "Cannot convert to Translation: errorCode=$resultTranslation.code" }
//        check(resultTranslation.text != null) { "Cannot convert to Translation: trans=null" }
//        val srcLang = Lang.fromDeeplLanguageCode(resultTranslation.srcLanguage)
//        val translation = resultTranslation.text
//
//        return Translation(original!!, translation, srcLang, targetLang, listOf(srcLang))
        check(original != null) { "Cannot convert to Translation: original=null" }
        check(isSuccessful) { "Cannot convert to Translation: errorCode=$code" }
        check(!data.isNullOrEmpty()) { "Cannot convert to Translation: trans=null" }
//        val srcLang = Lang.fromDeeplLanguageCode("EN")
        val translation = data

        return Translation(original!!, translation, srcLang, targetLang, listOf(srcLang))
    }
}

data class DeeplTranslation(
        @SerializedName("error_code")
        val code: Int = 0,
        @SerializedName("detected_source_language")
        val srcLanguage: String,
        @SerializedName("text")
        val text: String? = null
)
