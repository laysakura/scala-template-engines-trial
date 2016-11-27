package com.github.laysakura

import org.fusesource.scalate.TemplateEngine

object MustacheTrial {
  def main(args: Array[String]): Unit = {
    val engine = new TemplateEngine
    val bindings = Map(
      "firstName" -> "Sho",
      "lastN@me" -> "Nakatani",                      // lastName is not rendered because key has typo
      "htmlText" -> "<script>alert('xss')</script>"  // HTML tags are escaped
    )
    val output = engine.layout(
      getClass.getResource("/templates/mustache/greeting.mustache").getPath,
      bindings
    )
    println(output)
  }
}
