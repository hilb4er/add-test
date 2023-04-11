pipeline {
    agent any

    tools {

        jdk "1.11"
        gradle "8.1"


        stages {
            stage ("Build") {
                step
                        {
                          sh './gradlew clean build'
                        }
            }

        }
    }


}