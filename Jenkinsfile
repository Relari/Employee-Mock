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

        // stage('Analyze SonarQube') {
        //     steps {
        //         withSonarQubeEnv('sonarqube') {
        //             bat 'mvn clean verify sonar:sonar'
        //         }
        //     }
        // }

    }
    
    post {
        success {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.jar'
        }
    }
}
