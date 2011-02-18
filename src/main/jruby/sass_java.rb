require 'java'
require 'sass'
require 'sass/exec'

java_package 'com.organicelement.sass4j'

class SassJava
  java_signature 'void main(String[])'
  def self.main(args)
      newopts = args.to_a
      opts = Sass::Exec::Sass.new(newopts)

      begin
        opts.parse!
      rescue SystemExit => se
          puts "Exited normally"
      end
  end
end