@Library('asyla') _

pipeline {
    agent any 
    stages {
        stage('Stage 1') {
            steps {
                script {
                    helloWorld()
                    helloWorld 'test'
                    helloWorld('test2')
                }
            }
        }
    }
}
