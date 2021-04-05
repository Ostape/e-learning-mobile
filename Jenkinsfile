pipeline {
    agent any
    stages {
        stage('Ktlint') {
            steps {
              withGradle {
                sh 'chmod +x ./gradlew'
                sh './gradlew ktlintCheck'
              }
            }
        }
        stage('Tests') {
            steps {
              withGradle {
                sh 'chmod +x ./gradlew'
                sh './gradlew test'
              }
            }
        }
    }
    post {
        always {
            emailext body: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: Check console output at $BUILD_URL to view the results.',
            recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!'
        }
    }
}