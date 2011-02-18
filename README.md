# What is sass4j?
This library is a java wrapper around the [Sass] ruby code using [JRuby] 1.5.6.
There are other solutions to this problem, but none of them seemed to be keeping
current with the current state of the [Sass] 3 code line, so I set out to wrap it up
so that I can use it from Java, Scala, and other JVM powered languages.

I know everyone wants this to be implemented in native Java or Scala, but then
every time there is a new feature or extension added to [Sass] then you have to
implement the changes all over again in the native language.  Wrapping the sass
stuff in Java using [JRuby] will allow us to update directly from Sass itself
when these new things are added.  It might be a long shot, but this also
keeps open the possiblity of integrating with [Compass](http://compass-style.org/) or Sass mixins.

## What works
Right now you can use all of the command line options that sass accepts on the
command line.  This includes watching directories, updating, converting, and
even the interactive shell.

## TODO
### Full featured Java API
Right now I have only wrapped the command line options so that you can invoke
the commands just as you would with Ruby.  In order to get this to work as a Maven
plugin and a servlet filter, I will try to add some API sugar so that it can be
invoked from any JVM language without all of the overhead of creating new script
engines every time.

### Maven Plugin
The next step is to use the API and create a maven plugin for build time compilation and a
servlet filter to serve up the stylesheets at runtime if you desire.

[Sass]: http://sass-lang.com
[JRuby]: http://jruby.org