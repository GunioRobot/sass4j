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

### Maven Plugin
I have added a really hacked up maven plugin just to get started.  Embedding JRuby 
in a Maven plugin certainly adds some overhead but it works for now. This plugin
basically wraps the `sass --update <from>:<to>` command.  More options and features
to follow.


## TODO
### Full featured Java API
Right now I have only wrapped the command line options so that you can invoke
the commands just as you would with Ruby.  In order to get this to work as a Maven
plugin and a servlet filter, I will try to add some API sugar so that it can be
invoked from any JVM language without all of the overhead of creating new script
engines every time.

### Git Submodule for Sass source
For now I have just copied and hacked the latest source from Sass to get it to work here.
I'm hoping to set up some kind of git submodule setup to make it easier to integrate source
changes in Sass.  There are quite a few hacks to get it to work anyway, but hopefully
there is a better way to make this work and I think JRuby 1.6.0 will fix a lot of the issues.

[Sass]: http://sass-lang.com
[JRuby]: http://jruby.org