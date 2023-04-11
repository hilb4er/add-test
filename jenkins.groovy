pipeline {
    agent any

    tools {
        gradle "8.1"
    }

        stages {
            stage ("Build") {
//                when {
//                    branch 'master'
//                }
                steps
                        {
                          sh './gradlew clean apiTests'
//                            try
//                            {
//                                lablledShell(label: "Run API", script: "./gradlew -x test API")
//                            }finally
//                            {
//                                echo "гавно всё"
//                            }
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