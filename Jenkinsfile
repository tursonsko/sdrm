pipeline {
    agent { label 'java-11' }
     stages{
        stage('Test'){
            steps{
                sh './gradlew test'
            }
        }
     }
}