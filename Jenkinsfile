pipeline {

    agent any
    
    tools {
        gradle "GRADLE_HOME"
    }

    stages {

        stage('Compile') {
            steps {
                sh 'gradle compileJava'
            }
        }

        stage('Unit test') {
            steps {
                sh 'gradle test'
            }
        }

        stage('Code coverage - JaCoCo') {
            steps {
                sh 'gradle jacocoTestReport'
                    publishHTML (target: [
                        reportDir: 'build/reports/jacoco/test/html',
                        reportFiles: 'index.html',
                        reportName: 'JacocoReport'
                    ])

                    sh 'gradle jacocoTestCoverageVerification'
            }
        }

        stage('Analyze SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'gradle sonarqube'
                }
            }
        }

        // stage('Build And Unit Test') {
        //     steps {
        //         sh "gradle build"
        //     }
        // }
    }
    
    // post {
    //     success {
    //         archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
    //         junit 'build/reports/**/*.xml'
    //     }
    // }
}
