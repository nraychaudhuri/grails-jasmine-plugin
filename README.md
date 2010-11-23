Grails plugin to run jasmine BDD specs from grails project
================

In this 0.1 release of the plugin you can run jasmine tests from command line by using "grails run-jasmine".
The "grails run-jasmine-ci" command should also work but I am still trying to figure out how to get the 
report back from jasmine in a meaningful format.

How this works?
===============
I am using JRuby Redbridge to invoke jasmine ruby gem from the plugin. I know its crazy but it works :)


How to Start?
===============
* Install the grails plugin
* Install JRuby if you haven't already (I have used JRuby 1.5.5)
* Before running the plugin make sure to set the JRUBY_HOME

