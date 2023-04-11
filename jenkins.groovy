pipeline {
    agent any

    tools {
        gradle "8.1"
    }

        stages {
            stage ("Build") {
                steps
                        {
                          sh './gradlew clean apiTests'
                        }
            }
        }
    post {

        always {
        allure ([
                reportBuildPolicy: 'ALWAYS',
                results          : [[path: 'build/allure-results']]

        ])

        }
    }
}