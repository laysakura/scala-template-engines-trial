package com.github.laysakura

import java.io.{File, OutputStreamWriter}

import freemarker.template.Configuration

import scala.collection.immutable.HashMap

object FreeMarkerTrial {
  def main(args: Array[String]): Unit = {
    val cfg = new Configuration(Configuration.VERSION_2_3_23)
    cfg.setDirectoryForTemplateLoading(new File(getClass.getResource("/templates/freemarker").getPath))
    cfg.setDefaultEncoding("UTF-8")
    cfg.setRecognizeStandardFileExtensions(true)

    import scala.collection.JavaConverters._

    // 注意: scalaのMapを使うと、存在する変数は `Some(xxx)` 、存在しない変数は `None` と解決されてしまう。
    val bindings: java.util.Map[String, String] = HashMap(
      "firstName" -> "Sho",
      //"lastN@me" -> "Nakatani",                      // lastName has typo. #process() throws freemarker.core.InvalidReferenceException
      "lastName" -> "Nakatani",
      "htmlText" -> "<script>alert('xss')</script>"  // HTML tags are escaped
    ).asJava

    val temp = cfg.getTemplate("greeting.ftlh")
    try {
      temp.process(bindings, new OutputStreamWriter(System.out))
    } catch {
      case ex: freemarker.core.InvalidReferenceException =>
        println("exception!!")
        println(ex)
    }

  }
}
