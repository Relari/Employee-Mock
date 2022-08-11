pipeline {

    agent any
    
    tools {
        gradle "GRADLE_HOME"
    }

    stages {
        stage('Build And Unit Test') {
            steps {
                sh "gradle build"
            }
        }

        stage('Analyze SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube-test') {
                    sh 'gradle --info sonarqube'
                }
            }
        }

    }
    
    post {
        success {
            junit allowEmptyResults: true, testResults: '**/test-results/*.xml'
            //archiveArtifacts 'target/*.jar'
        }
    }
}
