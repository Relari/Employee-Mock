pipeline {

    agent any

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        def archivePom = readMavenPom()

        ARTIFACT_ID = archivePom.getArtifactId()
        PROJECT_VERSION = archivePom.getVersion()

        def dockerImage = ""

    }
    
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
                    sh 'mvn clean verify sonar:sonar'
                }
            }
        }

//         stage('Create Branch') {
//             steps {
//                 bat 'git branch RC-${IMAGE}:${VERSION}'
//                 bat 'git push'
//             }
//         }

        stage('Build Docker Image') {
            steps {

                script {
                    // dockerImage = docker.build "relari/${IMAGE}"

                    sh "docker build -t relari/${ARTIFACT_ID}:test ."

                }

            }
        }
        // stage('Docker Build & Push') {
        //   steps {
        //     script {  
        //       docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
        //         def appmavenjenkins = docker.build("relari/${ARTIFACT_ID}:${gitcommit}", ".")
        //         appmavenjenkins.push()
        //       }
        //     }  
        //   }  
        // }
//
//         stage('Deploy') {
//             steps {
//                 echo 'Deploy App'
//                 sh """
//                     docker run --name service-api-employee -it -p 8081:8081 employee
//                 """
//             }
//         }
    }
    
    post {
        success {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.jar'
            slackSend message: "Build Success - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
        }
        failure {
            slackSend message: "Build Failure - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
        }
//         always {
//             slackSend message: "Build Always - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
//         }
    }
}
