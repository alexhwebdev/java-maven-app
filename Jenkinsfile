def gv

pipeline {
    agent any
    // tools {
    //     maven 'maven-3.9'
    // }
    // environment {
    //     NEW_VERSION = '1.3.0'
    //     // Option 1:
    //     // SERVER_CREDENTIALS = credentials('server-credentials')
    // }
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'description')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'execute tests')
    }
    stages {
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }
        stage('build') {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }
        stage('test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage('deploy') {
            input {
                message "Select the environment to deploy to"
                ok "Done"
                parameters {
                    choice(name: 'ENV', choices: ['dev', 'staging', 'prod'], description: 'Select environment')
                }
            }
            steps {
                script {
                    gv.deployApp()
                    echo "deploying to ${ENV} environment"
                }

                // echo 'deploying the application...'
                // echo "deploying version ${params.VERSION}"
                // // // Option 1:
                // // //  echo "deploying with ${SERVER_CREDENTIALS}"
                // // //  sh "${SERVER_CREDENTIALS}"

                // // // Option 2:
                // // withCredentials([
                // //     usernamePassword(
                // //         credentials: 'server-credentials', 
                // //         usernameVariable: 'USER',
                // //         passwordVariable: 'PWD'
                // //     )
                // // ]){
                // //     echo "deploying with ${USER} and ${PWD}"
                // //     sh "some script ${USER} ${PWD}"
                // // }

            }
        }
    }
}






// def gv

// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }
//     stages {
//         stage('increment version') {
//             steps {
//                 script {
//                     echo 'incrementing app version...'
//                     sh 'mvn build-helper:parse-version versions:set \
//                         -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
//                         versions:commit'
//                     def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
//                     def version = matcher[0][1]
//                     env.IMAGE_NAME = "$version-$BUILD_NUMBER"
//                 }
//             }
//         }
//         stage('build app') {
//             steps {
//                 script {
//                     echo 'building the application...'
//                     sh 'mvn clean package'
//                 }
//             }
//         }
//         stage('build image') {
//             steps {
//                 script {
//                     echo "building the docker image..."
//                     withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
//                         sh "docker build -t nanatwn/demo-app:${IMAGE_NAME} ."
//                         sh 'echo $PASS | docker login -u $USER --password-stdin'
//                         sh "docker push nanatwn/demo-app:${IMAGE_NAME}"
//                     }
//                 }
//             }
//         }
//         stage('deploy') {
//             steps {
//                 script {
//                     echo 'deploying docker image...'
//                 }
//             }
//         }
//         stage('commit version update'){
//             steps {
//                 script {
//                     withCredentials([usernamePassword(credentialsId: 'gitlab-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
//                         sh 'git config --global user.email "jenkins@example.com"'
//                         sh 'git config --global user.name "jenkins"'

//                         sh 'git status'
//                         sh 'git branch'
//                         sh 'git config --list'

//                         // sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/twn-devops-bootcamp/latest/08-jenkins/java-maven-app.git"
//                         sh "git remote set-url origin https://${USER}:${PASS}@github.com/alexhwebdev/java-maven-app.git"
//                         sh 'git add .'
//                         sh 'git commit -m "ci: version bump"'
//                         sh 'git push origin HEAD:jenkins-jobs'
//                     }
//                 }
//             }
//         }
//     }
// }




// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }
//     stages {
//         stage('build') {
//             steps {
//                  echo 'building the application...'
//             }
//         }
//         stage('test') {
//             steps {
//                  echo 'testing the application...'
//             }
//         }
//         stage('deploy') {
//             steps {
//                  echo 'deploying the application...'
//             }
//         }
//     }
// }