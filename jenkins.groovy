pipeline {
    agent any

    tools {

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