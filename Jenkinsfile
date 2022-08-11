pipeline {

    agent any
    
    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Build And Unit Test') {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Analyze SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    bat 'mvn clean verify sonar:sonar'
                }
            }
        }

    }
    
    post {
        success {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.jar'
        }
    }
}
