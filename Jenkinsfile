node ('master') {
  stage 'Build and Test'
  def mvnHome = tool 'M3'
  env.PATH = "${mvnHome}/bin:${env.PATH}"
  sh 'mvn clean install'
}