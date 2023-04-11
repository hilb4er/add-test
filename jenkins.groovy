node {

    try {
        stage("Run tests") {
            parallel(
                    'Api Tests': {
                        runTestWithTag("apiTests")
                    },
                    'Ui Tests': {
                        runTestWithTag("uiTests")
                    }
            )
        }
    } finally {
        stage("Allure") {
            generateAllure()
        }
    }
}

def runTestWithTag(String tag) {
    try {
        labelledShell(label: "Run ${tag}", script: "chmod +x gradlew \n./gradlew -x test ${tag}")
    } finally {
        echo "some failed tests"
    }
}

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                                        url: repo
                                ]]
    ]
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'build/allure-results']]
    ])
}



