require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name                = "react-native-custom-tabs"
  s.version             = package['version']
  s.author              = { "droibit" => "roomful.rooms@gmail.com" }
  s.license             = 'Apache'
  s.summary             = s.name
  s.homepage            = 'https://github.com/droibit/react-native-custom-tabs'
  s.source              = { :git => 'https://github.com/droibit/react-native-custom-tabs.git', :tag => "#{s.version}" }
  s.requires_arc        = true
  s.platform            = :ios, "8.0"
  s.pod_target_xcconfig = { "CLANG_CXX_LANGUAGE_STANDARD" => "c++14" }
  s.header_dir          = 'ios'
  s.preserve_paths      = "ios/**"
  s.dependency      'React'
  s.source_files        = 'ios/**/*.{h,m}'
  s.exclude_files       = 'android/**/*'
  s.libraries     = "stdc++"
end
