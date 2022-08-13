pipeline {

    agent any
    
    tools {
        gradle "GRADLE_HOME"
    }

    stages {
        stage('Analyze SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'gradle sonarqube'
                }
            }
        }

        stage('Build And Unit Test') {
            steps {
                sh "gradle build"
            }
        }
    }
    
    // post {
    //     success {
    //         archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
    //         junit 'build/reports/**/*.xml'
    //     }
    // }
}
