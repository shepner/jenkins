//https://www.edureka.co/community/55640/jenkins-docker-docker-image-jenkins-pipeline-docker-registry

pipeline {

  environment {
    registry = "docker_hub_account/repository_name"
    registryCredential = 'dockerhub'
  }
  
  agent any  

  stages {
    stage('Building image') {
      steps {
        script {
          docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
  }
}
