pipeline {
    agent any

    tools {
        gradle "8.1"
    }

        stages {
            stage ("Build") {
                steps
                        {
                          sh './gradlew clean test'
                        }
            }
        }
}