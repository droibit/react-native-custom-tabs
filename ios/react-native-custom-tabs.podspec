require 'json'

package = JSON.parse(File.read(File.join(__dir__, '../../node_modules/react-native-custom-tabs/package.json')))

Pod::Spec.new do |s|
  s.name                = "react-native-custom-tabs"
  s.version             = package['version']
  s.author              = 'droibit'
  s.license             = 'Apache'
  s.summary             = s.name
  s.homepage            = 'https://github.com/droibit/react-native-custom-tabs'
  s.source              = { :git => 'https://github.com/droibit/react-native-custom-tabs', :commit => '630f54c7ead2e18fd22df94edb5ab5242b674948' }
  s.requires_arc        = true
  s.platform            = :ios, "8.0"
  s.pod_target_xcconfig = { "CLANG_CXX_LANGUAGE_STANDARD" => "c++14" }
  s.header_dir          = 'ios'
  s.preserve_paths      = "ios/**"
  s.dependency      'React'
  s.source_files  = "ios/**/*.{h,m}"
  s.libraries     = "stdc++"
end
