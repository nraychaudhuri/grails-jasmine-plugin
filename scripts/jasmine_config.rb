class Jasmine::Config
  def project_root
    File.expand_path(File.join(File.dirname(__FILE__), ".."))
  end

  def src_files
    files = match_files(project_root, "/web-app/js/*.js")
    files
  end  
  
  def spec_dir
    File.join(project_root, 'test/javascripts/spec/javascripts')
  end
end