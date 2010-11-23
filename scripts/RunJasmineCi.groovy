import org.jruby.embed.PathType

includeTargets << grailsScript("_GrailsArgParsing")

USAGE = """
    jasmine [--prefix=PREFIX]

where
    PREFIX = The prefix to add to the names of the realm and domain classes.
             This may include a package. (default: "Shiro").
"""

target(default: "Run continuous integration tests") {
  def c = new org.jruby.embed.ScriptingContainer()
  def jrubyHome = System.getenv("JRUBY_HOME")
  c.runScriptlet("ENV['GEM_PATH']='${jrubyHome}/lib/ruby/gems/1.8'")

  def script = """
    require 'rubygems'
    require 'jasmine'
    jasmine_config_overrides = 'PLUGIN_DIR/scripts/jasmine_config.rb'
    require jasmine_config_overrides if File.exist?(jasmine_config_overrides)
    if Jasmine::rspec2?
      require 'rspec'
    else
      require 'spec'
    end

    jasmine_config = Jasmine::Config.new
    spec_builder = Jasmine::SpecBuilder.new(jasmine_config)

    should_stop = false

    if Jasmine::rspec2?
      RSpec.configuration.after(:suite) do
        spec_builder.stop if should_stop
      end
    else
      Spec::Runner.configure do |config|
        config.after(:suite) do
          spec_builder.stop if should_stop
        end
      end
    end

    spec_builder.start
    should_stop = true
    x = spec_builder.load_results
    #x = spec_builder.declare_suites
    puts x
    spec_builder.stop
  """
  script = script.replace("PLUGIN_DIR", "${jasminePluginDir}")   
  c.runScriptlet(script)             
}