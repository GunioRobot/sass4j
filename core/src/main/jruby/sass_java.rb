require 'java'
require 'sass'
require 'sass/engine'
require 'sass/cache_stores'
require 'sass/cache_stores/null'

java_package 'com.organicelement.sass4j'

class Sass4Java

  java_signature 'void compile_file(java.lang.String source, java.lang.String target)'
  def compile_file(source, target)
    options = {}
    options[:cache_store] ||= Sass::CacheStores::Null.new

    result = Sass::Engine.for_file(source, options).render
    if target
      options[:css_filename] ||= target
      open(target,"w") {|css_file| css_file.write(result)}
      nil
    else
      result
    end

  end

end