import org.jruby.embed.PathType

includeTargets << grailsScript("_GrailsArgParsing")

USAGE = """
    jasmine [--prefix=PREFIX]

where
    PREFIX = The prefix to add to the names of the realm and domain classes.
             This may include a package. (default: "Shiro").
"""

target(default: "Runs the jasmine server") {
  def c = new org.jruby.embed.ScriptingContainer()
  def jrubyHome = System.getenv("JRUBY_HOME")
  c.runScriptlet("ENV['GEM_PATH']='${jrubyHome}/lib/ruby/gems/1.8'")

  def script = """
                  require 'rubygems'                                                   
                  require 'jasmine'  
                  jasmine_config_overrides = 'PLUGIN_DIR/scripts/jasmine_config.rb'
                  require jasmine_config_overrides if File.exist?(jasmine_config_overrides)

                  puts "your tests are here:"
                  puts "  http://localhost:8888/"

                  Jasmine::Config.new.start_server                                                                  
               """
  script = script.replace("PLUGIN_DIR", "${jasminePluginDir}")   
  c.runScriptlet(script)             
}
