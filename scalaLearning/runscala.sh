#!/bin/sh

exec scala "$0" "$@"
::!#
object HelloWorld {
  def main(args: Array[String]) {
    println("Hello, World! "+args.toList)
  }
}

HelloWorld.main(args)
